package Penguin;

import java.awt.*;


/**
 * Klasa odpowiedzlane za wyswietlenie wyniku gry
 */
public class DrawScore
{

    /**
     * Metoda odpowiedzialna za wypisanie wyniku końcowego gry
     * @param wynik w tym parametrze umieszczony jest wynik wyznaczony w klasie DrawPenguin
     * @param g obiekt klasy Graphics
     */
    public void drawActualScore(String wynik,Graphics g)
    {
        g.setColor(Color.black);
        g.setFont(new Font("Times New Roman",Font.BOLD,16));
        g.drawString(wynik,1000,860);
    }
}