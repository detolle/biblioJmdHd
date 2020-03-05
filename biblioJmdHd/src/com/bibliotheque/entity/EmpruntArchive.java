package com.bibliotheque.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EmpruntArchive extends Emprunt {
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	private Date dateEmprunt;
	private Date dateRestitutionEff;
	
	public Utilisateur utilisateur;
	public Exemplaire exemplaire;

	public EmpruntArchive() {
	}
	public EmpruntArchive(Date dateEmprunt, Date dateRestitutionEff, Utilisateur utilisateur, Exemplaire exemplaire) {
		super();
		this.dateEmprunt = dateEmprunt;
		this.dateRestitutionEff = dateRestitutionEff;
		this.utilisateur = utilisateur;
		this.exemplaire = exemplaire;
	}
	public Date getDateEmprunt() {
		return dateEmprunt;
	}
	public void setDateEmprunt(Date dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}
	public Date getDateRestitutionEff() {
		return dateRestitutionEff;
	}
	public void setDateRestitutionEff(Date dateRestitutionEff) {
		this.dateRestitutionEff = dateRestitutionEff;
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
	@Override
	public String toString() {
		return "EmpruntArchive [dateEmprunt=" + sdf.format(dateEmprunt) + ", dateRestitutionEff=" + sdf.format(dateRestitutionEff)
				+ ", utilisateur=" + utilisateur + "\n==>" + exemplaire + "]";
	}
	
}
