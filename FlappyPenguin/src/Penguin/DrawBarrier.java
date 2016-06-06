package Penguin;

import java.awt.*;
import java.awt.Color;

/**
 * Metoda odpowiedzlna za rysowanie barier
 */
public class DrawBarrier
{
    /**
     * Tablica przechowująca parametru górnych przeszkody.
     */
    //Tablice przechowujace parametry przeszkod
    private int[][][] przeszkodaGora=
            {{{0,90,450},{0,90,550},{0,90,350},{0,90,450},{0,90,550},{0,90,350}},
                    {{0,90,450},{0,90,550},{0,90,350},{0,90,450},{0,90,550},{0,90,350}}};

    /**
     * Tablica przechowująca parametru dolnych przeszkód.
     */
    private int[][][] przeszkodaDol=
            {{{650,90,170},{750,90,70},{550,90,270},{650,90,170},{750,90,70},{550,90,270}},
                    {{650,90,170},{750,90,70},{550,90,270},{650,90,170},{750,90,70},{550,90,270}}};
    private boolean checkPoint=false;
    private int z=0,przesuniecie;

    public void draw(int xW,Graphics g)
    {
        Color barrierColor=new Color(156,210,94);
        g.setColor(barrierColor);
        for(int i=0;i<4;i++)
        {
            this.przesuniecie=i*310;
            g.fill3DRect(xW+przesuniecie,przeszkodaGora[z][i][0],przeszkodaGora[z][i][1],przeszkodaGora[z][i][2],true);
            g.fill3DRect(xW+przesuniecie,przeszkodaDol[z][i][0],przeszkodaDol[z][i][1],przeszkodaDol[z][i][2],true);
        }
    }

    /**
     * Metoda odpowedzialna gdy pukty kolizycje z postaci znajdują się w obrębie rysowanej przeszkody.
     * @param pointX współrzędna x pkt. jednego z pięciu punktów kolizyjnych zlokalizowanych na postaci.
     * @param pointY współrzędna y pkt. jednego z pięciu punktów kolizyjnych zlokalizowanych na postaci.
     * @param xPrze przesuniecie przeszkody wzgledem położenia początkowego(takiego od któego startuge gra).
     * @param g obiekt klasy Graphics odpowiedzialny za rysowanie grafiki.
     * @return metoda zwraca wartość boolean, jeżeli dochodzi do kolizji pkt. kolizyjnego postaci z bariera
     */
    public boolean checkPoint(int pointX,int pointY,int xPrze,Graphics g)
    {
        for(int i=0;i<4;i++)
            {
                if(pointY<=przeszkodaGora[z][i][2] && pointX>=(xPrze+i*310) && pointX<=(xPrze+i*310)+90)
                    checkPoint=true;
                if(pointY>=przeszkodaDol[z][i][0] && pointX>=(xPrze+i*310) && pointX<=(xPrze+i*310)+90)
                    checkPoint=true;
            }
        return checkPoint;
    }
}