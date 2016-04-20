package jul.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainSQL {

	public static void main(String[] args) {
		MainSQL main = new MainSQL();
		// main.createEtudiant();
		main.insertEtudiant();

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
