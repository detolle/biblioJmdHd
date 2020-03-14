package com.bibliotheque.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.bibliotheque.dao.IUtilisateurDAO;
import com.bibliotheque.entity.Adherent;
import com.bibliotheque.entity.Employe;
import com.bibliotheque.entity.EmpruntEnCours;
import com.bibliotheque.entity.Utilisateur;
import com.bibliotheque.util.EnumCategorieEmploye;


public class UtilisateurDAO implements IUtilisateurDAO{
	private List<EmpruntEnCours> listeEmprunt = new ArrayList<>();
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	private Connection conn;

	public UtilisateurDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	public Utilisateur findByKey(Integer id) throws SQLException {
		Utilisateur utilisateur=null;
        PreparedStatement ps=conn
        		.prepareStatement("select * " + 
						"from utilisateur u, adherent a, employe e where u.IDUTILISATEUR=? "
						+"and u.IDUTILISATEUR=a.IDUTILISATEUR(+) and u.IDUTILISATEUR=e.IDUTILISATEUR(+)");
        ps.setInt(1, id);
        ResultSet rs=ps.executeQuery();

        if(rs.next()) {
        	if(rs.getString("categorieutilisateur").equals("ADHERENT")) {
        		utilisateur=new Adherent(rs.getString("nom"), 
        								rs.getString("prenom"), 
        								rs.getDate("dateNaissance"),
        								rs.getString("sexe"), 
        								rs.getInt("idUtilisateur"), 
        								rs.getString("pwd"), 
        								rs.getString("pseudonyme"), 
        								rs.getString("telephone"));          		
        	}
        	else {
        		utilisateur=new Employe(rs.getString("nom"), 
										rs.getString("prenom"), 
										rs.getDate("dateNaissance"),
										rs.getString("sexe"), 
										rs.getInt("idUtilisateur"), 
										rs.getString("pwd"), 
										rs.getString("pseudonyme"),
										rs.getString("codeMatricule"),
										EnumCategorieEmploye.valueOf(rs.getString("categorieEmploye")));
        	}            
        }            
        rs.close();
        ps.close();
        
		return utilisateur;
	}
	
	public List<Utilisateur> findAll() throws SQLException {
		List<Utilisateur> utilisateurs = new ArrayList<>();
		Utilisateur utilisateur=null;
		
        PreparedStatement ps=conn
        		.prepareStatement("select * " + 
						"from utilisateur u, adherent a, employe e where "
						+"and u.IDUTILISATEUR=a.IDUTILISATEUR(+) and u.IDUTILISATEUR=e.IDUTILISATEUR(+)");
        //ps.setInt(1, id);
        ResultSet rs=ps.executeQuery();

        while(rs.next()) {
        	if(rs.getString("categorieutilisateur").equals("ADHERENT")) {
        		utilisateur=new Adherent(rs.getString("nom"), 
        								rs.getString("prenom"), 
        								rs.getDate("dateNaissance"),
        								rs.getString("sexe"), 
        								rs.getInt("idUtilisateur"), 
        								rs.getString("pwd"), 
        								rs.getString("pseudonyme"), 
        								rs.getString("telephone"));          		
        	}
        	else {
        		utilisateur=new Employe(rs.getString("nom"), 
										rs.getString("prenom"), 
										rs.getDate("dateNaissance"),
										rs.getString("sexe"), 
										rs.getInt("idUtilisateur"), 
										rs.getString("pwd"), 
										rs.getString("pseudonyme"),
										rs.getString("codeMatricule"),
										EnumCategorieEmploye.valueOf(rs.getString("categorieEmploye")));
        	}            
        	utilisateurs.add(utilisateur);
        }            
        rs.close();
        ps.close();
        
		return utilisateurs;
	}
}
