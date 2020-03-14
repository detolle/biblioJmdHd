package com.bibliotheque.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.bibliotheque.dao.IExemplaireDAO;
import com.bibliotheque.entity.Exemplaire;
import com.bibliotheque.util.EnumStatusExemplaire;


public class ExemplaireDAO implements IExemplaireDAO {
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private Connection conn;	

	public ExemplaireDAO(Connection conn) {
		super();
		this.conn = conn;
	}	

	
	public Exemplaire findByKey(Integer id) throws SQLException {
		Exemplaire exemplaire=null;
		
        PreparedStatement ps=conn
        		.prepareStatement("select * from exemplaire where idExemplaire=?"); 
        ps.setInt(1, id);
        ResultSet rs=ps.executeQuery();

        if(rs.next()) {
//        	public Exemplaire(Integer idExemplaire, Date dateAchat, EnumStatusExemplaire status, String isbn) {
       	
        	exemplaire=new Exemplaire(
								rs.getInt("idExemplaire"),
								rs.getDate("dateAchat"),
								EnumStatusExemplaire.valueOf(rs.getString("status")),
								rs.getString("isbn")
								);
        }        
        rs.close();
        ps.close();
        
		return exemplaire;
	}
	public List<Exemplaire> findAll() throws SQLException {
		Exemplaire exemplaire=null;
		List<Exemplaire> exemplaires = new ArrayList<>();
        PreparedStatement ps=conn
        		.prepareStatement("select * from exemplaire order by idExemplaire"); 
        //ps.setInt(1, id);
        ResultSet rs=ps.executeQuery();

        while(rs.next()) {
        	exemplaire=new Exemplaire(
								rs.getInt("idExemplaire"),
								rs.getDate("dateAchat"),
								EnumStatusExemplaire.valueOf(rs.getString("status")),
								rs.getString("isbn")
								);
        	exemplaires.add(exemplaire);
        }        
        rs.close();
        ps.close();
        
		return exemplaires;
	}
	
	public Boolean updateStatus(Exemplaire exemplaire) throws SQLException {
        PreparedStatement ps=conn
        		.prepareStatement("update exemplaire set status=? where idExemplaire=?"); 
        ps.setString(1, exemplaire.getStatus().toString());
        ps.setInt(2, exemplaire.getIdExemplaire());
        ResultSet rs=ps.executeQuery();
		
		ps.close();
		return false;
	}

}
