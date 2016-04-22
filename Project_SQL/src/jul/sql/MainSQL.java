package jul.sql;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import jul.metier.Formation;
import jul.metier.ListeFormation;

public class MainSQL {

	public static void main(String[] args) {
		MainSQL main = new MainSQL();
		main.init();
	}

	public void init() {
		// createEtudiant();
		// insertEtudiant();
		// ListeFormation listeFormation = createFormation();
		// insertFormation(listeFormation);
		selectFormation();

	}

	private void selectFormation() {
		Connection connection = null;
		ResultSet res = null;
		Statement stat = null;
		String requete = "";

		String login = "Active";
		String password = "VDDMichel";

		try {

			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String urlBDD = "jdbc:mysql://www.psyeval.fr/bddCV";
		try {

			connection = DriverManager.getConnection(urlBDD, login, password);
			stat = connection.createStatement();
			requete = "SELECT * FROM formation";
			stat.executeQuery(requete);
			res = stat.getResultSet();
			// donne les valeurs d'une requete avec une commande select,resultat
			// de la requete
			boolean encore = res.first();
			// first donne la première ligne du tableau, dis si il y a qqchose
			while (encore) {
				String id = res.getString(1);
				// donne le numero de la colonne de la database
				// récupere la valeur qui se trouve dans la colonne
				String date = res.getString("dateFormation");
				String lieu = res.getString("lieuFormation");
				String domaine = res.getString("domaineFormation");
				System.out.println(id + " " + date + " " + lieu + " " + domaine);
				encore = res.next();
				// renvoie le ligne suivante

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stat.close();
			} catch (SQLException e2) {
				e2.printStackTrace();

			}
		}

	}

	private ListeFormation createFormation() {
		ListeFormation listeFormation = new ListeFormation();
		File fLecture = new File("C:/DevFormation/GIT/Perso/Project_SQL/xml/formation.xml");
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder documentBuilder = factory.newDocumentBuilder();
			Document document = documentBuilder.parse(fLecture);// effectue
			// l'ensemble de la lecture du fichier
			Element racine = document.getDocumentElement();
			// permet de chercher les elements du document
			NodeList liste = racine.getChildNodes();
			int nbList = liste.getLength();
			for (int i = 0; i < nbList; i++) {
				if (liste.item(i).getNodeType() == Node.ELEMENT_NODE) {// ?
					Element eFormation = (Element) liste.item(i);
					NodeList lFormation = eFormation.getChildNodes();
					// recupere chaque element du fichier xml
					String date = eFormation.getAttribute("dateFormation");
					String lieu = eFormation.getAttribute("lieuFormation");
					String domaine = eFormation.getAttribute("domaineFormation");
					Formation formation = new Formation(date, lieu, domaine);
					listeFormation.add(formation);
					System.out.println(date + " " + lieu + " " + domaine);
				}
			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listeFormation;
	}

	private void insertFormation(ListeFormation listeFormation) {
		Connection connection = null;
		ResultSet res = null;
		Statement stat = null;
		String requete = "";

		String login = "root";
		String password = "";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String urlBDD = "jdbc:mysql://localhost/bddcv";
		try {
			connection = DriverManager.getConnection(urlBDD, login, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < listeFormation.size(); i++) {
			Formation formation = listeFormation.get(i);
			requete = "INSERT INTO formation" + "(dateFormation,lieuFormation,domaineFormation)" + "VALUES(" + "'"
					+ formation.getDateFormation() + "'," + "'" + formation.getLieuFormation() + "'," + "'"
					+ formation.getDomaineFormation() + "'" + ");";

			try {
				stat = connection.createStatement();
				stat.executeUpdate(requete);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					stat.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		}

	}

	private void insertEtudiant() {
		Connection connection = null;
		ResultSet res = null;
		Statement stat = null;
		String requete = "";

		String login = "root";
		String password = "";

		try {
			// nomme le driver et permet de le charger
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String urlBDD = "jdbc:mysql://localhost/bddcv";
		try {
			// 2eme thread:permet de se connecter à la base de données
			connection = DriverManager.getConnection(urlBDD, login, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// tout ça permet de se connecter à la base de données
		requete = "INSERT INTO etudiant" + "(id,nom,prenom,metier,mail)" + "VALUES (" + "'1'," + "'nom' ," + "'prenom',"
				+ "'metier'," + "'mail'" + ");";
		try {
			stat = connection.createStatement();
			stat.executeUpdate(requete);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stat.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}

	}

	private void createEtudiant() {
		Connection connection = null;
		ResultSet res = null;
		Statement stat = null;
		String requete = "";

		String login = "root";
		String password = "";

		try {
			// nomme le driver et permet de le charger
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String urlBDD = "jdbc:mysql://localhost/bddcv";
		try {
			// 2eme thread:permet de se connecter à la base de données
			connection = DriverManager.getConnection(urlBDD, login, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// tout ça permet de se connecter à la base de données
		requete = "CREATE TABLE etudiant" + "(id INT NOT NULL PRIMARY KEY," + "nom VARCHAR(30)," + "prenom VARCHAR(30),"
				+ "metier VARCHAR(30)," + "mail VARCHAR(30)" + ");";
		try {
			stat = connection.createStatement();
			stat.executeUpdate(requete);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stat.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}

	}

}
