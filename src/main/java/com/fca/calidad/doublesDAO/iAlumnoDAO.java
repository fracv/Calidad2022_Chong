package com.fca.calidad.doublesDAO;

public interface iAlumnoDAO {
	public Boolean addAlumno(Alumno a);
	public Boolean deleteAlumno(Alumno a);
	public Boolean updateEmail(Alumno a);
	public Alumno searchAlumno(String id);

}
