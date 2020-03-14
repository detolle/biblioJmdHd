package com.bibliotheque.dao;

import java.sql.SQLException;
import java.util.List;

import com.bibliotheque.entity.EmpruntArchive;

public interface IEmpruntArchiveDAO {
//	public EmpruntArchive findByKeyExemplaire(Integer id) throws SQLException;
//	public List<EmpruntArchive> findByKeyUtilisateur(Integer id) throws SQLException;
	public boolean insert(EmpruntArchive empruntArchive) throws SQLException;
	public boolean delete(int id) throws SQLException;
}
