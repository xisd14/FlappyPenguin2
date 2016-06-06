package Okienko;

import java.awt.*;

/**
 * Klasa odpowiedzialna za wyznaczanie parametrów monitora(rozdzielczości).
 */
public class GetSize
{
    private int w,h;
    private Double height,width;

    /**
     * Metoda ustalająca rozdzielczośc ekanu i przypisywanie jest to parametrów Double height oraz width.
     */
    public void setValue()
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        height = screenSize.getHeight();
        width=screenSize.getWidth();
        this.h=height.intValue();
        this.w=width.intValue();
    }

    /**
     * @return zwracanie wysokości ekranu.
     */
    public int getH()
    {
        return h;
    }

    /**
     * @return zwracanie szerokości ekranu.
     */
    public int getW()
    {
        return w;
    }
}

