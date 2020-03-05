package com.bibliotheque.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bibliotheque.entity.Adherent;
import com.bibliotheque.entity.Employe;
import com.bibliotheque.entity.EmpruntEnCours;
import com.bibliotheque.entity.Exemplaire;
import com.bibliotheque.entity.Utilisateur;
import com.bibliotheque.util.EnumCategorieEmploye;

public class UtilisateurDAO {
	private static List<Utilisateur> listeUtilisateur = new ArrayList<>();
	private static List<EmpruntEnCours> listeEmprunt = new ArrayList<>();
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	static {		
		try {			
			listeUtilisateur.add( new Adherent("toto", "titi", sdf.parse("06/12/1990"), "H", 1, "toto","toto", "0102030405"));
			listeUtilisateur.add( new Adherent("Detolle", "JeanMi", sdf.parse("06/12/1960"), "H", 2, "jmd","", "0102030405"));
			listeUtilisateur.add( new Adherent("Detreille", "Hervé", sdf.parse("06/12/1987"), "H", 3, "dh","", "0102030405"));
			listeUtilisateur.add( new Employe("Detreille", "Hervé", sdf.parse("06/12/1987"), "H", 4, "dh","", "012345", EnumCategorieEmploye.BIBLIOTHECAIRE));
			listeUtilisateur.add( new Employe("Detolle", "JeanMi", sdf.parse("06/12/1960"), "H", 5, "jmd","", "012346", EnumCategorieEmploye.GESTIONNAIRE_DE_FONDS));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Utilisateur findByKey(Integer id) {
		for(Utilisateur e : listeUtilisateur) {
			if(e.getIdUtilisateur()==id) {
				return e;
			}
		}
		return null;
	}
	
}
