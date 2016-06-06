package Penguin;

/**
 * Klasa odpowiedzialna za sprawdzanie kolizji postaci z podłożem oraz z góra okienka.
 */
public class Collision
{
    private boolean kolizja=false;

    /**
     * Metoda odpowiedzialna za sprawdzanie kolizji postaci z podłożem oraz sufitem.
     * @param y Aktualne położenie pionowe postaci
     * @return Jeżeli metoda zwróci wartość true oznacza że postać jest w kolizii z podłożem lub z górą okienka.
     */
    public boolean chceckPenguin(int y)
    {

        if(y>=778)
        {
            kolizja=true;
        }
        else if(y<=0)
        {
            kolizja=true;
        }
        return kolizja;
    }
}
