package jul.metier.stock;

import java.util.ArrayList;

import jul.metier.produits.Alimentaire;
import jul.metier.produits.Consommable;
import jul.metier.produits.Produits;

public class StockList extends ArrayList<Produits> {
	public static int NUM_STOCK = 0;

	private String nom;
	private float limitePrix;
	private float mtTotalStock;

	public StockList() {
		nom = "stock" + Integer.toString(NUM_STOCK);
		setLimitePrix(0);
		mtTotalStock = 0;
		NUM_STOCK++;
	}

	public StockList(String nom) {
		nom = "stock" + Integer.toString(NUM_STOCK);
		setLimitePrix(0);
		mtTotalStock = 0;
		NUM_STOCK++;
	}

	public StockList(String nom, float limitePrix) {
		this.nom = nom;
		this.setLimitePrix(limitePrix);
		mtTotalStock = 0;
		NUM_STOCK++;

	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public float getLimitePrix() {
		return limitePrix;
	}

	public void setLimitePrix(float limitePrix) {
		this.limitePrix = Math.abs(limitePrix);
		if (limitePrix == 0) {
			this.limitePrix = Float.POSITIVE_INFINITY;
		}
	}

	@Override
	public String toString() {
		return this.nom;
	}

	public float getMtTotalStock() {
		return mtTotalStock;
	}

	public void setMtTotalStock(float mtTotalStock) {
		this.mtTotalStock = mtTotalStock;
	}

	public float calculSomme() {
		float somme = 0;
		for (int i = 0; i < this.size(); i++) {// this=super, car classe hérite
			// de la précédente, on peut même l'enlever car pas confusant
			somme += this.get(i).getPrix();
		}
		return somme;
	}

	@Override
	public void add(int index, Produits p) {
		float somme = mtTotalStock + p.getPrix();
		if (somme <= limitePrix || limitePrix == 0) {
			super.add(index, p);
			mtTotalStock = somme;
		}
	}

	@Override
	public boolean add(Produits e) {
		boolean depassePas = false;
		float somme = mtTotalStock + e.getPrix();
		if (somme <= limitePrix || limitePrix == 0) {
			if (contains(e)) {
				Produits p = get(indexOf(e));
				p.rajoute(e);
			} else {
				depassePas = super.add(e);
				if (depassePas) {
					mtTotalStock = somme;
				}
			}
		}
		return depassePas;
	}

	public Produits rechercheProduit(Produits e) {
		Produits produitTrouve = null;
		for (Produits produits : this) {
			if (produits.getNom().equals(e.getNom())) {
				if (produits instanceof Alimentaire && e instanceof Alimentaire
						|| produits instanceof Consommable && e instanceof Consommable) {
					produitTrouve = produits;
					break;
				}
			}
		}
		return produitTrouve;
	}

	@Override
	public boolean remove(Object o) {
		Produits p = (Produits) o;
		boolean enleve = false;
		float nouveauMtTot = getMtTotalStock() - p.getPrix();
		this.setMtTotalStock(nouveauMtTot);
		enleve = super.remove(p);
		return enleve;
	}

	@Override
	public Produits remove(int n) {
		Produits p = super.get(n);// permet d'instancier un produit à l'index
									// voulu
		this.setMtTotalStock(this.getMtTotalStock() - p.getPrix());
		// this permet de rappeller le montant
		// total du stock et de le comparer au prix de l'objet,
		// -= enleve le montant a l'ancienne somme, on veut que le nouvelle
		// somme soit egale à l'ancienne somme moins le produit
		return super.remove(n);
	}

	@Override
	public void clear() {
		this.setMtTotalStock(0);
		super.clear();

	}

	public float moyenne(Produits a, Produits c) {
		float moyenne = 0;
		if (a instanceof Alimentaire) {
			Alimentaire a1 = (Alimentaire) a;
			Alimentaire a2 = (Alimentaire) c;
			moyenne = (a1.getPrix() + a2.getPrix()) / (a1.getPoids() + a2.getPoids());
		}
		if (a instanceof Consommable) {
			Consommable c1 = (Consommable) a;
			Consommable c2 = (Consommable) c;
			moyenne = (c1.getPrix() + c2.getPrix()) / (c1.getQte() + c2.getQte());
		}
		return moyenne;
	}

	@Override
	public boolean contains(Object o) {
		boolean b = false;
		if (o instanceof Produits) {
			if (indexOf((Produits) o) >= 0) {
				b = true;
			}
		}
		return b;
	}

	@Override
	public int indexOf(Object o) {
		int b = -1;
		if (o instanceof Produits) {
			b = this.indexOf((Produits) o);
		}
		return b;
	}

	public int indexOf(Produits p) {
		int b = -1;
		Produits produitTrouve = null;
		for (Produits produits : this) {
			if (produits.getNom().equals(p.getNom())) {
				if (produits instanceof Alimentaire && (p instanceof Alimentaire
						|| produits instanceof Consommable && (p instanceof Consommable))) {
					b = super.indexOf(produits);
					break;
				}
			}
		}
		return b;
	}

	public boolean addOld(Produits e) {
		boolean depassePas = false;
		int index = e.getId();
		if (e.getPrix() + mtTotalStock <= limitePrix || limitePrix == 0) {
			Produits produitExiste = rechercheProduit(e);
			if (produitExiste != null) {
				depassePas = produitExiste.rajoute(e);
				// if (this.contains(e)) {
				// float moyenne = moyenne(e, super.contains(e));
				// depassePas=(e.getPrix+super.get(index
			} else {
				depassePas = super.add(e);
				if (depassePas) {// si renvoie faux, alors pas ajout a la somme.
					mtTotalStock = calculSomme();
				}
			}
		}
		return depassePas;
	}
}
