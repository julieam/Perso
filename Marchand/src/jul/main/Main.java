package jul.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import jul.metier.marchand.Marchand;
import jul.metier.produits.Alimentaire;
import jul.metier.produits.Consommable;
import jul.metier.produits.Produits;
import jul.metier.stock.StockList;

public class Main {

	public static void main(String[] args) {
		Main main = new Main();
		main.save();
		main.recup();
	}

	private void recup() {
		File file = new File("alimentaire.ser");
		ObjectInputStream objIn = null;
		try {
			objIn = new ObjectInputStream(new FileInputStream(file));
			Alimentaire alimentaire = (Alimentaire) objIn.readObject();
			System.out.println(alimentaire.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void save() {
		// lit le nom, le type d'objet, le poids ou quantite, prix unitaire
		// si le nom="" =>sortir du programme
		// afficher le stock
		Alimentaire a = new Alimentaire("Patate", 5.5f, 2.0f);
		File fileAlimentaire = new File("alimentaire.ser");
		ObjectOutputStream obj = null;
		try {
			obj = new ObjectOutputStream(new FileOutputStream(fileAlimentaire));
			obj.writeObject(a);
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			try {
				obj.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		StockList stock = new StockList();
		InputStreamReader entree = new InputStreamReader(System.in);
		LineNumberReader resLecture = new LineNumberReader(entree);
		String nomSaisi = null;
		String typeSaisi = null;
		String poidsOuQteSaisi = null;
		String prixUnitaireSaisi = null;

		try

		{
			System.out.println("nom du produit");
			nomSaisi = resLecture.readLine();
			while (!nomSaisi.equals("")) {
				System.out.println("Est-ce que ce produit est un alimentaire? O ou N");
				typeSaisi = resLecture.readLine();
				System.out.println("Saisir poids ou quantit� souhait�");
				poidsOuQteSaisi = resLecture.readLine();
				System.out.println("Prix unitaire au kilo");
				prixUnitaireSaisi = resLecture.readLine();
				boolean bool = verifSaisi(typeSaisi);
				if (bool) {
					Produits pro = null;
					if (typeSaisi.equals("O")) {
						pro = new Alimentaire(nomSaisi, poidsOuQteSaisi, prixUnitaireSaisi);
					}
					if (typeSaisi.equals("N")) {
						pro = new Consommable(nomSaisi, poidsOuQteSaisi, prixUnitaireSaisi);
					}
					stock.add(pro);
					System.out.println("nom du produit");
					nomSaisi = resLecture.readLine();
				} else {
					System.out.println("Erreur de saisi sur le type");
				}
			}

		} catch (

		IOException e)

		{
			e.printStackTrace();
		}

		afficherStock(stock);
	}

	private boolean verifSaisi(String typeSaisi) {
		boolean b = false;
		if (typeSaisi.equals("O") || typeSaisi.equals("N")) {
			b = true;
		}
		return b;
	}

	public StockList afficherStock(StockList stock) {
		for (Produits produits : stock) {
			System.out.println(produits + ", ");
		}
		return stock;

	}

	public void init() {
		Produits banane = new Alimentaire("Banane", 3.5f, 0.99f);// on �crit
		// produits quand on veut se servir de la collection
		Produits enveloppe = new Consommable("Enveloppe", 50, 0.1f);
		System.out.println(Produits.CPT);// CPT accessible via le nom de la
		// classe
		Produits fraise = new Alimentaire("Fraise", 0.5f, 2.99f);
		Produits banane1 = new Alimentaire("Banane", 4.2f, 1.5f);
		Produits patate = new Alimentaire("Patate", 5f, 0.69f);

		StockList st = new StockList();
		st.add(banane);
		st.add(enveloppe);
		st.add(fraise);

		// Collections.sort(st);
		for (Produits produits : st) {
			System.out.println(produits);
		}

		// TreeMap<String, Produits> map = new TreeMap<String, Produits>();
		// Stock map = new Stock();
		// map.put(banane.getNom(), banane);
		// map.put(enveloppe.getNom(), enveloppe);
		// map.put(fraise.getNom(), fraise);
		//
		// if (map.get(banane1.getNom()) != null) {
		// Produits p = map.get(banane.getNom());
		// p.rajoute(banane1);
		// map.put(p.getNom(), p);
		// }
		// Set<Entry<String, Produits>> set = map.entrySet();
		// for (Entry<String, Produits> entry : set) {
		// Produits p = entry.getValue();
		// String clef = entry.getKey();
		// System.out.println(clef + " " + p);
		// }
		//
		// System.exit(0);

		Marchand apu = new Marchand();
		// apu.getStock().setLimitePrix(9f);
		apu.getStock().add(banane);
		apu.getStock().add(enveloppe);
		apu.getStock().add(fraise);
		System.out.println("la somme du stock vaut " + apu.getStock().calculSomme() + " �");
		apu.getStock().remove(fraise);
		System.out.println("la somme du stock vaut " + apu.getStock().calculSomme() + " �");
		apu.getStock().add(banane);
		apu.getStock().add(patate);
		System.out.println("la somme du stock vaut " + apu.getStock().calculSomme() + " �");
		// apu.getStock().clear();
		System.out.println("la somme du stock vaut " + apu.getStock().calculSomme() + " �");
		System.out.println(apu.getStock().indexOf(enveloppe));

		ArrayList<Produits> stock = new ArrayList<Produits>();// classe: permet
		// de faire la liste
		System.out.println(apu.getStock().contains(banane));
		System.out.println(apu.getStock().indexOf(enveloppe));
		System.out.println(banane1.equals(banane));
		System.out.println(patate.equals(banane));

		float sommeTotale = 0;
		float sommeAlimentaire = 0;
		float sommeConso = 0;
		float sommeMoy = 0;

		// exemple ArrayList<Produits> stockA = monStock.getStockFamille();//
		// cr�er une
		// liste
		// de stock � partir objet stock param�tre stock famille

		for (Produits produits : stock) {
			if (produits instanceof Alimentaire) {
				sommeAlimentaire += produits.getPrix();
			} else {
				sommeConso += produits.getPrix();
			}
		}

	}

}
