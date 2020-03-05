package com.bibliotheque.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.bibliotheque.dao.ExemplaireDAO;
import com.bibliotheque.dao.UtilisateurDAO;
import com.bibliotheque.entity.Adherent;
import com.bibliotheque.entity.Employe;
import com.bibliotheque.entity.EmpruntEnCours;
import com.bibliotheque.entity.Exemplaire;
import com.bibliotheque.ui.Ui;
import com.bibliotheque.util.BiblioException;
import com.bibliotheque.util.EnumStatusExemplaire;


public class Test1_3 {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");		
		String strId;
		Integer id;
		Exemplaire exemplaire;
		Adherent adherent;
		Employe employe;
		List<Exemplaire> listeExemplaire = new ArrayList<>();

		/***************************************************************************/
		/** 
		 * find id exemplaire
		 */

		ExemplaireDAO exemplaireDAO=new ExemplaireDAO();
		for (int i = 0; i < 2; i++) {
			id=Ui.saisieId("Entrer l'ID de l'exemplaire :");
			
			exemplaire=exemplaireDAO.findByKey(id);
			listeExemplaire.add(exemplaire);
			
			JOptionPane.showMessageDialog(null,exemplaire);		
		}

		/***************************************************************************/
		/** 
		 * find id employe
		 */		
		id=Ui.saisieId("Entrer l'ID de l'employé :");
		
		UtilisateurDAO utilisateurDAO=new UtilisateurDAO();
		employe=(Employe) utilisateurDAO.findByKey(id);		
		JOptionPane.showMessageDialog(null,employe);	
		
		/***************************************************************************/
		/** 
		 *  creation emprunt sur employe
		 */	
		try {
			listeExemplaire.get(0).setStatus(EnumStatusExemplaire.PRETE);
			exemplaireDAO.updateStatus(listeExemplaire.get(0));
			
			EmpruntEnCours empruntEnCour2=new EmpruntEnCours(sdf.parse("03/03/2019"), employe, listeExemplaire.get(0));
			employe.addEmpruntEnCours(empruntEnCour2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		

		try {
			employe.isConditionsPretAcceptees(); 
		}
		catch (BiblioException be) {
			JOptionPane.showMessageDialog(null, "Emprunt interdit\n"+be.getMessage());
		}

		strId="";
		for(EmpruntEnCours empruntEnCours: employe.getEmpruntEnCours()) {
			strId+=empruntEnCours+"\n";
		}
		JOptionPane.showMessageDialog(null, employe+"\n"+strId);		
		

	}

}
