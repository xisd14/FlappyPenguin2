package Penguin;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;


/**
 * Główna klasa odpowiedzialna za rysowanie postaci, powoływanie barier oraz ruch postaci i barier.
 */
public class DrawPenguin extends JComponent implements ActionListener,MouseListener
{
	Timer t=new Timer(18,this);
	private BufferedImage image,apple;
	private boolean q2=false, init=true, q3=false, sem=true,kolizja=false,kolizjaBariera=false,rank=true;
	private int x=300, y=440, xPrze=600, xPrze2=1200, xPrze3=1200, score=0,movePenguin=50,moveBarier=6;
	private Integer wynik=0;
	private int collisionPoints[][]=new int[5][5];
	private Menu menu=new Menu();
	public boolean start=true,tmp=false,mouse=true;
	private String aktualnyWynik,nazwa,lista;
	private Sql s1=new Sql();

	/**
	 * Parametry przechowujące stany gry.
	 */
	public static enum STATE
	{
		MENU,
		GAME,
		RANK,
        GAMEOVER
	};
	public static STATE State=STATE.MENU;

	/**
	 * Konstruktor ustawia parametry początkowe, ładuje ikonę do parametru image
	 */
	//Konstruktor (wczytanie ikony pingwina do rysowania w grze)
	public DrawPenguin()
	{
		try
	{
		image = ImageIO.read(new File("ikona.png"));
	}
	catch (IOException ex)
	{
		System.out.println("Brak ikonki");
		System.exit(1);
	}
	}
	//Metoda odpowiedzalna za rysowanie calej grafiki gry (pingwin, przeszkody,grafika)

	/**
	 * Metoda odpowiedzialna za rysowanie bohatera gry(pingwina), dodatkowo powolywane są przeszkody z klasy DrawBarrier oraz wyznaczane są stany gry.
	 * @param g obiekt klasy Graphics odpowiedzialny za rysowanie grafiki w grze
	 */
	public void paint(Graphics g)
	{
		if(State==STATE.GAME)
		{
			tmp=false;
			g.drawImage(image,x,y,null);
			g.setColor(Color.blue);
			Penguin.Graphic g1 =new Penguin.Graphic();
			g1.drawGraphic(g);
			//Powolanie obiektow klasy barier
			DrawBarrier d1=new DrawBarrier();
			DrawBarrier d2=new DrawBarrier();
			DrawBarrier d3=new DrawBarrier();
			//sprawdzenie stanow dla barier i rysowanie odpowiedniego obiektu
			if(init)
			{
				d1.draw(xPrze,g);
			}
			if(q2)
			{
				d2.draw(xPrze2,g);
			}
			if(q3)
			{
				d3.draw(xPrze3,g);
			}
			//Sprawdzenie zderzenia pingwina
			setCollisionPoints(x,y);
			Collision c1=new Collision();
			//sprawdzanie kolizji z podlozem
			kolizja=c1.chceckPenguin(y);
			DrawBarrier d4=new DrawBarrier();
			for(int i=0;i<5;i++)
			{
				if(q2==true) kolizjaBariera|=d4.checkPoint(collisionPoints[i][0],collisionPoints[i][1],xPrze2,g);
				if(q3==true) kolizjaBariera|=d4.checkPoint(collisionPoints[i][0],collisionPoints[i][1],xPrze3,g);
				if(xPrze>=-1200) kolizjaBariera|=d4.checkPoint(collisionPoints[i][0],collisionPoints[i][1],xPrze,g);
			}
			if(kolizja==false&&kolizjaBariera==false)
			{

				t.start();
				DrawScore s=new DrawScore();
				aktualnyWynik="Score: "+Integer.toString(0);
				aktualnyWynik="Score: "+Integer.toString(wynik);
				s.drawActualScore(aktualnyWynik,g);
				score++;
				if(score%50==0)
				{
					wynik++;
				}
			}
			else
			{
                State=STATE.GAMEOVER;
			}
		}
		else if(State==STATE.MENU)
		{
			menu.render(g);
			tmp=true;
		}
		else if(State==STATE.RANK)
		{
			g.setFont(new Font("Jokerman",Font.BOLD,40));
			java.util.List<Gracz> gracze = null;
			try
			{
				gracze = s1.selectRanking();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
			g.drawString("Ranking",500,100);
			for(int c=0;c<gracze.size();c++)
			{
				int y;
				this.lista= Integer.toString(c+1) + ". "+ gracze.get(c).getNazwa() +"  "+ Integer.toString(gracze.get(c).getWynik())+" pkt.";
				if(c==0) y=250;
				else y=c*100+250;
				g.drawString(this.lista,430,y);
			}
			g.drawString("Wstecz",500,800);
		}
        else if(State==STATE.GAMEOVER)
        {
            tmp=true;
            t.stop();
            g.setColor(Color.BLUE);
            g.drawRect(0,0,1200,900);
            g.setColor(Color.RED);
            g.setFont(new Font("Times New Roman",Font.BOLD,24));
            g.drawString("GAME OVER",500,450);
            g.drawString(aktualnyWynik,500,470);
            g.setColor(Color.black);
            g.setFont(new Font("Times New Roman",Font.BOLD,16));
            g.drawString("Click to MENU",500,510);
        }
		t.start();
		repaint();
        if(mouse)
        {
            this.addMouseListener(this);
            this.addMouseListener(new MouseInput());
            mouse=false;
        }
	}

	/**
	 * Metoda odpowiedzialna za ruch pingwina ruchomych elementów gry(pingwin,przeszkody), operacje te wykonywane są przy użyciu timera.
	 * @param e obiekt klasy ActionEvent obsulgujący timer
	 */
	public void actionPerformed(ActionEvent e)
	{
		if(!tmp)
		{
			//Opadanie pingiwna
			y+=5;
			//Sterowanie barierami przez timer
			if(xPrze<=-1200)
			{
				init=false;
			}
			else xPrze=xPrze-moveBarier;
			if(xPrze==0) q2=true;

			if(q2)
			{
				xPrze2=xPrze2-moveBarier;
				if(xPrze2==0)
				{
					q3=true;
				}
			}
			if(q3)
			{
				xPrze3=xPrze3-moveBarier;
				if(xPrze3==-1200)
				{
					xPrze3=1200;
				}
			}
			if(xPrze3==0)
			{
				xPrze2=1200;
			}
			sem=true;
			repaint();
		}

	}
	/**
	 * Metoda odpowiedzialna za nasłuch myszki, odpowiedzialna jest za ruch przeszkody w górę po kliknieciu oraz zmianę statusu gry w odpowiednim momencie.
	 * @param e  obiekt klasy MouseEvent obsulgujący klikniecia myszka
	 */
	public void mousePressed(MouseEvent e)
	{
		if(sem)
		{
			sem=false;
			y=y-movePenguin;
			repaint();
		}
		if(kolizja||kolizjaBariera)
		{
			nazwa = JOptionPane.showInputDialog("Koniec gry!","Podaj nazwe:");
			rank=false;
			s1=new Sql();
			s1.insertRekord(nazwa,wynik);
				kolizja=false;
				int x=300, y=440, xPrze=600, xPrze2=1200, xPrze3=1200, score=0;
				this.x=300;
				this.y=440;
				this.xPrze=600;
				this.xPrze2=1200;
				this.xPrze3=1200;
				this.wynik=0;
				this.q2=false; this.init=true;this.q3=false;this.sem=true;this.kolizja=false;kolizjaBariera=false;rank=true;
				State=STATE.MENU;
		}
	}
	//Metoda odpowiedzialna za ustawianie pkt. kolizyjnych pingwina

	/**
	 * Metoda odpowiedzialna za ustalenie pukntów kolizyjcnyh na ikonie pingwina
	 * @param x podaje współrzędne x ikony
	 * @param y podaje współrzędne y ikony
	 */
	public void setCollisionPoints(int x,int y)
	{
		//1 pkt.
		this.collisionPoints[0][0]=x+42;
		this.collisionPoints[0][1]=y+10;
		//2 pkt.
		this.collisionPoints[1][0]=x+25;
		this.collisionPoints[1][1]=y+42;
		//3pkt.
		this.collisionPoints[2][0]=x+5;
		this.collisionPoints[2][1]=y+36;
		//4pkt.
		this.collisionPoints[3][0]=x+12;
		this.collisionPoints[3][1]=y+20;
		//5pkt.
		this.collisionPoints[4][0]=x+28;
		this.collisionPoints[4][1]=y+3;
	}
	public void mouseReleased(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseClicked(MouseEvent e){}


	/*public void mouseClicked(MouseEvent e)
    {
        int mx=e.getX();
        int my=e.getY();

        if(mx>=500&&mx<=730)
        {
            //Play button
            if(my>=300&&my<370)
            {

                DrawPenguin.State=DrawPenguin.STATE.GAME;
                System.out.println("Jestem w stanie gry");
            }
            if(my>=400&&my<=470)
            {

                DrawPenguin.State= STATE.RANK;
                System.out.println("Jestem w stanie rankingu");
            }
            //Exit button
            if(my>=480&&my<=550)
            {

                System.exit(1);
            }
            if(my>=770&&my<=810)
            {
                System.out.println("jest w wroc");
                DrawPenguin.State=STATE.MENU;

            }
        }
        // else DrawPenguin.State=STATE.MENU;
        System.out.println("Drugie nasluchiwanie");
    }*/
}