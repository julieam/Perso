package etape4;

public class Factory {
	public boolean res;

	public Factory() {
		A a = new A(new B());
		res = a.m();
		System.out.println(res);
	}
	
	public boolean isRes(){
		return res;
	}
	public void setRes(boolean res){
		this.res=res;
	}

}
