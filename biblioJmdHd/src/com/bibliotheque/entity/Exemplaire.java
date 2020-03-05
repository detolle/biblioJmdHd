//Source file: D:\\CDA\\TP_UML\\bibliotheque\\domain\\Exemplaire.java

package com.bibliotheque.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.bibliotheque.util.EnumStatusExemplaire;

public class Exemplaire {
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private Integer idExemplaire;
	private Date dateAchat;
	private EnumStatusExemplaire status;
	private String isbn;
	
	public Livre theLivre;
	public EmpruntArchive theEmpruntArchive[];

	public Exemplaire() {
		super();		
	}
	public Exemplaire(Integer idExemplaire, Date dateAchat, EnumStatusExemplaire status, String isbn) {
		super();
		this.idExemplaire = idExemplaire;
		this.dateAchat = dateAchat;
		this.status = status;
		this.isbn = isbn;
	}
	
	public Integer getIdExemplaire() {
		return idExemplaire;
	}
	public void setIdExemplaire(Integer idExemplaire) {
		this.idExemplaire = idExemplaire;
	}
	public Date getDateAchat() {
		return dateAchat;
	}
	public void setDateAchat(Date dateAchat) {
		this.dateAchat = dateAchat;
	}
	public EnumStatusExemplaire getStatus() {
		return status;
	}
	public void setStatus(EnumStatusExemplaire status) {
		this.status = status;
	}
	public Livre getTheLivre() {
		return theLivre;
	}
	public void setTheLivre(Livre theLivre) {
		this.theLivre = theLivre;
	}
	public EmpruntArchive[] getTheEmpruntArchive() {
		return theEmpruntArchive;
	}
	public void setTheEmpruntArchive(EmpruntArchive[] theEmpruntArchive) {
		this.theEmpruntArchive = theEmpruntArchive;
	}	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	@Override
	public String toString() {
		return "Exemplaire [idExemplaire=" + idExemplaire + ", dateAchat=" + sdf.format(dateAchat) + ", status=" + status 
				+", isbn="+ isbn + "]";
	}
	
}
