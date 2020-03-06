package com.bibliotheque.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bibliotheque.entity.Adherent;
import com.bibliotheque.entity.Employe;
import com.bibliotheque.entity.EmpruntEnCours;
import com.bibliotheque.entity.Exemplaire;
import com.bibliotheque.entity.Utilisateur;
import com.bibliotheque.util.ConnectionProvider;
import com.bibliotheque.util.EnumCategorieEmploye;

public class UtilisateurDAO {
	private static List<Utilisateur> listeUtilisateur = new ArrayList<>();
	private static List<EmpruntEnCours> listeEmprunt = new ArrayList<>();
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

//	static {		
//		try {			
//			listeUtilisateur.add( new Adherent("toto", "titi", sdf.parse("06/12/1990"), "H", 1, "toto","toto", "0102030405"));
//			listeUtilisateur.add( new Adherent("Detolle", "JeanMi", sdf.parse("06/12/1960"), "H", 2, "jmd","", "0102030405"));
//			listeUtilisateur.add( new Adherent("Detreille", "Hervé", sdf.parse("06/12/1987"), "H", 3, "dh","", "0102030405"));
//			listeUtilisateur.add( new Employe("Detreille", "Hervé", sdf.parse("06/12/1987"), "H", 4, "dh","", "012345", EnumCategorieEmploye.BIBLIOTHECAIRE));
//			listeUtilisateur.add( new Employe("Detolle", "JeanMi", sdf.parse("06/12/1960"), "H", 5, "jmd","", "012346", EnumCategorieEmploye.GESTIONNAIRE_DE_FONDS));
//			
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//	}

	public Utilisateur findByKey(Integer id) {
		Connection conn = null;
		Utilisateur utilisateur=null;
		try {
			conn = ConnectionProvider.getdataSourceInstance();
            PreparedStatement preparedStatement=conn.prepareStatement("select * \r\n" + 
										            		"from utilisateur u, adherent a, employe e\r\n" + 
										            		"where u.IDUTILISATEUR=?" + 
										            		"and u.IDUTILISATEUR=a.IDUTILISATEUR(+)" + 
										            		"and u.IDUTILISATEUR=e.IDUTILISATEUR(+)");
            preparedStatement.setInt(1, id);

//        	public Utilisateur(String nom, String prenom, Date dateNaissance, String sexe,
//					Integer idUtilisateur, String pwd, String pseudonyme)            
            ResultSet rs=preparedStatement.executeQuery();

            if(rs.next()) {
            	utilisateur=new Utilisateur();
            	
//            	joueur.setId(id);
//            	joueur.setNom(rs.getString("nom"));
//            	joueur.setPrenom(rs.getString("prenom"));
//            	joueur.setSexe(rs.getString("sexe").charAt(0));
            }
            
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

//		for (Utilisateur e : listeUtilisateur) {
//			if (e.getIdUtilisateur() == id) {
//				return e;
//			}
//		}
		return null;
	}

}
