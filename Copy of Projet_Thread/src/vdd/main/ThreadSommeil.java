package vdd.main;

import java.text.DecimalFormat;

public class ThreadSommeil extends Thread {
	private long dureeDuSommeil;

	public ThreadSommeil(long dureeDuSommeil) {
		this.dureeDuSommeil = dureeDuSommeil;
	}

	@Override
	public void run() {
		ThreadReveil reveil = new ThreadReveil();
		reveil.start();
		long t = System.nanoTime();
		for (int i = 0; i < 2; i++) {
			synchronized (reveil) {
				try {
					reveil.wait();
				} catch (InterruptedException e) {
				}
				System.out.println("J'ai dormi " + difToString(t, System.nanoTime()));
				t = System.nanoTime();
			}
		}
		reveil.setFin(true);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("arrêt du sommeil");
		
	}

	private String difToString(long avant, long apres) {
		double dif = ((double) apres - avant) / 1000000;
		DecimalFormat df = new DecimalFormat("#####.00");
		return df.format(dif) + " ms";
	}

}
