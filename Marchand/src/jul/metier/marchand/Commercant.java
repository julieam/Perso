package jul.metier.marchand;

import java.io.Serializable;

import jul.metier.stock.StockList;

public class Commercant implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nomDuMarchand;
	private StockList monStock;

	public Commercant() {
		monStock = new StockList();
	}

	public String getNomDuMarchand() {
		return nomDuMarchand;
	}

	public void setNomDuMarchand(String nomDuMarchand) {
		this.nomDuMarchand = nomDuMarchand;
	}

	public StockList getMonStock() {
		return monStock;
	}

	public void setMonStock(StockList monStock) {
		this.monStock = monStock;
	}

	@Override
	public String toString() {
		return nomDuMarchand;
	}

}
