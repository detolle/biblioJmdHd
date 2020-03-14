package com.bibliotheque.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import com.bibliotheque.dao.mock.ExemplaireDAOMock;
import com.bibliotheque.dao.mock.UtilisateurDAOMock;
import com.bibliotheque.entity.Adherent;
import com.bibliotheque.entity.Employe;
import com.bibliotheque.entity.EmpruntArchive;
import com.bibliotheque.entity.EmpruntEnCours;
import com.bibliotheque.entity.Exemplaire;
import com.bibliotheque.ui.Ui;
import com.bibliotheque.util.BiblioException;
import com.bibliotheque.util.EnumStatusExemplaire;

public class Test1_6 {

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
		for (int i = 0; i<3; i++) {
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
		 *  creation des 3 emprunts sur adherent
		 */	
		EmpruntEnCours empruntEnCour;
		for(int i=0;i<3;i++) {
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
		
//		strId="";
//		for(Exemplaire ex : listeExemplaire) {
//			strId+=ex+"\n";
//		}
		JOptionPane.showMessageDialog(null, "Liste des Exemplaires\n"+exemplaireDAO.toString());
		/***************************************************************************/
		/** 
		 *  retour d'un exemplaire
		 */	
		
		int posEmpruntEnCours;
		do {
			id=Ui.saisieId("Entrer l'ID de l'exemplaire rendu :");
			if((posEmpruntEnCours=adherent.existEmpruntEnCours(id))==-1) {
				JOptionPane.showMessageDialog(null, "erreur, exemplaire non trouvé dans la liste empruntée");
			}	
			else {
				exemplaire=exemplaireDAO.findByKey(id);
				exemplaire.setStatus(EnumStatusExemplaire.DISPONIBLE);
				exemplaireDAO.updateStatus(exemplaire);
				
				empruntEnCour=adherent.getEmpruntEnCours().get(posEmpruntEnCours);
				EmpruntArchive empruntArchive=new EmpruntArchive(empruntEnCour.getDateEmprunt()
										, new Date(), empruntEnCour.getUtilisateur(), empruntEnCour.getExemplaire());
				adherent.addEmpruntArchive(empruntArchive);
				adherent.delEmpruntEnCours(id);
				break;
			}
		}while(true);
	
		strId="";
		for(EmpruntEnCours empruntEnCours: adherent.getEmpruntEnCours()) {
			strId+=empruntEnCours+"\n";
		}
		for(EmpruntArchive empruntArchive: adherent.getEmpruntArchive()) {
			strId+=empruntArchive+"\n";
		}		
		JOptionPane.showMessageDialog(null, adherent+"\n"+strId);

		
		JOptionPane.showMessageDialog(null, "Liste des Exemplaires\n"+exemplaireDAO.toString());

		
	}

}
