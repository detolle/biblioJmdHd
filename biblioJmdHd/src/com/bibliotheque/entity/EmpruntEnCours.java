package com.bibliotheque.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EmpruntEnCours {
	protected static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	protected Date dateEmprunt;
	
	protected Utilisateur utilisateur;
	protected Exemplaire exemplaire;

	public EmpruntEnCours() {
		super();
	}
	public EmpruntEnCours(Date dateEmprunt) {
		super();
		this.dateEmprunt = dateEmprunt;
	}	
	public EmpruntEnCours(Date dateEmprunt, Utilisateur utilisateur, Exemplaire exemplaire) {
		super();
		this.dateEmprunt = dateEmprunt;
		this.utilisateur = utilisateur;
		this.exemplaire = exemplaire;
	}
	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public Exemplaire getExemplaire() {
		return exemplaire;
	}
	public void setExemplaire(Exemplaire exemplaire) {
		this.exemplaire = exemplaire;
	}
	public Date getDateEmprunt() {
		return dateEmprunt;
	}
	public void setDateEmprunt(Date dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}
	@Override
	public String toString() {
		return "EmpruntEnCours [dateEmprunt=" + sdf.format(dateEmprunt) 
				+ " utilisateur=" + utilisateur + " exemplaire="+ exemplaire + "]";
	}

}
