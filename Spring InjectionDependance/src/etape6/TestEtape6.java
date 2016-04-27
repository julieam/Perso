package etape6;

import org.junit.Test;

import junit.framework.Assert;

public class TestEtape6 {

	@Test
	public void testA() {
		I i = new BTestOk();
		A a = new A(i);
		Assert.assertTrue(a.m());
		I j = new BTestNonOk();
		A a2 = new A(j);
		Assert.assertFalse(a2.m());
	}

	@Test
	public void testB() {
		B b = new B();
		Assert.assertTrue(b.q());
	}

	@Test
	public void testB2() {
		B2 b = new B2();
		Assert.assertTrue(b.q());
	}

	@Test
	public void testFactory() throws Exception {
		Factory factory = new Factory();
		Assert.assertTrue(factory.isRes());

	}

}
