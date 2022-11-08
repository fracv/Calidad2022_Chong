package com.fca.calidad.dbunit;

//import static org.junit.Assert.*;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class DaoEstudianteSqlLiteTest extends TestCase{
	IDatabaseConnection connection;
	DAOEstudianteSQLlite daoSQLite;
	public DaoEstudianteSqlLiteTest(String name) {
		super(name);
	}
	protected IDataSet getDataSet() throws Exception{
		return new FlatXmlDataSetBuilder().build(new File("src/resources/initDB.xml"));
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		daoSQLite = new DAOEstudianteSQLlite();
		Connection jdbcConnection;
		jdbcConnection = DriverManager.getConnection
				("jdbc:sqlite:\\Users\\fca\\Documents\\Admón. de la CS - Francisco Chong\\Calidad2022_Chong\\Calidad2022_Chong\\src\\resources\\Alumnos.db");
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
	public void tearDown(){
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

	//Insert
	@Test
	public void crearEstudianteTest() {
		 Estudiante alumno = new Estudiante("nombrePrueba","apellidoPrueba","email@Prueba","carreraPrueba");
		//Insertar en la tabla
		 int idNuevo = daoSQLite.createEstudiante(alumno);
		 //Verificar
		 int numEsperado = 4;
		 int numReal = -1;
		 try {
			 //Base de datos
			 IDataSet databaseDataSet = getConnection().createDataSet();
			 //table
			 ITable actualTable = databaseDataSet.getTable("Estudiante");
			 numReal = actualTable.getRowCount();
			 //Verificar
			 assertThat(numEsperado,is(numReal));
		 } catch(Exception e){
			 System.out.println("Error en el crearAlumnoTest");
		 }
	}
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
