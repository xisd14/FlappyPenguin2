package Penguin;

/**
 * Created by Bartek` on 2016-05-30.
 */
public class Gracz
{
    private int id,wynik;
    private String nazwa;
    public int getID()
    {
        return id;
    }
    public void setID(int id)
    {
        this.id=id;
    }
    public int getWynik()
    {
        return wynik;
    }
    public void setWynik(int wynik)
    {
        this.wynik=wynik;
    }
    public String getNazwa()
    {
        return nazwa;
    }
    public void setNazwa(String nazwa)
    {
        this.nazwa=nazwa;
    }
    //public Gracz(){};
    public Gracz(int id,int wynik,String nazwa)
    {
        this.id=id;
        this.wynik=wynik;
        this.nazwa=nazwa;
    }

}
