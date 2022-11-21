package com.fca.calidad.doublesDAO;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class FakeAlumnoDAOTest {
	
	private FakeAlumnoDAO DAO;
	public HashMap<String, Alumno> baseDatos;
	
	@Before
	public void setUp() throws Exception {
		DAO = Mockito.mock(FakeAlumnoDAO.class);
		baseDatos = new HashMap<String, Alumno>();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void addAlumnoTest() {
		when(DAO.addAlumno(any(Alumno.class))).thenAnswer(new Answer<Boolean>() {
			public Boolean answer(InvocationOnMock invocation) throws Throwable{
				// Set behavior
				Alumno arg = (Alumno) invocation.getArguments()[0];
				
				// Save in baseDatos
				baseDatos.put("1", arg);
				//System.out.println("Size después=" + baseDatos.size() + "\n");
				return true;
			}
		});
	// Setup
	// Create a
	Alumno a = new Alumno("nombre", "id", 21, "email");
	
	// Check sizeBefore baseDatos
	int sizeBefore = baseDatos.size();
	Boolean res = DAO.addAlumno(a);
	
	// Check sizeAfter baseDatos
	int sizeAfter = baseDatos.size();
	
	// Verify
	System.out.println("Resultado addAlumno: " + res);
	assertThat(sizeAfter,is(sizeBefore+1));
	}
	
	@Test
	public void deleteAlumnoTest() {
		when(DAO.deleteAlumno(any(Alumno.class))).thenAnswer(new Answer<Boolean>() {
			public Boolean answer(InvocationOnMock invocation) throws Throwable{
				// Set behavior
				Alumno arg = (Alumno) invocation.getArguments()[0];
				
				// Delete with specific id
				baseDatos.remove(arg.getId());
				return true;
			}
		});
		// Setup
		// Create a
		Alumno a = new Alumno("nombre", "1", 21, "email");
		
		// Save in baseDatos
		baseDatos.put("1", a);
		int sizeSaved = baseDatos.size();
		//System.out.println(sizeSaved);
		// Delete in baseDatos
		Boolean res = DAO.deleteAlumno(a);
		int sizeDeleted = baseDatos.size();
		//System.out.println(sizeDeleted);

		
		// Verify
		System.out.println("Resultado deleteAlumno: " + res);
		assertThat(sizeDeleted,is(sizeSaved-1));
	}
	
	@Test
	public void updateEmailTest() {
		when(DAO.updateEmail(any(Alumno.class))).thenAnswer(new Answer<Boolean>() {
			public Boolean answer(InvocationOnMock invocation) throws Throwable{
				// Set behavior
				Alumno arg = (Alumno) invocation.getArguments()[0];
				
				// Save new in same id
				baseDatos.put(arg.getId(),arg);
				return true;
			}
		});
		// Setup
		// add to baseDatos
		Alumno a = new Alumno("nombre", "id", 21, "email");
		baseDatos.put("1", a);

		// Set new email
		String nuevoCorreo = "nuevoCorreo";
		a.setCorreo(nuevoCorreo);
		
		// Call update method
		Boolean res = DAO.updateEmail(a);
		
		// Verify
		String valorEsperado = nuevoCorreo;
		String valorEjecucion = baseDatos.get("1").getCorreo();
		System.out.println("Resultado updateEmail: " + res);
		assertThat(valorEsperado,is(valorEjecucion));
	}
	
	@Test
	public void searchAlumnoTest() {
		when(DAO.searchAlumno(anyString())).thenAnswer(new Answer<Alumno>() {
			public Alumno answer(InvocationOnMock invocation) throws Throwable{
				// Set behavior
				String id = (String) invocation.getArguments()[0];
				
				// Save new in same id
				Alumno a = baseDatos.get(id);
				return a;
			}
		});
		// Setup
		// add to baseDatos
		Alumno a = new Alumno("nombre", "1", 21, "email");
		baseDatos.put("1", a);

		// Call search method
		Alumno res = DAO.searchAlumno("1");
		
		// Verify
		String nomEsperado = res.getNombre();
		String idEsperado = res.getId();
		int edadEsperado = res.getEdad();
		String emailEsperado = res.getCorreo();

		String nomEjecucion = baseDatos.get("1").getNombre();
		String idEjecucion = baseDatos.get("1").getId();
		int edadEjecucion = baseDatos.get("1").getEdad();
		String emailEjecucion = baseDatos.get("1").getCorreo();
		
		System.out.println("Resultado searchAlumno: " + res);
		assertThat(nomEsperado,is(nomEjecucion));
		assertThat(idEsperado,is(idEjecucion));
		assertThat(edadEsperado,is(edadEjecucion));
		assertThat(emailEsperado,is(emailEjecucion));
	}
	
}
