package com.bibliotheque.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JOptionPane;

import com.bibliotheque.dao.IEmpruntArchiveDAO;
import com.bibliotheque.dao.IEmpruntEnCoursDAO;
import com.bibliotheque.dao.IExemplaireDAO;
import com.bibliotheque.dao.impl.EmpruntArchiveDAO;
import com.bibliotheque.dao.impl.EmpruntEnCoursDAO;
import com.bibliotheque.dao.impl.ExemplaireDAO;
import com.bibliotheque.entity.EmpruntArchive;
import com.bibliotheque.entity.EmpruntEnCoursDb;
import com.bibliotheque.entity.Exemplaire;
import com.bibliotheque.entity.Utilisateur;
import com.bibliotheque.ui.Ui;
import com.bibliotheque.util.ConnectionFactory;
import com.bibliotheque.util.EnumStatusExemplaire;

public class RetourControler {

//	public static void main(String[] args) {
	public static void retour() {
		int idex, iYN;
		Exemplaire exemplaire=null;
		Utilisateur utilisateur = null;
		EmpruntEnCoursDb empruntEnCoursDb = null;
		EmpruntArchive empruntArchive = null;
		
		/***************************************************************************/
		/**
		 * connection à la base
		 */
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection conn = connectionFactory.getConnectionSansAutoCommit("jdbc_biblio@localhost.properties");

		try {
			IEmpruntEnCoursDAO empruntEnCoursDao = new EmpruntEnCoursDAO(conn);
			IEmpruntArchiveDAO empruntArchiveDAO = new EmpruntArchiveDAO(conn);
			IExemplaireDAO exemplaireDao = new ExemplaireDAO(conn);
			
			/***************************************************************************/
			/**
			 * boucle sur les exemplaires
			 */
			do {
				/***************************************************************************/
				/**
				 * saisie de l'ID exemplaire
				 */
				idex = Ui.saisieId("RETOUR\nEntrer l'ID de l'exemplaire (0 pour sortir) :");
				if (idex == 0) break;
				
				exemplaire = exemplaireDao.findByKey(idex);
				empruntEnCoursDb = empruntEnCoursDao.findByKey(idex);
				if(empruntEnCoursDb==null) {
					JOptionPane.showMessageDialog(null, exemplaire + "\n\nExemplaire non emprunté...");
					continue;
				}
				else {
					iYN=Ui.saisieON("Exemplaire emprunté par l'utilisateur "+empruntEnCoursDb.getIdUtilisateur()
									+"\nConfirmez-vous le retour ?");
					switch (iYN) {
					case 2:	//cancel						
					case 1:	//no						
						continue;
					case 0:	//yes					
						break;
					default:
						continue;
					}
					/***************************************************************************/
					/**	tout est ok
					 *  del empruntEnCours + insert empruntArchive + update exemplaire
					 */							
					utilisateur=new Utilisateur("", "", new Date(), "", empruntEnCoursDb.getIdUtilisateur(), "", "");
//					utilisateur.setIdUtilisateur(empruntEnCoursDb.getIdUtilisateur());
					empruntArchive=new EmpruntArchive(empruntEnCoursDb.getDateEmprunt(), 
													new Date(), utilisateur, exemplaire);
					empruntArchiveDAO.insert(empruntArchive);

					empruntEnCoursDao.delete(idex);
					
					exemplaire.setStatus(EnumStatusExemplaire.DISPONIBLE);
					exemplaireDao.updateStatus(exemplaire);
System.out.println(empruntEnCoursDb);	
System.out.println(empruntArchive);				
					
					//conn.rollback();
					conn.commit();					
					JOptionPane.showMessageDialog(null, exemplaire + "\nExemplaire disponible");
				}

				
			} while (idex != 0);

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.rollback();;
					conn.close();
					//System.exit(0);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
