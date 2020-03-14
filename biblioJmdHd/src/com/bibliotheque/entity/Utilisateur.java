package com.bibliotheque.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.bibliotheque.util.BiblioException;


public class Utilisateur extends Personne {
	private Integer idUtilisateur;
	private String pwd;
	private String pseudonyme;
	
//	private Emprunt theEmprunt[];
	protected List<EmpruntArchive> listeempruntArchive = new ArrayList<>();
	protected List<EmpruntEnCoursDb> listeEmpruntEnCoursDb = new ArrayList<>();
	protected List<EmpruntEnCours> listeEmpruntEnCours = new ArrayList<>();

	public Utilisateur() {
		super();
	}
	public Utilisateur(String nom, String prenom, Date dateNaissance, String sexe,
						Integer idUtilisateur, String pwd, String pseudonyme) {
		super(nom, prenom, dateNaissance, sexe);
		this.idUtilisateur = idUtilisateur;
		this.pwd = pwd;
		this.pseudonyme = pseudonyme;
	}

	public Integer nbEmpruntsEnCours() {
		return null;
	}
	public Integer getIdUtilisateur() {
		return idUtilisateur;
	}
	public void setIdUtilisateur(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getPseudonyme() {
		return pseudonyme;
	}
	public void setPseudonyme(String pseudonyme) {
		this.pseudonyme = pseudonyme;
	}	
	public List<EmpruntArchive> getEmpruntArchive() {
		return listeempruntArchive;
	}
	public void setEmpruntArchive(List<EmpruntArchive> listeempruntArchive) {
		this.listeempruntArchive = listeempruntArchive;
	}
	public List<EmpruntEnCoursDb> getEmpruntEnCoursDb() {
		return listeEmpruntEnCoursDb;
	}
	public List<EmpruntEnCours> getEmpruntEnCours() {
		return listeEmpruntEnCours;
	}
	public void setEmpruntEnCoursDb(List<EmpruntEnCoursDb> listeEmpruntEnCoursDb) {
		this.listeEmpruntEnCoursDb = listeEmpruntEnCoursDb;
	}	
	public void setEmpruntEnCours(List<EmpruntEnCours> listeEmpruntEnCours) {
		this.listeEmpruntEnCours = listeEmpruntEnCours;
	}	
	public void addEmpruntEnCoursDb(EmpruntEnCoursDb emprunt) {
		listeEmpruntEnCoursDb.add(emprunt);
	}			
	public void addEmpruntEnCours(EmpruntEnCours emprunt) {
		listeEmpruntEnCours.add(emprunt);
	}	
	public void addEmpruntArchive(EmpruntArchive emprunt) {
		listeempruntArchive.add(emprunt);
	}	
	public Boolean isConditionsPretAcceptees() throws BiblioException {
		return true;
	}
	
	public Integer existEmpruntEnCours(Integer id) {
		for(int i=0;i<listeEmpruntEnCours.size();i++) {
			if(listeEmpruntEnCours.get(i).getExemplaire().getIdExemplaire()==id) {
				return i;
			}
		}
		return -1;			
	}
	public Boolean delEmpruntEnCours(Integer id) {
		EmpruntEnCours e;
		for(Iterator<EmpruntEnCours> iterator=listeEmpruntEnCours.iterator();iterator.hasNext();) {
			e=iterator.next();
			if(e.getExemplaire().getIdExemplaire()==id) {
				iterator.remove();
				return true;
			}			
		}
		return false;		
	}	
	
	@Override
	public String toString() {
		return super.toString()+" Utilisateur [idUtilisateur=" + idUtilisateur + ", pwd=" + pwd + ", pseudonyme=" + pseudonyme+ "]";
	}
	
}
