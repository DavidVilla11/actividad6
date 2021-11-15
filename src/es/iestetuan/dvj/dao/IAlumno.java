package es.iestetuan.dvj.dao;

import es.iestetuan.dvj.vo.Alumno;

public interface IAlumno {

	public void crearAlumno(Alumno alumno);
	public void borrarAlumno(int idAlumno);
	public void modificarAlumno(Alumno alumno);
	public void obtenerInformacionAlumno(int idAlumno);
	public void obtenerListaAlumnos();
}
