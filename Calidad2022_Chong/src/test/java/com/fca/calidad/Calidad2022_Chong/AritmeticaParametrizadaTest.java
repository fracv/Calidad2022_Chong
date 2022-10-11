package com.fca.calidad.Calidad2022_Chong;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(Parameterized.class)
public class AritmeticaParametrizadaTest {

	private float arg1;
	private float arg2;
	private float expected;
	
	
	public AritmeticaParametrizadaTest(float arg1, float arg2, float expected) {
		this.arg1 = arg1;
		this.arg2 = arg2;
		this.expected = expected;
	}
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
