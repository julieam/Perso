package jul.metier.stock;

import java.util.ArrayList;

import jul.metier.produits.Alimentaire;
import jul.metier.produits.Produits;

public class PasUnStock {
	private ArrayList<Produits> monStock;
	private ArrayList<Produits> stockFamille;
	private ArrayList<Produits> stockPrive;

	public PasUnStock() {
		monStock = new ArrayList<Produits>();
		stockFamille = new ArrayList<Produits>();
		stockPrive = new ArrayList<Produits>();
	}

	public ArrayList<Produits> getMonStock() {
		return monStock;
	}

	public void setMonStock(ArrayList<Produits> monStock) {
		this.monStock = monStock;
	}

	public void prixPanier() {
		float prix = 0;
		float sommeAlimentaire = 0;
		float sommeConso = 0;
		float sommeTotale = 0;
		for (int i = 0; i < monStock.size(); i++) {
			System.out.println(monStock.get(i));// get méthode de ArrayList
			if (monStock.get(i) instanceof Alimentaire) {
				sommeAlimentaire += monStock.get(i).getPrix();
			} else {
				sommeConso += monStock.get(i).getPrix();
			}
		}
		sommeTotale = sommeAlimentaire + sommeConso;
		System.out.println("la somme totale vaut " + sommeTotale + " €");
		System.out.println("la somme alimentaire vaut " + sommeAlimentaire + " €");
		System.out.println("la sommedes consommables vaut " + sommeConso + " €");
	}

	public ArrayList<Produits> getStockFamille() {
		return stockFamille;
	}

	public void setStockFamille(ArrayList<Produits> stockFamille) {
		this.stockFamille = stockFamille;
	}

	public ArrayList<Produits> getStockPrive() {
		return stockPrive;
	}

	public void setStockPrive(ArrayList<Produits> stockPrive) {
		this.stockPrive = stockPrive;
	}

}
