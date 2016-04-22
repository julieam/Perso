package vdd.ascenceur;

import vdd.main.Main;
import vdd.personne.Personne;

public class Asc extends Thread {

	public static int CPT = 0;
	public static long TEMPS = 10;
	public static int HAUTEUR_ETAGE=30;

	public int etage;
	public int progression;
	public boolean finDeTraitement;
	public Main main;
	public Personne personneAscenseur;

	public Asc(Main manageur) {
		super(Integer.toString(CPT));
		this.main = manageur;
		etage = 0;
		progression = 0;
		finDeTraitement = false;
		personneAscenseur = null;
		CPT++;
	}

	public String toString() {
		return getName() + " etage :" + etage + " pas :" + progression;
	}
}
