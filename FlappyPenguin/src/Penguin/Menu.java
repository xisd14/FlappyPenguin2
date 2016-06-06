package Penguin;

import java.awt.*;

/**
 * Klasa odpowiedzialna za tworzenie menu gry.
 */
public class Menu
{
    private Rectangle playButton=new Rectangle(500,300,230,70);
    private Rectangle rankButton=new Rectangle(500,390,230,70);
    private Rectangle quitButton=new Rectangle(500,480,230,70);
    private Graphic g1;

    /**
     * Metoda odpowiedzialna za renderowanie menu gry. Wyswietla przyciski menu.
     * @param g obiekt klasy Graphics odpowiedzialny za rysowanie grafiki.
     */
    public void render(Graphics g)
    {
        g1 =new Graphic();
        g1.drawGraphic(g);
        Graphics2D g2d=(Graphics2D) g;
        Font font1=new Font("Jokerman",Font.BOLD,50);
        g.setFont(font1);
        g.setColor(Color.black);
        g.drawString("Flappy Penguin",400,200);
        g.drawString("Zagraj",playButton.x+10,playButton.y+55);
        g.drawString("Ranking", rankButton.x+10,rankButton.y+55);
        g.drawString("Koniec", quitButton.x+10,quitButton.y+55);
        g2d.draw(playButton);
        g2d.draw(rankButton);
        g2d.draw(quitButton);
    }
}
