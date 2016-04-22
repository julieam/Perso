package vdd.main;

public class MainThread {

	public static void main(String[] args) {
		MainThread main = new MainThread();
		main.init();
	}

	public void init() {
		ThreadSommeil tSommeil = new ThreadSommeil(20);
		tSommeil.start();
		
	}

}
