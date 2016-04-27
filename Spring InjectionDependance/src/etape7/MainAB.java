package etape7;

public class MainAB {

	public static void main(String[] args) {
		MainAB ab = new MainAB();
		ab.init();

	}

	public void init() {
		I i=new B();
		A a = new A(i);
		a.m();

	}

}
