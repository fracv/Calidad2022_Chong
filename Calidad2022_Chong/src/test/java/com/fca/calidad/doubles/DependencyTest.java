package com.fca.calidad.doubles;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class DependencyTest {
	Dependency dependency;
	
	@Before
	public void setUp() throws Exception {
		dependency = Mockito.mock(Dependency.class);
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void test() {
		// Verificar
		assertThat(dependency.getClassName(),is(nullValue()));
		//assertThat(dependency.getClassName(),is("Dependency"));
	}
	
	private void setBehavior() {
		when(dependency.getClassName()).thenReturn("Nombre de la clase");
	}
	
	@Test
	public void getClassNametest() {
		setBehavior();
		String resultadoEsperado = "Nombre de la clase";
		String resultadoEjecucion = dependency.getClassName();
		assertThat(resultadoEjecucion,is(resultadoEsperado));

	}
	
	private void setAddTwo() {
		when(dependency.addTwo(1)).thenReturn(3);
	}
	
	@Test
	public void addTwoTest() {
		setAddTwo();
		int resultadoEsperado = 3;
		// int resultadoEsperado2 = 4;
		int resultadoEjecucion = dependency.addTwo(1);
		// int resultadoEjecucion2 = dependency.addTwo(2);
		assertThat(resultadoEjecucion,is(resultadoEsperado));
		// assertThat(resultadoEjecucion2,is(resultadoEsperado2));
	}
	
	@Test
	public void mockArgumentTest() {
		when(dependency.addTwo(anyInt())).thenReturn(2);
		int resultadoEsperado = 2;
		int resultadoEjecucion = dependency.addTwo(40);
		assertThat(resultadoEjecucion,is(resultadoEsperado));
	}
	
//	@Test (expected = ArithmeticException.class)
//	public void exceptionTest() {
//		when(dependency.addTwo(1)).thenReturn(3);
//		int resultadoEsperado = 20;
//		resultadoEsperado = dependency.addTwo(3);
//	}
	
	@Test
	public void callRealMethod() {
		int resultadoEsperado = 90;
		int resultadoEsperado2 = 2501;
		
		when(dependency.addTwo(anyInt())).thenCallRealMethod();

		int resultadoEjecucion = dependency.addTwo(88);
		int resultadoEjecucion2 = dependency.addTwo(2499);
		
		assertThat(resultadoEjecucion,is(resultadoEsperado));
		assertThat(resultadoEjecucion2,is(resultadoEsperado2));
	}
	
	@Test
	public void mockAnswerTest() {
		when(dependency.addTwo(anyInt())).thenAnswer(
				new Answer<Integer>() {
					public Integer answer(InvocationOnMock invocation) throws Throwable{
						int arg = (Integer) invocation.getArguments()[0];
						System.out.println("Este es el argumento del thenAnswer: "+arg);
						System.out.println("Este es el argumento del thenAnswer más 2: "+(arg+2));
						return arg + 10;
					}
				}
		);
		assertThat(20,is(dependency.addTwo(10)));
		assertThat(49,is(dependency.addTwo(39)));
	}
}
