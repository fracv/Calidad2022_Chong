package com.fca.calidad.dbunit;


import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.dbunit.Assertion;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;
//import com.fca.calidad.curdMOCK.*;


import junit.framework.TestCase;
public class DaoEstudiantetestSqlLiteTest extends TestCase{

	
	public IDatabaseConnection connection;
	public DAOEstudianteSQLlite daoSQLite;
	
	public DaoEstudiantetestSqlLiteTest(String name)
	{
		super(name);
	}

	protected IDataSet getDataSet() throws Exception {
		// TODO Auto-generated method stub
		return new FlatXmlDataSetBuilder().build
				(new File("./src/resources/initDB.xml"));
	}
	
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		daoSQLite = new DAOEstudianteSQLlite ();
		Connection jdbcConnection;
		
		jdbcConnection = DriverManager.getConnection
				("jdbc:sqlite:.\\src\\resources\\Alumnos.db");
		
		connection = new DatabaseConnection(jdbcConnection);
		
		try {
			
			PreparedStatement preparedStatement;
			preparedStatement = jdbcConnection.prepareStatement("Delete from sqlite_sequence WHERE name = ?");
			// Set the values to match in the ? on query
			
			
			preparedStatement.setString(1, "Estudiante");
			
			Boolean result = false;

			// Return the result of connection and statement
			if (preparedStatement.executeUpdate() >= 1) {
				result = true;
			}
			preparedStatement.close();
			
			DatabaseOperation.CLEAN_INSERT.execute(connection, getDataSet());
		} catch(Exception e) {
			fail("Error in setup: " + e.getMessage());
			connection.close();
		} 
	}
	
	@After
	public void tearDown()
	{
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public IDatabaseConnection getConnection()  {
		return connection;
	}

	
	@Test
	public void testCrearEstudiante() {
		Estudiante alumno = new Estudiante ("nombrePruebaCrear","appellidoCrear","email" ,"carrera");
		
		int id = daoSQLite.createEstudiante(alumno);
		alumno.setId(id);
		
		//verify
		int numEsperado = 4; //sabemos que iniciamos con 3 y agregamos una mas
		int numReal = -1;
		try {
			IDataSet databaseDataSet = getConnection().createDataSet(); //esta es toda la base de datos
			
			ITable actualTable = databaseDataSet.getTable("estudiante"); //esta es la tabla que estamos usando
			
			numReal = actualTable.getRowCount(); //número de filas
			//verificar
			System.out.println("Estudiante con ID:"+id+" creado con éxito"+"\n");
			assertThat(numEsperado,is(numReal));			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			fail("Error in insert test: " + e.getMessage());
		}
	}

	/*@Test
	public void testCrearCompararQuery() {
		Estudiante alumno = new Estudiante ("nombrePruebaCrear","appellidoCrear","email" ,"carrera");
		
		int id = daoSQLite.createEstudiante(alumno);
		alumno.setId(id);
		
		//verify
		int numEsperado = 4; //sabemos que iniciamos con 3 y agregamos una mas
		int numReal = -1;
		try {
			IDataSet databaseDataSet = getConnection().createDataSet(); //esta es toda la base de datos
			
			ITable actualTable = databaseDataSet.getTable("estudiante"); //esta es la tabla que estamos usando
			
			numReal = actualTable.getRowCount(); //número de filas
			//verificar
			assertThat(numEsperado,is(numReal));			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			fail("Error in insert test: " + e.getMessage());
		}
	}
	
	@Test
	public void testCrearCompararTabla() {
		//<Estudiante id="3" nombre="nombre1" apellido="apellido1" email="email" carrera = "carrera"/>
		Estudiante alumno = new Estudiante ("nombre1","apellido1","email" ,"carrera");
		
		int id = daoSQLite.createEstudiante(alumno);
		alumno.setId(id);
		
		
		try {
			ITable actualTable = getConnection().createQueryTable(
	                "estudiante",
	                "SELECT * FROM estudiante"); //tabla con los resultados del query
			
			IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
	                 new File("./src/resources/insert_result.xml")); //archivo xml con los datos esperados después de insertar
			 ITable expectedTable = expectedDataSet.getTable("estudiante");
	        Assertion.assertEquals(actualTable, expectedTable);		 //comparamos las tablas
			
			
		} catch (Exception e) {
			// TODO: handle exception
			fail("Error in insert test: " + e.getMessage());
		}
	}
	
	@Test
	public void testCrearCompararTabla2() {
		//<Estudiante id="3" nombre="nombre3" apellido="apellido3" email="email" carrera = "carrera"/>
		Estudiante alumno = new Estudiante ("nombre3","apellido3","email" ,"carrera");
		
		int id = daoSQLite.createEstudiante(alumno);
		alumno.setId(id);
		
		
		try {
			ITable actualTable = getConnection().createQueryTable(
	                "estudiante",
	                "SELECT * FROM estudiante where id = 3"); //tabla con los resultados del query
			
			IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
	                 new File("./src/resources/insert2.xml")); //archivo xml con los datos esperados después de insertar
			 ITable expectedTable = expectedDataSet.getTable("estudiante");
	        Assertion.assertEquals(actualTable, expectedTable);	//comparamos las tablas
			
			
		} catch (Exception e) {
			// TODO: handle exception
			fail("Error in insert test: " + e.getMessage());
		}
	}*/
	
	@Test
	public void testBuscarEstudiante() {
		Estudiante alumno = daoSQLite.findEstudiante(0);
		
		try {
			ITable actualTable = getConnection().createQueryTable(
	                "estudiante",
	                "SELECT * FROM estudiante where id = 0"); //tabla con los resultados del query
			
			// Comparamos al alumno con la tabla actual
			System.out.println("Estudiante con ID:0 buscado con éxito"+"\n");
			assertThat(alumno.getNombre(), is(actualTable.getValue(0, "nombre")));
			assertThat(alumno.getApellido(), is(actualTable.getValue(0, "apellido")));			
			assertThat(alumno.getEmail(), is(actualTable.getValue(0, "email")));			
			assertThat(alumno.getCarrera(), is(actualTable.getValue(0, "carrera")));

		} catch (Exception e) {
			// TODO: handle exception
			fail("Error in insert test: " + e.getMessage());
		}
	}
	
	@Test
	public void testActualizarEstudiante() {
		Estudiante alumno = daoSQLite.findEstudiante(0);
		
		alumno.setEmail("emailnuevo@hola");
		daoSQLite.updateEmailEstudiante(alumno);
		
		try {
			ITable actualTable = getConnection().createQueryTable(
	                "estudiante",
	                "SELECT * FROM estudiante where id = 0"); //tabla con los resultados del query
			
			// Comparamos al alumno con la tabla actual
			System.out.println("Estudiante con ID:"+alumno.getId()+" actualizado con éxito"+"\n");
			assertThat(alumno.getNombre(), is(actualTable.getValue(0, "nombre")));
			assertThat(alumno.getApellido(), is(actualTable.getValue(0, "apellido")));			
			assertThat(alumno.getEmail(), is(actualTable.getValue(0, "email")));			
			assertThat(alumno.getCarrera(), is(actualTable.getValue(0, "carrera")));
			
		} catch (Exception e) {
			// TODO: handle exception
			fail("Error in insert test: " + e.getMessage());
		}
	}
	
	@Test
	public void testEliminarEstudiante() {
		Estudiante alumno = daoSQLite.findEstudiante(0);
		
		daoSQLite.deleteEstudiante(alumno.getId());
		
		try {
			ITable actualTable = getConnection().createQueryTable(
	                "estudiante",
	                "SELECT * FROM estudiante where id = 0"); //tabla con los resultados del query
			
			int expectedRows = 0;
			System.out.println("Estudiante con ID:"+alumno.getId()+" eliminado con éxito"+"\n");
			assertThat(actualTable.getRowCount(), is(expectedRows));
			
		} catch (Exception e) {
			// TODO: handle exception
			fail("Error in insert test: " + e.getMessage());
		}
	}
}