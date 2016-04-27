package etape2;

import org.junit.Test;

import junit.framework.Assert;

public class TestEtape2 {

	@Test
	public void testA() {
		A a = new A();
		Assert.assertEquals(true, a.m());
	}

	@Test
	public void testB() {
		B b = new B();
		Assert.assertEquals(true, b.q());
	}

	@Test
	public void testB2() {
		B2 b = new B2();
		Assert.assertEquals(true, b.q());
	}
}
