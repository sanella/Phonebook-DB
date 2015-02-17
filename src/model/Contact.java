package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * The contacts model we are using It is both a class definition and connection
 * to the database
 * 
 * @author Sanela
 *
 */
public class Contact extends Application {

	// primary key
	private int id;
	private String name;
	private String surname;
	private String number;
	// so we don't have to write it every time
	private final static String tableName = "contacts";
/**
 * Konstruktor koji ne prima nista
 */
	public Contact() {
		this.id = -1;
		this.name = "Unknown";
		this.surname = "Unknown";
		this.number = "";
	}
	/**
	 * Konstruktor koji prima:
	 * @param id - id broj 
	 * @param name - ime kontakta
	 * @param surname - prezime kontakta
	 */
	public Contact(int id, String name, String surname){
		this.id = id;
		this.name = name;
		this.surname = surname;
	}

	/**
	 * Konstruktor koji prima:
	 * @param name - ime korisnika
	 * @param surname - prezime korisnika
	 * @param number - broj korisnika
	 */
	public Contact(String name, String surname, String number) {
		this.id = -1;
		this.name = name;
		this.surname = surname;
		this.number = number;
	}

	/**
	 * Konstruktor koji prima:
	 * @param id - broj korsinika
	 * @param name - ime korisnika
	 * @param surname - prezime korisnika
	 * @param number - broj korisnika
	 */
	public Contact(int id, String name, String surname, String number) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.number = number;
	}

	/**
	 * Retrieves data for the contact with the given ID from the database and
	 * creates a object from it
	 * 
	 * @param id
	 *            id of the contact
	 * @return a object with the given id, null if there is no such contact
	 */
	
	/**
	 * Poziva metodu koja prima id i ime tabele a vraca objekat Contact
	 * @param id - id kontakta
	 * @return vraca objekat trazenog id-a, vraca null ako nema objekta
	 */
	public static Contact find(int id) {
		ResultSet res = Application.find(id, tableName);
		try {
			int cId = res.getInt("id");
			String cName = res.getString("name");
			String cSurname = res.getString("surname");
			String cNumber = res.getString("number");
			return new Contact(cId, cName, cSurname, cNumber);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	/**
	 * Metoda koja spakuje sve vrijednosti u jedan string
	 * @return poziva metodu Application.save koja upisuje te podatke u bazu 
	 */
	public boolean save(){
		String values = String.format("(?, '%s', '%s', '%s')", this.name, this.surname, this.number);
		return Application.save(tableName, values);
	}
	
	/**
	 * 
	 * @return vraca 0 ako nema podataka u bazi
	 */
	//Metoda koja vraza niz Contacta-a
	public static Contact[] all(){
		//Metoda all iz Application prina columnNames koji moze imati bilo koje atribute
		ResultSet rs = Application.all(tableName, "id, name, surname");
		if( rs == null)
			return new Contact[0];
		LinkedList<Contact> cl = new LinkedList<Contact>();
		try {
			while(rs.next()){
				int id = rs.getInt("id");
				String cName = rs.getString("name");
				String cSurname = rs.getString("surname");
				cl.add(new Contact(id, cName, cSurname));
			}
			Contact[] all = new Contact[cl.size()];
			cl.toArray(all);
			return all;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return new Contact[0];
		}
	}

	public String getDisplayName(){
		return this.name + " " + this.surname;
	}
	
	// GETERI I SETER
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
