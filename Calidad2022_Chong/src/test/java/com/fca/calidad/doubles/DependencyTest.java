package com.fca.calidad.doubles;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.ArgumentMatchers.*;

public class DependencyTest {
	Dependency dependency;
	
	@Before
	public void setUp() throws Exception {
		dependency = Mockito.mock(Dependency.class);
	}
	
	@Test
	public void test() {
		// Verificar
		assertThat(dependency.getClassName(),is(nullValue()));
		//assertThat(dependency.getClassName(),is("Dependency"));
	}
}
