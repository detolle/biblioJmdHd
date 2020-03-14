package com.bibliotheque.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import com.bibliotheque.dao.IEmpruntEnCoursDAO;
import com.bibliotheque.dao.IExemplaireDAO;
import com.bibliotheque.dao.IUtilisateurDAO;
import com.bibliotheque.dao.impl.EmpruntEnCoursDAO;
import com.bibliotheque.dao.impl.ExemplaireDAO;
import com.bibliotheque.dao.impl.UtilisateurDAO;
import com.bibliotheque.entity.Adherent;
import com.bibliotheque.entity.EmpruntEnCoursDb;
import com.bibliotheque.entity.Exemplaire;
import com.bibliotheque.entity.Utilisateur;
import com.bibliotheque.ui.Ui;
import com.bibliotheque.util.BiblioException;
import com.bibliotheque.util.ConnectionFactory;
import com.bibliotheque.util.EnumStatusExemplaire;

public class EmpruntController {

//	public static void main(String[] args) {
	public static void emprut() {
		String strId;
		Integer idu, idex, iYN;
		Utilisateur utilisateur = null;
		Exemplaire exemplaire = null;
		EmpruntEnCoursDb empruntEnCoursDb = null;
		List<EmpruntEnCoursDb> listeEmpruntEnCours = null;
		boolean okEmprunt;
		String messageErreur = "";

		/***************************************************************************/
		/**
		 * connection à la base
		 */
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection conn = connectionFactory.getConnectionSansAutoCommit("jdbc_biblio@localhost.properties");

		try {
			/***************************************************************************/
			/**
			 * boucle sur les utilisateurs
			 */
			do {
				/***************************************************************************/
				/**
				 * saisie de l'ID utilisateur
				 */
				idu = Ui.saisieId("EMPRUNT\nEntrer l'ID de l'adhérent (0 pour sortir) :");
				if (idu == 0)
					break;

				IUtilisateurDAO utilisateurDao = new UtilisateurDAO(conn);
				utilisateur = (Utilisateur) utilisateurDao.findByKey(idu);

				IEmpruntEnCoursDAO empruntEnCoursDao = new EmpruntEnCoursDAO(conn);
				IExemplaireDAO exemplaireDao = new ExemplaireDAO(conn);

				if (utilisateur instanceof Adherent) {
					/***************************************************************************/
					/**
					 * controle Adherent
					 */
					listeEmpruntEnCours = empruntEnCoursDao.findByKeyUtilisateur(idu);
					utilisateur.setEmpruntEnCoursDb(listeEmpruntEnCours);
					for (EmpruntEnCoursDb emprunt : listeEmpruntEnCours) {
						exemplaire = exemplaireDao.findByKey(emprunt.getIdExemplaire());
						emprunt.setExemplaire(exemplaire);
					}

					try {
						if (utilisateur.isConditionsPretAcceptees()) {
							okEmprunt = true;
						}
					} catch (BiblioException be) {
						JOptionPane.showMessageDialog(null, utilisateur + "\n" + be.getMessage());
						okEmprunt = false;
						continue; // on sort et revient en début de la boucle sur les utilisateurs
					}

				} else {
					okEmprunt = true;
				}

				JOptionPane.showMessageDialog(null, utilisateur + "\n" + "=> emprunt ok");

				/***************************************************************************/
				/**
				 * boucle sur les exemplaires
				 */
				do {
					try {
						if (utilisateur.isConditionsPretAcceptees()) {
							okEmprunt = true;
						}
					} catch (BiblioException be) {
						JOptionPane.showMessageDialog(null, utilisateur + "\n\n" + be.getMessage());
						okEmprunt = false;
						break; // on sort et revient en début de la boucle sur les utilisateurs
					}

					idex = Ui.saisieId("Entrer l'ID de l'exemplaire (0 pour sortir) :");
					if (idex == 0)
						break;

					exemplaire = exemplaireDao.findByKey(idex);
					empruntEnCoursDb = empruntEnCoursDao.findByKey(idex);

					if (empruntEnCoursDb != null) {
						JOptionPane.showMessageDialog(null,
								exemplaire + "\n\nExemplaire déjà emprunté par l'utilisateur "
										+ empruntEnCoursDb.getIdUtilisateur());
					} else {
						iYN = Ui.saisieON("Exemplaire disponible : " + exemplaire + "\nConfirmez-vous l'emprunt ?");
						switch (iYN) {
						case 2: // cancel
						case 1: // no
							continue;
						case 0: // yes
							break;
						default:
							continue;
						}

						/***************************************************************************/
						/**
						 * tout est ok insert empruntEnCours + update exemplaire
						 */
						empruntEnCoursDb = new EmpruntEnCoursDb(idex, idu, new Date());
						empruntEnCoursDao.insert(empruntEnCoursDb);

						exemplaire.setStatus(EnumStatusExemplaire.PRETE);
						exemplaireDao.updateStatus(exemplaire);

						utilisateur.addEmpruntEnCoursDb(empruntEnCoursDb);
						System.out.println(empruntEnCoursDb);
						System.out.println(exemplaire);
						System.out.println(utilisateur);

						// conn.rollback();;
						conn.commit();

						JOptionPane.showMessageDialog(null, exemplaire + "\nExemplaire emprunté");
					}

				} while (idex != 0);
			} while (idu != 0);

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.rollback();
					;
					conn.close();
					//System.exit(0);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
