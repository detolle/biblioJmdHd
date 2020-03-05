//Source file: D:\\CDA\\TP_UML\\bibliotheque\\domain\\Livre.java

package com.bibliotheque.entity;

import java.util.Arrays;

/**
 * Catalogue de livres
 */
public class Livre {
	private String isbn;
	private String titre;
	private Short anneeParution;
	private Integer nbPages;

	public Exemplaire theExemplaire[];
	public Auteur theAuteur[];
	public Editeur theEditeur;
	public Theme theTheme;

	public Livre() {
	}
	public Livre(String isbn, String titre, Short anneeParution, Integer nbPages) {
		super();
		this.isbn = isbn;
		this.titre = titre;
		this.anneeParution = anneeParution;
		this.nbPages = nbPages;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public Short getAnneeParution() {
		return anneeParution;
	}
	public void setAnneeParution(Short anneeParution) {
		this.anneeParution = anneeParution;
	}
	public Integer getNbPages() {
		return nbPages;
	}
	public void setNbPages(Integer nbPages) {
		this.nbPages = nbPages;
	}
	public Exemplaire[] getTheExemplaire() {
		return theExemplaire;
	}
	public void setTheExemplaire(Exemplaire[] theExemplaire) {
		this.theExemplaire = theExemplaire;
	}
	public Auteur[] getTheAuteur() {
		return theAuteur;
	}
	public void setTheAuteur(Auteur[] theAuteur) {
		this.theAuteur = theAuteur;
	}
	public Editeur getTheEditeur() {
		return theEditeur;
	}
	public void setTheEditeur(Editeur theEditeur) {
		this.theEditeur = theEditeur;
	}
	public Theme getTheTheme() {
		return theTheme;
	}
	public void setTheTheme(Theme theTheme) {
		this.theTheme = theTheme;
	}
	@Override
	public String toString() {
		return "Livre [isbn=" + isbn + ", titre=" + titre + ", anneeParution=" + anneeParution + ", nbPages=" + nbPages + "]";
	}
	
}
