package vdd.ascenceur;

import vdd.main.Main;
import vdd.personne.Personne;

public class Asc0 extends Asc {

	public Asc0(Main manageur) {
		super(manageur);
	}

	@Override
	public void run() {
		while (!finDeTraitement) {
			recherchePersonneEnAttente();

			if (personneEstEnPositionDeDepart()) {
				deplacerAscenseurJusquaDepart();
				this.personneAscenseur.etat = Personne.ETAT_MOVE;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
			if (PersonneADeplacerJusquaArrivee()) {
				deplacerAscenseurJusquaArrivee();
				this.personneAscenseur.etat = Personne.ETAT_ARRIVE;
				this.personneAscenseur = null;// efface le pointeur mais
				// n'efface pas la personne, enleve l'association de la personne
				// avec l'ascenseur
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private boolean personneEstEnPositionDeDepart() {
		boolean depart = false;
		if (this.personneAscenseur != null && this.personneAscenseur.etat == Personne.ETAT_DEPART) {
			depart = true;
		}
		return depart;
	}

	private void deplacerAscenseurJusquaArrivee() {
		etage = personneAscenseur.depart;
		while (etage != personneAscenseur.arrive) {
			if (etage < personneAscenseur.arrive) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				progression++;
				if (progression % HAUTEUR_ETAGE == 0) {
					progression = 0;
					etage++;
				}

			} else if (etage > personneAscenseur.arrive) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				progression--;
				if (progression % HAUTEUR_ETAGE == 0) {
					progression = 0;
					etage--;
				}

			}

			main.aff.repaint();

		}

	}

	private boolean PersonneADeplacerJusquaArrivee() {
		boolean deplacement = false;
		if (this.personneAscenseur != null && this.personneAscenseur.etat == Personne.ETAT_MOVE) {
			deplacement = true;
		}
		return deplacement;
	}

	private void deplacerAscenseurJusquaDepart() {
		while (etage != this.personneAscenseur.depart) {
			if (etage > personneAscenseur.depart) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				progression--;
				if (progression % HAUTEUR_ETAGE == 0) {
					progression = 0;
					etage--;
				}
			} else if (etage < personneAscenseur.depart) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				progression++;
				if (progression % HAUTEUR_ETAGE == 0) {
					progression = 0;
					etage++;
				}
			}
			main.aff.repaint();
		}

	}

	private boolean personneEstEnAttente() {
		boolean attente = false;
		if (this.personneAscenseur.etat == Personne.ETAT_ATTENTE) {
			attente = true;

		}

		return attente;
	}

	private void recherchePersonneEnAttente() {
		synchronized (main.listePersonne) {
			for (int i = 0; i < main.listePersonne.size(); i++) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (main.listePersonne.get(i).etat == Personne.ETAT_ATTENTE) {
					this.personneAscenseur = main.listePersonne.get(i);// associe
					// la personne à l'ascenseur

					personneAscenseur.etat = Personne.ETAT_DEPART;// change
					// l'état de la personne dans l'ascenseur

					break;
				}
			}

		}

	}
}
