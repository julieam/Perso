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
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import jul.metier.Etudiant;
import jul.metier.Formation;
import jul.metier.ListeEtudiants;
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

	public ServletListeFormation() {
		super();
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	@Override
	public void init() throws ServletException {

	}

	private void chargerListeEtudiants(ListeFormation listeFormation) {
		File fLecture = new File("../GIT/Perso/Project_Cv/WebContent/WEB-INF/xml/Etudiants.xml");
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder documentBuilder = factory.newDocumentBuilder();
			Document document = documentBuilder.parse(fLecture);// effectue
			// l'ensemble de la lecture du fichier
			// ligne 26 et 27 permet d'obtenir la fabrique

			Element racine = document.getDocumentElement();
			// permet de chercher les elements du document
			NodeList liste = racine.getChildNodes();
			int nbList = liste.getLength();
			for (int i = 0; i < nbList; i++) {
				if (liste.item(i).getNodeType() == Node.ELEMENT_NODE) {
					Element eFormation = (Element) liste.item(i);
					NodeList lFormation = eFormation.getChildNodes();
					String id = eFormation.getAttribute("id");
					Formation form = listeFormation.get(Integer.valueOf(id).intValue());

					// recupere chaque element du fichier xml

					for (int j = 0; j < lFormation.getLength(); j++) {
						if (lFormation.item(j).getNodeType() == Node.ELEMENT_NODE) {
							Element eEtudiant = (Element) lFormation.item(j);
							String nom = eEtudiant.getAttribute("nom");
							String prenom = eEtudiant.getAttribute("prenom");
							String metier = eEtudiant.getAttribute("metier");
							String mail = eEtudiant.getAttribute("mail");
							String commentaire = eEtudiant.getTextContent();
							Etudiant etudiant = new Etudiant(nom, prenom, metier, mail, commentaire, form);
							form.getListeEtudiants().add(etudiant);
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

	}

	public ListeFormation chargerListeFormation() {
		ListeFormation listeFormation = new ListeFormation();
		BufferedReader reader = null;
		File file = new File("C:/DevFormation/GIT/Perso/Project_Cv/WebContent/WEB-INF/xml/formation.xml");
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
		return listeFormation;
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
		HttpSession session = request.getSession();
		ListeFormation listeFormation = chargerListeFormation();
		chargerListeEtudiants(listeFormation);
		// permet de sauvegarder les données de cette servlett et de s'en servir
		// dans une autre
		session.setAttribute("listeForm", listeFormation);

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
				affListeFormation(out, line);

			} else {
				out.println(line);
			}
			line = reader.readLine();

		}

	}

	private void affListeFormation(PrintWriter out, String line) {
		ListeFormation listeFormation = new ListeFormation();
		for (int i = 0; i < listeFormation.size(); i++) {
			// out.println("<tr><th style=\"width: 5%\">sel</th><th
			// style=\"width: 20%\">" + formation.getDateFormation()
			// + "</th><th style=\"width 50%\">" + formation.getLieuFormation()
			// + "</th><th style=\"width: 30%\">"
			// + formation.getDomaineFormation() + "</th></tr>");
			Formation formation = listeFormation.get(i);
			String ligneAAfficher = line;
			ligneAAfficher = ligneAAfficher.replace("%%name%%", "boutonFormation");
			ligneAAfficher = ligneAAfficher.replace("%%valeur%%", Integer.toString(i));
			ligneAAfficher = ligneAAfficher.replace("%%date%%", formation.getDateFormation());
			ligneAAfficher = ligneAAfficher.replace("%%lieu%%", formation.getLieuFormation());
			ligneAAfficher = ligneAAfficher.replace("%%domaine%%", formation.getDomaineFormation());

			out.println(ligneAAfficher);

		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// recuperation de parametre au travers de la requete req
		HttpSession session = req.getSession();
		ListeFormation listeFormation = (ListeFormation) session.getAttribute("listeForm");

		String sIdFormation = req.getParameter("boutonFormation");
		// permet de récupérer le clic du bouton
		int idFormation = Integer.valueOf(sIdFormation).intValue();
		ListeEtudiants lstEtudiants = null;
		if (idFormation >= 0 && idFormation < listeFormation.size()) {
			Formation formation = listeFormation.get(idFormation);
			session.setAttribute("formation", formation);
			lstEtudiants = formation.getListeEtudiants();
			PrintWriter out = resp.getWriter();
			File file = new File("../GIT/Perso/Project_Cv/WebContent/WEB-INF/Page/pageListeEtudiant.html");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			while (line != null) {
				if (line.contains("%%nom%%")) {
					for (int i = 0; i < lstEtudiants.size(); i++) {
						String ligneAAfficher = line;
						ligneAAfficher = ligneAAfficher.replace("%%name%%", "boutonCV");
						ligneAAfficher = ligneAAfficher.replace("%%valeur%%", Integer.toString(i));
						ligneAAfficher = ligneAAfficher.replace("%%nom%%", lstEtudiants.get(i).getNom());
						ligneAAfficher = ligneAAfficher.replace("%%prenom%%", lstEtudiants.get(i).getPrenom());
						ligneAAfficher = ligneAAfficher.replace("%%metier%%", lstEtudiants.get(i).getMetier());
						ligneAAfficher = ligneAAfficher.replace("%%mail%%", lstEtudiants.get(i).getMail());
						out.println(ligneAAfficher);
					}
				} else {
					out.println(line);
				}
				line = reader.readLine();
			}
		} else {

		}

	}

}
