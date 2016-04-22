package vdd.personne;

import java.util.ArrayList;

public class ListePersonne extends ArrayList<Personne> implements Runnable {

	public boolean sortie;

	public ListePersonne() {
		sortie = false;
	}

	public void run() {
		while (!sortie) {
			try {
				Thread.sleep((long) (Math.random() * 10000));

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (this) {
				Personne gens = new Personne();
				this.add(gens);
				if (gens.etat == Personne.ETAT_ARRIVE) {
					this.remove(gens);
				}
			}

		}
	}
}
