package vdd.main;

import vdd.ascenceur.ListeAscenseur;
import vdd.personne.ListePersonne;
import vdd.personne.Personne;

public class Main {
	public static void main(String[] args) {
		Main main = new Main();
		main.init();
	}

	public ListePersonne listePersonne;
	public ListeAscenseur listeAscenseur;
	public Affichage aff;

	public boolean sortie;

	public Main() {
		listePersonne = null;
		listeAscenseur = null;
		sortie = false;
	}

	public void init() {
		// asc.start();//hérite de thread, pas besoin de l'instancier
		aff = new Affichage(this);
		aff.init();
		listePersonne = new ListePersonne();
//		for (int i = 0; i < 10; i++) {
//			listePersonne.add(new Personne());
//		}
		listeAscenseur = new ListeAscenseur(this);
		// Personne p = new Personne();
		// Asc0 asc = new Asc0(this);
		// asc.personne = p;
		// p.depart = 5;
		// p.arrive = 15;
		// listeAscenseur.add(asc);
		// Personne p1 = new Personne();
		// Asc0 asc1 = new Asc0(this);
		// asc1.personne = p1;
		// p1.depart = 10;
		// p1.arrive = 5;
		// listeAscenseur.add(asc1);
		// asc.start();
		// asc1.start();

		// for (int i = 0; i < 6; i++) {
		// Personne gens = new Personne();
		// Asc0 asc2 = new Asc0(this);
		// listeAscenseur.add(asc2);
		// asc2.personneAscenseur = gens;
		// asc2.start();
		// }
		Thread personneAl =new Thread(listePersonne);
		personneAl.start();
		

	}
}
