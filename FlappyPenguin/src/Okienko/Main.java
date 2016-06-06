package Okienko;

import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import Penguin.*;
import javax.swing.*;

/**
 * Klasa main, w tej klasie powoływane jest okno gry.
 */
public class Main extends JFrame
{

	public static Main window;
	DrawPenguin b1 =new DrawPenguin();

	/**
	 * Konstruktor klasy main tworzy okienko oraz powołuje element klasy DrawPenguin.
	 */
	public Main()
	{
		//set main window parameters
		setSize(1200, 900);
		setResizable(false);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setTitle("FlappyPenguin v.1.0");
		ImageIcon icon = new ImageIcon("ikona.png");
		setIconImage(icon.getImage());
		setVisible(true);
		getContentPane().add(b1);
		Color backGround = new Color(91, 193, 230);
		getContentPane().setBackground(backGround);
	}

	/**
	 * Metoda main, tworzeone jest okienko oraz łączenie się z bazą danych.
	 * @param args wartości z konsoli.
	 * @throws SQLException w rezie wystąpienia błędu z połączeniem z bazą danych.
	 */
	public static void main(String[] args) throws SQLException
	{
		window=new Main();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//łączenie z bazą danych.
		Connection connect=Sql.connection();
	}
}