package com.bibliotheque.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Personne {
	private String nom;
	private String prenom;
	private Date dateNaissance;
	private String sexe;
	protected static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public Personne() {
	}
	public Personne(String nom, String prenom, Date dateNaissance, String sexe) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.sexe = sexe;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	@Override
	public String toString() {
		return "Personne [nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + sdf.format(dateNaissance) + ", sexe=" + sexe + "]";
	}
	
}
