package etape5;

public class Factory {
	public boolean res;

	public Factory() throws Exception {
		Package p = this.getClass().getPackage();
		System.out.println(p.getName());
		Class<?> c = Class.forName(p.getName() + ".B");
		I i = (I) c.newInstance();
		A a = new A(i);
		res = a.m();
	}

	public boolean isRes() {
		return res;
	}

	public void setRes(boolean res) {
		this.res = res;
	}

}
