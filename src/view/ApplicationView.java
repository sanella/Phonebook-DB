package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import model.Contact;
import controller.ApplicationController;
/**
 * 
 * @author Sanela
 *
 */

public class ApplicationView extends Main {

	/**
	 * Prikaz glavnog menija - JPanel
	 */
	public static void home() {
		JPanel content = new JPanel();
		
		JLabel greeting = new JLabel("Welcome to BitBook");
		Font greetingFont = new Font("SansSerif", Font.BOLD, 30);
		greeting.setFont(greetingFont);
		content.add(greeting);

		JButton showContacts = new JButton("Show Contacts");
		/**
		 * Anonimna klasa koja treba da prikaze sve kontakte u Phonebooku
		 */
		showContacts.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/** poziva metodu iz klase ApplicationController
				koja poziva metodu list(iz ove klase i salje joj niz COntacts)
				*/
				ApplicationController.list();
			}

		});

		JButton addContact = new JButton("Add Contact");
		addContact.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/** poziva metodu addContact iz ApplicationControllera 
				koja poziva metodu add iz ove klase*/
				ApplicationController.addContact();
			}
		});

		content.add(addContact);
		content.add(showContacts);
		replaceContent(content);
	}

	/**
	 * Meni za dodavanje novih kontakata
	 */
	public static void addContact() {

		JPanel content = new JPanel();
		content.setLayout(new FlowLayout());
		JLabel nameL = new JLabel("Name: ");
		final JTextField nameF = new JTextField(25);
		content.add(nameL);
		content.add(nameF);

		JLabel surnameL = new JLabel("Surname: ");
		final JTextField surnameF = new JTextField(25);
		content.add(surnameL);
		content.add(surnameF);

		JLabel numberL = new JLabel("Number: ");
		final JTextField numberF = new JTextField(25);
		content.add(numberL);
		content.add(numberF);

		JButton submit = new JButton("Add");
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				 * Kupi podatke koji su uneseni i salje ih u
				 * metodu create iz klase ApplicationController
				 */
				String cName = nameF.getText();
				String cSurname = surnameF.getText();
				String cNumber = numberF.getText();
				ApplicationController.create(cName, cSurname, cNumber);
			}

		});

		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				/**
				 * Vraca na glavni meni ako pritisnes dugme back
				 */
				ApplicationController.home();
			}

		});

		JPanel buttons = new JPanel();
		buttons.add(submit);
		buttons.add(back);
		content.add(buttons);
		//lijepi ovaj panel na frame
		replaceContent(content);
	}

	/**
	 * Pravi listu kontakata koji se ispisuju na maniju
	 * ima i dva JButtona add i back 
	 * @param all - prmima niz 
	 */
	public static void list(Contact[] all) {
		
		int buttonHeight = 50;
		
		JPanel content = new JPanel();
		content.setPreferredSize(new Dimension(ApplicationView.windowWidth - 70, all.length * (buttonHeight + 20)-100));
		
		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/**
				 * Vraca nas na glavni meni
				 */
				ApplicationController.home();
			}
		});
		JButton addContact = new JButton("Add Contact");
		addContact.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/**
				 * Poziva metodu koja kupi podatke o novom unesenom kontaktu
				 */
				ApplicationController.addContact();
			}
		});
		
		content.add(back);
		content.add(addContact);

		/**
		 * ako je primljeni niz prazan onda nema kontakata u 
		 * phonebooku i saljemo poruku
		 */
		if (all.length < 1) {
			JLabel message = new JLabel("You have no friends");
			Font messageFont = new Font("SansSerif", Font.BOLD, 30);
			message.setFont(messageFont);
			content.add(message);
		}

		// TODO add Add Contact button

		
		/**
		 * petlja koja prolayi kroz niz i pravi button za svaki kontakt
		 */
		for (int i = 0; i < all.length; i++) {
			JButton current = new JButton(all[i].getDisplayName());
			current.setName(Integer.toString(all[i].getId()));
			current.setPreferredSize(new Dimension(
					ApplicationView.windowWidth - 75, buttonHeight));
			current.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO redirect to contact
					JButton clicked = (JButton) (e.getSource());
					int id = Integer.parseInt(clicked.getName());
					System.out.println("Korisnik: " + id);
				}
			});
			content.add(current);
		}

		JScrollPane sp = new JScrollPane(content);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		replaceContent(sp);
	}

}
