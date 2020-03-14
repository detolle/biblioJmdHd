package com.bibliotheque.dao;

import java.sql.SQLException;
import java.util.List;

import com.bibliotheque.entity.EmpruntEnCoursDb;

public interface IEmpruntEnCoursDAO {
	public EmpruntEnCoursDb findByKey(Integer id) throws SQLException;
	public List<EmpruntEnCoursDb> findByKeyUtilisateur(Integer id) throws SQLException;
	public boolean insert(EmpruntEnCoursDb empruntEnCoursDb) throws SQLException;
	public boolean delete(int id) throws SQLException;
}
