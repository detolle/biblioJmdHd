package com.bibliotheque.dao;

import java.sql.SQLException;
import java.util.List;

import com.bibliotheque.entity.Utilisateur;

public interface IUtilisateurDAO {
	public Utilisateur findByKey(Integer id) throws SQLException;
	public List<Utilisateur> findAll() throws SQLException;
}
