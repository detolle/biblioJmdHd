package com.bibliotheque.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.bibliotheque.dao.IEmpruntArchiveDAO;
import com.bibliotheque.entity.EmpruntArchive;


public class EmpruntArchiveDAO implements IEmpruntArchiveDAO {
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private Connection conn;	

	public EmpruntArchiveDAO(Connection conn) {
		super();
		this.conn = conn;
	}	

//	public EmpruntArchive findByKeyExemplaire(Integer id) throws SQLException {
//		EmpruntArchive empruntArchive=null;		
//        PreparedStatement ps=conn
//        		.prepareStatement("select * from EmpruntArchive where idExemplaire=?"); 
//        ps.setInt(1, id);
//        ResultSet rs=ps.executeQuery();
//
//        if(rs.next()) {
//        	empruntArchive=new EmpruntArchive(
//        						rs.getDate("dateEmprunt"),
//        						rs.getDate("dateRestitutionEff"),
//								rs.getInt("idUtilisateur"),
//								rs.getInt("idExemplaire"));
//        }        
//        rs.close();
//        ps.close();
//        
//		return empruntArchive;
//	}
	
//	public List<EmpruntArchive> findByKeyUtilisateur(Integer id) throws SQLException {
//		EmpruntArchive empruntArchive=null;		
//		List<EmpruntArchive> empruntArchives = new ArrayList<>();
//		
//        PreparedStatement ps=conn
//        		.prepareStatement("select * from EmpruntArchive where idUtilisateur=?"); 
//        ps.setInt(1, id);
//        ResultSet rs=ps.executeQuery();
//
//        while(rs.next()) {
//        	empruntArchive=new EmpruntArchive(
//					rs.getDate("dateEmprunt"),
//					rs.getDate("dateRestitutionEff"),
//					rs.getInt("idUtilisateur"),
//					rs.getInt("idExemplaire"));
//        	empruntArchives.add(empruntArchive);
//        }        
//        rs.close();
//        ps.close();
//        
//		return empruntArchives;
//	}
	
	public boolean insert(EmpruntArchive empruntArchive) throws SQLException {
        PreparedStatement ps=conn
        		.prepareStatement("insert into EmpruntArchive (idempruntarchive, dateemprunt, daterestitutioneff, idexemplaire, idutilisateur) values(seq_archive.nextval,?,?,?,?)"); 
        ps.setDate(1, new java.sql.Date(empruntArchive.getDateEmprunt().getTime()));
        ps.setDate(2, new java.sql.Date(empruntArchive.getDateRestitutionEff().getTime()));
        ps.setInt(3, empruntArchive.getExemplaire().getIdExemplaire());
        ps.setInt(4, empruntArchive.getUtilisateur().getIdUtilisateur());
        int nb=ps.executeUpdate();
        ps.close();
        
		return ((nb > 0) ? true : false);
	}

	public boolean delete(int id) throws SQLException {
        PreparedStatement ps=conn
        		.prepareStatement("delete EmpruntArchive where idempruntarchive=?"); 
        ps.setInt(1, id);
        int nb=ps.executeUpdate();
        ps.close();
        
		return ((nb > 0) ? true : false);
	}	
	
}
