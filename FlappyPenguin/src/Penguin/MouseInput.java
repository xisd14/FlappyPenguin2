package Penguin;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;

/**
 * Klasa odpowiedzialna za wybór elementów menu i przełączenie w odpowiedni stan gry.Klas ta dziedziczy po klasie DrawPenguin oraz implementuje MouseListener.
 */
public class MouseInput extends DrawPenguin implements MouseListener
{
    private boolean on=true;
    public void mouseClicked(MouseEvent e)
    {}

    /**
     * Metoda obsługuje kliknięcie przycików menu i ustawia odpowiedni stan gry.
     * @param e Obiekt klasy MouseEvent odpowiedzialny za wywoływani metod obsługi myszki.
     */
    public void mousePressed(MouseEvent e)
    {
        int mx=e.getX();
        int my=e.getY();

            if(mx>=500&&mx<=730)
            {
                //Play button
                if(my>=300&&my<370)
                {
                    on=false;
                    DrawPenguin.State=DrawPenguin.STATE.GAME;
                }
                if(my>=400&&my<=470)
                {
                    on=false;
                    DrawPenguin.State= STATE.RANK;
                }
                //Exit button
                if(my>=480&&my<=550)
                {
                    on=false;
                    System.exit(1);
                }
                if(my>=770&&my<=810)
                {
                    on=false;
                    DrawPenguin.State=STATE.MENU;

                }
            }
    }
    public void setOn()
    {
        this.on=true;
    }
    public void mouseReleased(MouseEvent e)
    {}
    public void mouseEntered(MouseEvent e)
    {}
    public void mouseExited(MouseEvent e)
    {}
}
