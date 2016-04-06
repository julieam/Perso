package jul.metier;

public class Voiture {
	private float reservoir;
	private float qteMaxReservoir;

	public Voiture(float qtMaxReservoir) {
		reservoir = 0;
		this.qteMaxReservoir = qtMaxReservoir;
	}

	public Voiture(float reservoir, float qteMaxReservoir) {
		this.reservoir = reservoir;
		this.qteMaxReservoir = qteMaxReservoir;
	}

	public float getReservoir() {
		return reservoir;
	}

	public void setReservoir(int reservoir) {
		this.reservoir = reservoir;
	}

	public float getQteMaxReservoir() {
		return qteMaxReservoir;
	}

	public void setQteMaxReservoir(float qteMaxReservoir) {
		this.qteMaxReservoir = qteMaxReservoir;
	}

	public void prendreEssence(EssenceInterface st) {
		reservoir = st.donnerEssenceInterface(qteMaxReservoir - reservoir);
	}

	@Override
	public String toString() {
		return getClass().getName() + " " + getReservoir();
		// getClass() de la classe String et getName()de la classe String
		// permet d'envoyer le toString lors de l'envoi quelque soit l'objet
	}

}
