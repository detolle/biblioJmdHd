package com.bibliotheque.dao;

import java.sql.SQLException;
import java.util.List;

import com.bibliotheque.entity.Exemplaire;

public interface IExemplaireDAO {
	public Exemplaire findByKey(Integer id) throws SQLException;
	public List<Exemplaire> findAll() throws SQLException;
	public Boolean updateStatus(Exemplaire exemplaire) throws SQLException;
}
