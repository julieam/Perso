package etape7;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Factory {
	public boolean res;

	public Factory() throws Exception {
		Properties props = new Properties();
		props.load(new FileInputStream(new File("etape6/Factory.props.xml")));

		Package p = this.getClass().getPackage();
		String nomClasse = p.getName() + "." + props.getProperty("implementation_i_class");

		Class<?> d = Class.forName(nomClasse);
		I i = (I) d.newInstance();
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
