//Source file: D:\\CDA\\TP_UML\\bibliotheque\\domain\\Adherent.java

package com.bibliotheque.entity;

import java.util.Date;

import com.bibliotheque.util.BiblioException;
import com.bibliotheque.util.EnumCategorieEmploye;

public class Adherent extends Utilisateur {
	private String telephone;
	private static Integer nbMaxPrets = 3;
	private static Integer dureeMaxPrets = 15;

	public Adherent() {
	}
	public Adherent(String nom, String prenom, Date dateNaissance, String sexe, Integer idUtilisateur, String pwd,
			String pseudonyme, String telephone) {
		super(nom, prenom, dateNaissance, sexe, idUtilisateur, pwd, pseudonyme);
		this.telephone = telephone;
	}

	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public static Integer getNbMaxPrets() {
		return nbMaxPrets;
	}
	public static void setNbMaxPrets(Integer nbMaxPrets) {
		Adherent.nbMaxPrets = nbMaxPrets;
	}
	public static Integer getDureeMaxPrets() {
		return dureeMaxPrets;
	}
	public static void setDureeMaxPrets(Integer dureeMaxPrets) {
		Adherent.dureeMaxPrets = dureeMaxPrets;
	}
	public Boolean isConditionsPretAcceptees() throws BiblioException {
		//test du max
		if(empruntEnCours.size()>=nbMaxPrets) {
			throw new BiblioException("Nombre maximun d'emprunt atteind ("+nbMaxPrets+")");
		}
		for(EmpruntEnCours empruntEnCours:empruntEnCours) {
			if(isPretEnRetard(empruntEnCours.getDateEmprunt())) {
				throw new BiblioException("emprunt en retard ("
						+empruntEnCours.getExemplaire().getIsbn()
						+" "+sdf.format(empruntEnCours.getDateEmprunt())+")");
			}
		}
		return true;
	}
	public boolean isPretEnRetard(Date dateEmprunt) {
		if(dateEmprunt!=null) {
			return ((new Date().getTime() - (dureeMaxPrets * 24 * 3600 * 1000)) > dateEmprunt.getTime());
		}
		else
			return false;
	}
//	public static boolean isPretEnRetard(Date dateEmpruntEffective, Date maintenant, int dureeMaxPret) {
//	return ((maintenant.getTime() - (dureeMaxPret * 24 * 3600 * 1000)) > dateEmpruntEffective.getTime());
//}	
	public Integer getNbRetards() {
		return empruntEnCours.size();
	}
	@Override
	public String toString() {
		return super.toString()+" Adherent [telephone=" + telephone + "]";
	}

}
