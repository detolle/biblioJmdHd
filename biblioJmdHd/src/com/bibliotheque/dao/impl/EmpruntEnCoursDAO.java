package com.bibliotheque.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.bibliotheque.dao.IEmpruntEnCoursDAO;
import com.bibliotheque.entity.EmpruntEnCoursDb;


public class EmpruntEnCoursDAO implements IEmpruntEnCoursDAO {
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private Connection conn;	

	public EmpruntEnCoursDAO(Connection conn) {
		super();
		this.conn = conn;
	}	

	public EmpruntEnCoursDb findByKey(Integer id) throws SQLException {
		EmpruntEnCoursDb empruntEnCoursDb=null;		
        PreparedStatement ps=conn
        		.prepareStatement("select * from EmpruntEnCours where idExemplaire=?"); 
        ps.setInt(1, id);
        ResultSet rs=ps.executeQuery();

        if(rs.next()) {
        	empruntEnCoursDb=new EmpruntEnCoursDb(
								rs.getInt("idExemplaire"),
								rs.getInt("idUtilisateur"),
								rs.getDate("dateEmprunt"));
        }        
        rs.close();
        ps.close();
        
		return empruntEnCoursDb;
	}
	
	public List<EmpruntEnCoursDb> findByKeyUtilisateur(Integer id) throws SQLException {
		EmpruntEnCoursDb empruntEnCoursDb=null;
		List<EmpruntEnCoursDb> empruntEnCoursDbs = new ArrayList<>();
		
        PreparedStatement ps=conn
        		.prepareStatement("select * from EmpruntEnCours where idUtilisateur=?"); 
        ps.setInt(1, id);
        ResultSet rs=ps.executeQuery();

        while(rs.next()) {
        	empruntEnCoursDb=new EmpruntEnCoursDb(
								rs.getInt("idExemplaire"),
								rs.getInt("idUtilisateur"),
								rs.getDate("dateEmprunt"));
        	empruntEnCoursDbs.add(empruntEnCoursDb);
        }        
        rs.close();
        ps.close();
        
		return empruntEnCoursDbs;
	}
	
	public boolean insert(EmpruntEnCoursDb empruntEnCoursDb) throws SQLException {
        PreparedStatement ps=conn
        		.prepareStatement("insert into EmpruntEnCours (idExemplaire, idUtilisateur, dateEmprunt) values(?,?,?)"); 
        ps.setInt(1, empruntEnCoursDb.getIdExemplaire());
        ps.setInt(2, empruntEnCoursDb.getIdUtilisateur());
        ps.setDate(3, new java.sql.Date(empruntEnCoursDb.getDateEmprunt().getTime()));
        int nb=ps.executeUpdate();
        ps.close();
        
		return ((nb > 0) ? true : false);
	}

	public boolean delete(int id) throws SQLException {
        PreparedStatement ps=conn
        		.prepareStatement("delete EmpruntEnCours where idExemplaire=?"); 
        ps.setInt(1, id);
        int nb=ps.executeUpdate();
        ps.close();
        
		return ((nb > 0) ? true : false);
	}	
	
}
