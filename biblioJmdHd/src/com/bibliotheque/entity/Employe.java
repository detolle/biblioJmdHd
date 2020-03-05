package com.bibliotheque.entity;

import java.util.Date;

import com.bibliotheque.util.BiblioException;
import com.bibliotheque.util.EnumCategorieEmploye;

public class Employe extends Utilisateur {
	private String codeMatricule;
	private EnumCategorieEmploye categorieEmploye;

	public Employe() {

	}	
	public Employe(String nom, String prenom, Date dateNaissance, String sexe, Integer idUtilisateur, String pwd,
			String pseudonyme, String codeMatricule, EnumCategorieEmploye categorieEmploye) {
		super(nom, prenom, dateNaissance, sexe, idUtilisateur, pwd, pseudonyme);
		this.codeMatricule = codeMatricule;
		this.categorieEmploye = categorieEmploye;
	}
	public Boolean isConditionsPretAcceptees() throws BiblioException {
		return true;
	}

	@Override
	public String toString() {
		return super.toString()+" Employe [codeMatricule=" + codeMatricule + ", categorieEmploye=" + categorieEmploye + "]";
	}


	
}