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
import com.bibliotheque.util.EnumStatusExemplaire;

public class Test1_1 {

	public static void main(String[] args) {
		//test modif git 
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
		 * find id adherent
		 */		
		id=Ui.saisieId("Entrer l'ID de l'adhérent :");
		
		UtilisateurDAO utilisateurDAO=new UtilisateurDAO();
		adherent=(Adherent) utilisateurDAO.findByKey(id);
		
		JOptionPane.showMessageDialog(null,adherent);		
		

		/***************************************************************************/
		/** 
		 * find id employe
		 */		
		id=Ui.saisieId("Entrer l'ID de l'employé :");
		
		employe=(Employe) utilisateurDAO.findByKey(id);		
		JOptionPane.showMessageDialog(null,employe);		
		
		/***************************************************************************/
		/** 
		 *  creation emprunt sur adherent
		 */	
		try {
			listeExemplaire.get(0).setStatus(EnumStatusExemplaire.PRETE);
			exemplaireDAO.updateStatus(listeExemplaire.get(0));
			
			EmpruntEnCours empruntEnCour1=new EmpruntEnCours(sdf.parse("03/03/2020"), adherent, listeExemplaire.get(0));
			adherent.addEmpruntEnCours(empruntEnCour1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		strId="";
		for(EmpruntEnCours empruntEnCours: adherent.getEmpruntEnCours()) {
			strId+=empruntEnCours+"\n";
		}
		JOptionPane.showMessageDialog(null, adherent+"\n"+strId);

		/***************************************************************************/
		/** 
		 *  creation emprunt sur employe
		 */	
		try {
			listeExemplaire.get(1).setStatus(EnumStatusExemplaire.PRETE);
			exemplaireDAO.updateStatus(listeExemplaire.get(1));
			
			EmpruntEnCours empruntEnCour2=new EmpruntEnCours(sdf.parse("03/03/2020"), employe, listeExemplaire.get(1));
			employe.addEmpruntEnCours(empruntEnCour2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		strId="";
		for(EmpruntEnCours empruntEnCours: employe.getEmpruntEnCours()) {
			strId+=empruntEnCours+"\n";
		}
		JOptionPane.showMessageDialog(null, employe+"\n"+strId);
	
	}

}
