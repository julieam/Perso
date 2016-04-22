package vdd.main;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class ThreadReveil extends Thread {
	private boolean fin;

	public ThreadReveil() {
		fin = false;

	}

	@Override
	public void run() {
		InputStreamReader entree = new InputStreamReader(System.in);
		LineNumberReader resLecture = new LineNumberReader(entree);

		while (!fin) {
			System.out.println("Appuyer sur Entrée");
			try {
				resLecture.readLine();
				System.out.println("Debout tout le monde");
				synchronized (this) {
					notify();
				}
			} catch (IOException e1) {
				break;
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Fin de reveil");

	}

	public boolean isFin() {
		return fin;
	}

	public void setFin(boolean fin) {
		this.fin = fin;
	}

}
