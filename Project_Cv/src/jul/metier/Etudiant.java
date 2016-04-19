package jul.metier;

public class Etudiant {

	private String nom;
	private String prenom;
	private String metier;
	private String mail;
	private String commentaire;
	private Formation form;

	public Etudiant() {
		nom = "";
		prenom = "";
		metier = "";
		mail = "";
		commentaire = "";
		form = null;
	}

	public Etudiant(String nom, String prenom, String metier, String mail, String commentaire, Formation form) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.metier = metier;
		this.mail = mail;
		this.commentaire = commentaire;
		this.form = form;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getMetier() {
		return metier;
	}

	public void setMetier(String metier) {
		this.metier = metier;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

}
