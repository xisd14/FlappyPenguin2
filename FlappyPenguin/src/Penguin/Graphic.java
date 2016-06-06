package Penguin;

import Okienko.GetSize;
import java.awt.Color;
import java.awt.Graphics;


/**
 * Klasa odpowiedzialna za rysowanie grafiki(ziemia,trawa)
 */
public class Graphic
{	
	private int w,h;

	/**
	 * Metoda rysująca grafikę trawę oraz ziemię
	 * @param g obiekt klasy Graphics odpowiedzialny za wywoływanie metod rysujących.
	 */
	public void drawGraphic(Graphics g)
	{
		//Pobieranie rozdzilczosci ekranu z classy GetSize
		GetSize gsG=new GetSize();
		gsG.setValue();

		this.h=gsG.getH();
		this.w=gsG.getW();
		//Rysowanie
		//draw grass
		Color grass=new Color(0,102,0);
		g.setColor(grass);
		g.fillRect(0,820,1200,20);
		//draw ground
		Color ground=new Color(102,51,0);
		g.setColor(ground);
		g.fillRect(0,840,1200,60);
	}
}