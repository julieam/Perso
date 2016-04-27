package etape1;
import org.junit.Test;

import junit.framework.Assert;

public class TestEtape1 {

	@Test
	public void test() {
		A a = new A();
		Assert.assertEquals(true,a.m());
	}

}
