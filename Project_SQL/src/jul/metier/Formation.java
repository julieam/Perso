package jul.metier;

public class Formation {
	private String dateFormation;
	private String lieuFormation;
	private String domaineFormation;

	private ListeEtudiants listeEtudiants;

	public Formation() {
		dateFormation = "";
		lieuFormation = "";
		domaineFormation = "";
		listeEtudiants=new ListeEtudiants();
	}

	public Formation(String dateForamtion, String lieuFormation, String domaineFormation) {
		this.dateFormation = dateForamtion;
		this.lieuFormation = lieuFormation;
		this.domaineFormation = domaineFormation;
		listeEtudiants=new ListeEtudiants();
	}

	public String getDateFormation() {
		return dateFormation;
	}

	public void setDateFormation(String dateFormation) {
		this.dateFormation = dateFormation;
	}

	public String getLieuFormation() {
		return lieuFormation;
	}

	public void setLieuFormation(String lieuFormation) {
		this.lieuFormation = lieuFormation;
	}

	public String getDomaineFormation() {
		return domaineFormation;
	}

	public void setDomaineFormation(String domaineFormation) {
		this.domaineFormation = domaineFormation;
	}

	public ListeEtudiants getListeEtudiants() {
		return listeEtudiants;
	}

	public void setListeEtudiants(ListeEtudiants listeEtudiants) {
		this.listeEtudiants = listeEtudiants;
	}

}
