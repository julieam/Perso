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
		main.createEtudiant();
		main.insertEtudiant();
		main.createFormation();

	}

	private void createFormation() {
		ListeFormation listeFormation = new ListeFormation();
		File fLecture = new File("../GIT/Perso/Project_SQL/xml/formation.xml");
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
					Element eListeFormation = (Element) liste.item(i);
					NodeList lFormation = eListeFormation.getChildNodes();

					// recupere chaque element du fichier xml

					for (int j = 0; j < lFormation.getLength(); j++) {
						if (lFormation.item(j).getNodeType() == Node.ELEMENT_NODE) {
							Element eFormation = (Element) lFormation.item(j);
							String date = eFormation.getAttribute("dateFormation");
							String lieu = eFormation.getAttribute("lieuFormation");
							String domaine = eFormation.getAttribute("domaineFormation");
							Formation formation = new Formation();
							listeFormation.add(formation);
						}
					}
				}

			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
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
		String urlBDD = "jdbc:mysql://localhost/BddCv";
		try {
			// 2eme thread:permet de se connecter à la base de données
			connection = DriverManager.getConnection(urlBDD, login, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// tout ça permet de se connecter à la base de données
		requete = "CREATE TABLE formation" + "(id INT NOT NULL PRIMARY KEY," + "dateFormation VARCHAR(30),"
				+ "lieuFormation VARCHAR(30)," + "domaineFormation VARCHAR(30)," + ");";
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
		String urlBDD = "jdbc:mysql://localhost/BddCv";
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
		String urlBDD = "jdbc:mysql://localhost/BddCv";
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
