package controller;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import view.ApplicationView;
import view.Main;
import model.Application;
import model.Contact;
/**
 * @author Sanela Grcic
 *
 */

public class ApplicationController {

	/**
	 * Poziva metodu da prikaze home view (Glavni meni) tj JPanel
	 */
	public static void home(){
		//prikaz home GUI-a
		ApplicationView.home();
	}
	
	/**
	 * Poziv metode koja dodaje novi kontakt u phonebook
	 */
	public static void addContact(){
		ApplicationView.addContact();
	}
	/**
	 * Metoda koja ispisuje poruku ukoliko smo uspjesno 
	 * ili neuspjesno spasili kontakt
	 * @param name - ime novog kontakta
	 * @param surname - prezime novog kontakta
	 * @param number - broj novog kontakta
	 */
	public static void create(String name, String surname, String number){
		Contact newContact = new Contact(name, surname, number);
		if( newContact.save() == true){
			//TODO redirect to contact info
			home();
			JOptionPane.showMessageDialog(null, "Successfuly saved "+newContact.getName(), "Contact added", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "There has been a mistake", "Error saving Contact", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	/**
	 * Metoda koja uzima niz kontakata i poziva metodu list 
	 * ???
	 */
	public static void list(){
		Contact[] all = Contact.all();
		ApplicationView.list(all);
	}
	
	public static void main(String[] args) {	
		//Pokusavamo da ostvarimo konekciju sa bazom
		try {
			Application.init();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
		//Main.init je metoda koja pravi JFrame
		Main.init();
		//metoda koja poziva metodu koja daje panel na kojem je glavni meni
		home();
	}

}
