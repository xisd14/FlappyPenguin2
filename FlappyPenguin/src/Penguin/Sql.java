package Penguin;

import javax.swing.*;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Klasa odpowiedzialna za połączenie z baza danych oraz osbługe bazy danych.
 */
public class Sql
{
    public static final String DRIVE="org.sqlite.JDBC";
    //public static final String URL="jdbc:sqlite:C:\\Users\\Bartek`\\Desktop\\sqlite\\ranking.sqlite";
    public static final String URL="jdbc:sqlite:F:\\FlappyPenguin\\out\\artifacts\\FlappyPenguin_jar\\ranking.sqlite";
    private static Connection conn;
    private static Statement stat;


    /**
     * Metoda łączy z bazą danych
     * @return połączenie z bazą w rzzie wystpienia wyjątków nie jest zwaracane nic.
     */
    public static Connection connection()
    {

        try
        {
            Class.forName(DRIVE);
            conn=DriverManager.getConnection(URL);
            stat=conn.createStatement();
            System.out.println("Polaczono z baza");
            return  conn;
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,"Blad polaczenia z baza. Rekordy nie będą dodawane!");
            System.out.println("Blad polaczenia");
            return null;
        }

    }

    /**
     * Metoda odpowiedzialna za dodawanie rekordów do bazy danych.
     * @param nazwa jest to nazwa gracza, nazwa ta wpisana jest to bazy danych w kolumnie nazwa.
     * @param wynik jest to uzyskany wynik gracza, wartość wpisana jest do kolumny wynik w bazie danych.
     * @return metoda zwraca true jeżeli rekord został dodany poprawnie, false w przeciwnym przypadku.
     */
   public boolean insertRekord(String nazwa,Integer wynik)
   {
       try
       {
        PreparedStatement prepStmt=conn.prepareStatement("insert into ranking" + "(nazwa,wynik,data)" + "values" + "(?,?,CURRENT_TIMESTAMP);");
           prepStmt.setString(1,nazwa);
           prepStmt.setInt(2,wynik);
           prepStmt.execute();
       }
       catch (Exception e)
       {
           System.err.println("Blad podczas dodawania rekordu do bazy!");
           return false;
       }
           return true;
   }

    /**
     * Metoda odpowiedzialna za wyświetlenie 5 najlepszych rekordów z bazy danych.
     * @return metoda zwraca listę graczy.
     * @throws SQLException wyjątek jest wyrzucany w momencie gdy polecenie select nie zadziałało(błąd parametrów).
     */
    public List<Gracz> selectRanking() throws SQLException {
        List<Gracz> ranking =new LinkedList<Gracz>();

        try{
            ResultSet result=stat.executeQuery("select  * from ranking order by wynik desc limit 5;");
            Integer id,wynik;
            String nazwa;
            while(result.next())
            {
                id=result.getInt("ID");
                wynik=result.getInt("WYNIK");
                nazwa=result.getString("NAZWA");
                ranking.add(new Gracz(result.getInt("ID"),result.getInt("WYNIK"),result.getString("NAZWA")));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        return ranking;
    }

    /**
     * Metoda odpowiedzialna za zrywanie połączenia z bazą danych.
     */
    public void closeConnection()
    {
        try
        {
         conn.close();
        }
        catch (Exception e)
        {
            System.err.println("Blad zamkniecia polaczenia");
            e.printStackTrace();
        }
    }
}
