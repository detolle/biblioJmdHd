package com.bibliotheque.main;

import javax.swing.JOptionPane;

import com.bibliotheque.dao.UtilisateurDAO;
import com.bibliotheque.entity.Employe;
import com.bibliotheque.entity.Utilisateur;

public class EmpruntController {

	public static void main(String[] args) {
		String strId;
		Integer id;
		Utilisateur utilisateur;
		
		/***************************************************************************/
		/** 
		 * saisie de l'ID utilisateur
		 */		
		do {
			strId = JOptionPane.showInputDialog("Entrer l'ID de l'utilisateur :");

			try {
				id = Integer.parseInt(strId);
				break;
			} 
			catch (Exception e) {
				//e.printStackTrace();
				JOptionPane.showMessageDialog(null, "entrer un nombre valide");
			}

		}while(true);
		
		UtilisateurDAO utilisateurDAO=new UtilisateurDAO();
		utilisateur=(Utilisateur) utilisateurDAO.findByKey(id);
		JOptionPane.showMessageDialog(null,utilisateur);		
		
		


	}

}
