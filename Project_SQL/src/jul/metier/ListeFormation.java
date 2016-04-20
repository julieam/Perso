package jul.metier;

import java.util.ArrayList;

public class ListeFormation extends ArrayList<Formation> {
	private ListeEtudiants listeEtudiants;

	public ListeFormation() {
		listeEtudiants = new ListeEtudiants();
	}

	public ListeFormation(ListeEtudiants listeEtudiants) {
		listeEtudiants = new ListeEtudiants();
	}

	public ListeEtudiants getListeEtudiants() {
		return listeEtudiants;
	}

	public void setListeEtudiants(ListeEtudiants listeEtudiants) {
		this.listeEtudiants = listeEtudiants;
	}

}
