package com.bibliotheque.main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import com.bibliotheque.controller.EmpruntController;
import com.bibliotheque.controller.RetourControler;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;

public class BibliothequeMain {

	private JFrame frame;
	private JTextField textField;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BibliothequeMain window = new BibliothequeMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
		 * Create the application.
		 */
		public BibliothequeMain() {
			initialize();
		}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 480, 326);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 307, 115);
		// lblNewLabel.setIcon(new
		// ImageIcon("D:\\CDA\\TP_JAVA\\20Swing01\\src\\logo_loto.jpg"));
		lblNewLabel.setIcon(new ImageIcon("afpa.jpg"));
		frame.getContentPane().add(lblNewLabel);

//		textField = new JTextField();
//		textField.setEditable(false);
//		textField.setBounds(83, 171, 182, 20);
//		frame.getContentPane().add(textField);
//		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Menu :");
		lblNewLabel_1.setBounds(10, 155, 75, 14);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblAdresseIp = new JLabel("Bibliothèque");
		lblAdresseIp.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblAdresseIp.setBounds(200, 42, 200, 50);
		frame.getContentPane().add(lblAdresseIp);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(83, 151, 192, 22);
		frame.getContentPane().add(comboBox);

		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(comboBox.getSelectedIndex());
				switch (comboBox.getSelectedIndex()) {
				case 1:
					EmpruntController.emprut();
					break;
				case 2:
					RetourControler.retour();
					break;
				case 3:
					System.exit(0);					
					break;
				default:
					break;
				}

			}
		});
		
		comboBox.addItem("Choissez une option");	
		comboBox.addItem("Gestion de emprunts");	
		comboBox.addItem("Gestion des retours");	
		comboBox.addItem("Quitter");	
		/**************************/


	}

}
