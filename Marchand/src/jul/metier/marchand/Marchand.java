package jul.metier.marchand;

import jul.metier.stock.StockList;

public class Marchand extends Commercant {

	private StockList stock;
	private StockList stockPrive;
	private StockList stockFamille;

	public Marchand() {
		stock = new StockList("nom");
		stockPrive = new StockList("nom");
		stockFamille = new StockList("nom");

	}

	public Marchand(float limite, float limitePrivee, float limiteFamille) {
		stock = new StockList("nom");
		stockPrive = new StockList("nom");
		stockFamille = new StockList("nom");
		stock.setLimitePrix(limite);
		stockPrive.setLimitePrix(limitePrivee);
		stockFamille.setLimitePrix(limiteFamille);

	}

	public StockList getStockPrive() {
		return stockPrive;
	}

	public void setStockPrive(StockList stockPrive) {
		this.stockPrive = stockPrive;
	}

	public StockList getStockFamille() {
		return stockFamille;
	}

	public void setStockFamille(StockList stockFamille) {
		this.stockFamille = stockFamille;
	}

	public void setStock(StockList stock) {
		this.stock = stock;
	}

	public StockList getStock() {
		return stock;
	}

}
