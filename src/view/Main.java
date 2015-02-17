package view;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JFrame;

/**
 * 
 * @author Sanela
 *
 */
public class Main {

	private static JFrame window = null;
	protected static int windowWidth = 400;
	protected static int windowHeight = 500;

	/**
	 * Ovo je JFrame na koji cemo lijepiti panele
	 */
	public static void init() {
		window = new JFrame("BitBook");
		window.setSize(windowWidth, windowHeight);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
	}

	/**
	 * Metoda koja frame cini vidljivim
	 */
	protected static void setVisible() {
		window.validate();
		window.repaint();
		window.setVisible(true);
	}

	/**
	 * Replaces the current content pane with the new one. Enabling the app to
	 * use only one JFrame Metoda koja prima panel koji postavja na Frame poziva
	 * metoda setVisible(); koja cini Frame vidljivim
	 * @param panel-koji zelimo da zalijepimo na nas frame
	 * 
	 */
	protected static void replaceContent(Container panel) {
		window.setContentPane(panel);
		setVisible();
	}

}
