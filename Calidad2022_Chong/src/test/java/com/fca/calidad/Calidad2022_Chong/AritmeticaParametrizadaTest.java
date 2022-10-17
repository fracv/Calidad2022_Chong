package com.fca.calidad.Calidad2022_Chong;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class AritmeticaParametrizadaTest {

	private float arg1;
	private float arg2;
	private float expected;
	private Aritmetica miCalculadora;
	
	public AritmeticaParametrizadaTest(float arg1, float arg2, float expected) {
		this.arg1 = arg1;
		this.arg2 = arg2;
		this.expected = expected;
	}
	
	//Set parameters
	@Parameters
	public static Collection<Object[]> data(){
		return Arrays.asList(new Object[][]{
			{4,2,2},						//Integers
			{6,-3,-2},						//Negative integer
			{5,5,1},						//Same number
			{5,2,2.5f},						//Decimal
			{5,-2,-2.5f},					//Negative decimal
			//{10,0,Double.POSITIVE_INFINITY},//Infinity
			//{0,0,Double.NaN}				//Not a number
		});
	}
	
	@Before
	public void setUp() throws Exception {
		//Create new object
		miCalculadora = new Aritmetica();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void divisionTest() {
		//Using HamCrest
		//Exercise code to run and test
		float resEjecucion = miCalculadora.division(this.arg1, arg2);
		//Verify
		assertThat(this.expected, is(resEjecucion));
	}

}
