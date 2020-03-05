package com.bibliotheque.ui;

import javax.swing.JOptionPane;

public class Ui {

	public static Integer saisieId(String label) {
		String strId;
		Integer id;
		do {
			strId = JOptionPane.showInputDialog(label);

			try {
				id = Integer.parseInt(strId);
				break;
			} 
			catch (Exception e) {
				//e.printStackTrace();
				JOptionPane.showMessageDialog(null, "entrer un nombre valide");
			}

		}while(true);	
		return id;
	}
	
}
