package jul.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;// s'ajoute avec le buildpath, prendre serveur tom4
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jul.metier.Formation;
import jul.metier.ListeFormation;

/**
 * Servlet implementation class ServletListeFormation
 */
@WebServlet("/ListeFormation")
public class ServletListeFormation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */

	private ListeFormation listeFormation;

	public ServletListeFormation() {
		super();
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	@Override
	public void init() throws ServletException {
		listeFormation = new ListeFormation();
		chargerListeFormation();
		// charge la liste des formations
	}

	public void chargerListeFormation() {
		BufferedReader reader = null;
		File file = new File("/Project_Cv/WebContent/WEB-INF/xml/formation.xml");
		try {
			reader = new BufferedReader(new FileReader(file));
			String chaine = reader.readLine();
			while (chaine != null) {
				if (chaine.contains("<Formation")) {
					String date = extraitAtt(chaine, "dateFormation='");
					String lieu = extraitAtt(chaine, "lieuFormation='");
					String domaine = extraitAtt(chaine, "domaineFormation='");
					Formation formation = new Formation(date, lieu, domaine);
					listeFormation.add(formation);
				} else if (chaine.contains("</ListeFormation")) {
					for (Formation formation : listeFormation) {
						System.out.println(formation.toString());
					}
				}
				chaine = reader.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	private String extraitAtt(String line, String ch) {
		String res = "";
		String newLine = line.replace("\"", "'");
		int deb = newLine.indexOf((ch));
		int fin = newLine.indexOf("'", deb + ch.length());
		res = newLine.substring(deb + ch.length(), fin);
		return res;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// récupération des paramètres d'utilisateurs
		// contrôler les paramètres utilisateurs
		// créer un flux de sortie
		// charger la page html à afficher
		// envoyer cette page dans le flux de sortie.
		PrintWriter out = response.getWriter();
		File file = new File("../GIT/Perso/Project_Cv/WebContent/WEB-INF/Page/pageListeFormation.html");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = reader.readLine();
		while (line != null) {
			if (line.contains("%%date%%") || line.contains("%%lieu%%") || line.contains("%%domaine%%")
					|| line.contains("%%name%%") || line.contains("%%valeur%%")) {
				affListeFormation(out);

			} else {
				out.println(line);
			}
			line = reader.readLine();

		}

	}

	private void affListeFormation(PrintWriter out) {
		for (Formation formation : listeFormation) {
			out.println("<tr><th style=\"width: 5%\">sel</th><th style=\"width: 20%\">"+formation.getDateFormation()+"</th><th style=\"width 50%\">"+formation.getLieuFormation()+"</th><th style=\"width: 30%\">"+formation.getDomaineFormation()+"</th></tr>");
			// out.println("<tr>");
			// out.println("<th style=\"width:5%\">sel</td>");
			// out.println("<th style=\"width:30%\">" +
			// formation.getDateFormation() + "</th>");
			// out.println("<th style=\"width:30%\">" +
			// formation.getLieuFormation() + "</th></tr>");
			// out.println("<th style=\"width:30%\">" +
			// formation.getDomaineFormation() + "</th></tr>");
			// out.println("</tr>");
		}

	}

	private String extraitAttHtml(String line, String ch) {
		String res = "";
		String newLine = line.replace("\"", "%");
		int deb = newLine.indexOf((ch));
		int fin = newLine.indexOf("%", deb + ch.length());
		res = newLine.substring(deb + ch.length(), fin);
		return res;
	}

}
