package com.bibliotheque.dao.mock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.bibliotheque.entity.Exemplaire;
import com.bibliotheque.entity.Livre;
import com.bibliotheque.util.EnumStatusExemplaire;

public class ExemplaireDAOMock {
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private static List<Exemplaire> listeExemplaire = new ArrayList<>();
	//private static List<Livre> listeLivre = new ArrayList<>();

	static {		
		try {
			listeExemplaire.add(new Exemplaire(1, sdf.parse("06/12/2018"), EnumStatusExemplaire.DISPONIBLE, "Connard le barbant") );
			listeExemplaire.add(new Exemplaire(2, sdf.parse("06/12/1991"), EnumStatusExemplaire.DISPONIBLE, "Les Misérables"));
			listeExemplaire.add(new Exemplaire(3, sdf.parse("06/12/1990"), EnumStatusExemplaire.DISPONIBLE, "Guerre et paix" ));
			listeExemplaire.add(new Exemplaire(4, sdf.parse("06/12/1985"), EnumStatusExemplaire.DISPONIBLE, "Tintin à Wuhan"));
			listeExemplaire.add(new Exemplaire(5, sdf.parse("06/12/1984"), EnumStatusExemplaire.DISPONIBLE, "Tintin et le corona"));
			listeExemplaire.add(new Exemplaire(6, sdf.parse("06/12/2002"), EnumStatusExemplaire.DISPONIBLE,"Luky Luke et les daltons"));
			listeExemplaire.add(new Exemplaire(7, sdf.parse("06/12/2013"), EnumStatusExemplaire.DISPONIBLE,",klmfmfmfkjfm"));
			listeExemplaire.add(new Exemplaire(8, sdf.parse("06/12/2008"), EnumStatusExemplaire.DISPONIBLE,"jlkjflkfjjfljflkj"));
			listeExemplaire.add(new Exemplaire(9, sdf.parse("06/12/2014"), EnumStatusExemplaire.DISPONIBLE,"jlkjf jfjljf djlj"));
			listeExemplaire.add(new Exemplaire(10, sdf.parse("06/12/2016"), EnumStatusExemplaire.DISPONIBLE,"nljfjf jjjfjf jjjf"));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}	
	
	public Exemplaire findByKey(Integer id) {
		for(Exemplaire e : listeExemplaire) {
			if(e.getIdExemplaire()==id) {
				return e;
			}
		}
		return null;
	}
	public List<Exemplaire> findAll() {
		return listeExemplaire;
	}
	
	public Boolean updateStatus(Exemplaire exemplaire) {
		for(int i=0; i<listeExemplaire.size();i++) {
			if(listeExemplaire.get(i).getIdExemplaire()==exemplaire.getIdExemplaire()) {
				listeExemplaire.get(i).setStatus(exemplaire.getStatus());
				return true;
			}
		}		
		return false;
	}
	@Override
	public String toString() {
		String str="[";
		for(int i=0; i<listeExemplaire.size();i++) {
			str+=listeExemplaire.get(i)+"\n";
		}
		str+="]";		
		return str;
	}	
	
}
