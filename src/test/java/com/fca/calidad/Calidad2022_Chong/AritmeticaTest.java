package com.fca.calidad.Calidad2022_Chong;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.util.*;

public class AritmeticaTest {
	private Aritmetica calculadora;
	
	@Before
	public void setUp() throws Exception {
		System.out.println("Este es el before");
		calculadora = new Aritmetica();
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Este es el after");
	}

	@Test
	public void sumaTest() {
		System.out.println("Este es el sumaTest");
		// Inicializar
		double resultadoEsperado = 4; // resultado esperado 2+2
		double resultadoEjecucion = 0;
		
		// Ejercicio del código
		resultadoEjecucion = calculadora.suma(2, 2);
		
		// Verificar
		assertThat(resultadoEsperado, is(resultadoEjecucion));
	}
	
	@Test
	public void restaTest() {
		System.out.println("Este es el restaTest");
		// Inicializar
		double resultadoEsperado = 3; // resultado esperado 5-2
		double resultadoEjecucion = 0;
		
		// Ejercicio del código
		resultadoEjecucion = calculadora.resta(5, 2);
		
		// Verificar
		assertThat(resultadoEsperado, is(resultadoEjecucion));
	}
	
	@Test
	public void multiplicacionTest() {
		System.out.println("Este es el multiplicacionTest");
		// Inicializar
		double resultadoEsperado = 10; // resultado esperado 5*2
		double resultadoEjecucion = 0;
		
		// Ejercicio del código
		resultadoEjecucion = calculadora.multiplicacion(5, 2);
		
		// Verificar
		assertThat(resultadoEsperado, is(resultadoEjecucion));
	}
	
	@Test
	public void divisionTest() {
		System.out.println("Este es el divisionTest");
		// Inicializar
		double resultadoEsperado = 5; // resultado esperado 10/2
		double resultadoEjecucion = 0;
		
		// Ejercicio del código
		resultadoEjecucion = calculadora.division(10, 2);
		
		// Verificar
		assertThat(resultadoEsperado, is(resultadoEjecucion));
	}
	
	@Test
	public void divisionEnteraTest() {
		System.out.println("Este es el divisionEnteraTest");
		// Inicializar
		double resultadoEsperado = 0; // resultado esperado 10/0
		double resultadoEjecucion = 0;
		
		// Ejercicio del código
		resultadoEjecucion = calculadora.divisionEntera(0, 10);
		
		// Verificar
		assertThat(resultadoEsperado, is(resultadoEjecucion));
	}
	
	@Test (expected = ArithmeticException.class)
	public void divisionEntre0Test() {
		System.out.println("Este es el divisionEnteraEntre0Test");
		// Inicializar
		double resultadoEsperado = Double.POSITIVE_INFINITY; // resultado esperado 10/0
		double resultadoEjecucion = 0;
		
		// Ejercicio del código
		resultadoEjecucion = calculadora.divisionEntera(10, 0);
		
		// Verificar
		assertThat(resultadoEsperado, is(resultadoEjecucion));
	}

}
