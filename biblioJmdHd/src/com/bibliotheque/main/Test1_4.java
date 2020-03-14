package com.bibliotheque.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.bibliotheque.dao.mock.ExemplaireDAOMock;
import com.bibliotheque.dao.mock.UtilisateurDAOMock;
import com.bibliotheque.entity.Adherent;
import com.bibliotheque.entity.Employe;
import com.bibliotheque.entity.EmpruntEnCours;
import com.bibliotheque.entity.Exemplaire;
import com.bibliotheque.ui.Ui;
import com.bibliotheque.util.BiblioException;
import com.bibliotheque.util.EnumStatusExemplaire;

public class Test1_4 {

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
		ExemplaireDAOMock exemplaireDAO=new ExemplaireDAOMock();
		for (int i = 0; i <= 3; i++) {
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
		
		UtilisateurDAOMock utilisateurDAO=new UtilisateurDAOMock();
		adherent=(Adherent) utilisateurDAO.findByKey(id);
		
		JOptionPane.showMessageDialog(null,adherent);		
		
		/***************************************************************************/
		/** 
		 *  creation emprunt sur adherent
		 */	
		EmpruntEnCours empruntEnCour=new EmpruntEnCours();
		for(int i=0;i<=3;i++) {
System.out.println(i);			
			try {
				adherent.isConditionsPretAccepteesMock(); 
			}
			catch (BiblioException be) {
				JOptionPane.showMessageDialog(null, "Emprunt interdit\n"+be.getMessage());
				break;
			}
			
			try {
				listeExemplaire.get(i).setStatus(EnumStatusExemplaire.PRETE);
				exemplaireDAO.updateStatus(listeExemplaire.get(i));
				
				empruntEnCour=new EmpruntEnCours(sdf.parse("03/03/2020"), adherent, listeExemplaire.get(i));
				adherent.addEmpruntEnCours(empruntEnCour);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}

		strId="";
		for(EmpruntEnCours empruntEnCours: adherent.getEmpruntEnCours()) {
			strId+=empruntEnCours+"\n";
		}
		JOptionPane.showMessageDialog(null, adherent+"\n"+strId);		
		
		
	}

}
