package com.bibliotheque.entity;

import java.util.Date;

public class EmpruntEnCoursDb extends EmpruntEnCours {
	private Integer idExemplaire;
	private Integer idUtilisateur;

	public EmpruntEnCoursDb() {}
	public EmpruntEnCoursDb(Integer idExemplaire, Integer idUtilisateur, Date dateEmprunt, Utilisateur utilisateur, Exemplaire exemplaire) {
		super(dateEmprunt, utilisateur, exemplaire);
		this.idExemplaire = idExemplaire;
		this.idUtilisateur = idUtilisateur;
	}	
	public EmpruntEnCoursDb(Integer idExemplaire, Integer idUtilisateur, Date dateEmprunt) {
		super(dateEmprunt);
		this.idExemplaire = idExemplaire;
		this.idUtilisateur = idUtilisateur;
	}
	
	public Integer getIdExemplaire() {
		return idExemplaire;
	}
	public void setIdExemplaire(Integer idExemplaire) {
		this.idExemplaire = idExemplaire;
	}
	public Integer getIdUtilisateur() {
		return idUtilisateur;
	}
	public void setIdUtilisateur(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	@Override
	public String toString() {
		return super.toString()+" EmpruntEnCoursDb [idExemplaire=" + idExemplaire + ", idUtilisateur=" + idUtilisateur + "]";
	}

}
