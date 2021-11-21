package es.iestetuan.dvj;

import es.iestetuan.dvj.dao.IAlumno;
import es.iestetuan.dvj.implement.ImplementacionBBDD;
import es.iestetuan.dvj.implement.ImplementacionXML;
import es.iestetuan.dvj.vo.Alumno;

public class GestorProcesos {

	public static void main(String[] args) {
		
		IAlumno pruebaXML = new ImplementacionXML();
		IAlumno pruebaBBDD = new ImplementacionBBDD();
		Alumno alumnoPrueba = new Alumno(929, "Sergio", "Martin", "Perez");
		Alumno alumnoPruebaBBDD = new Alumno(959, "David", "Ramon", "Muñoz", null, null, 656492350);
		Alumno alumnoPruebaModificar = new Alumno(929, "Carlos", "Santiago", "Jimenez");
		
		//Coger la lista de alumnos del TXT y pasarlo al archivo XML
		pruebaXML.obtenerListaAlumnosTXT();
		pruebaXML.guardarAlumnosXML();
		
		//Pruebas implementación XML
		pruebaXML.crearAlumno(alumnoPrueba);
		pruebaXML.borrarAlumno(919);
		pruebaXML.modificarAlumno(alumnoPruebaModificar);
		
		
		//Pruebas implementacion BBDD
		pruebaBBDD.crearAlumno(alumnoPrueba);
		pruebaBBDD.crearAlumno(alumnoPruebaBBDD);
		pruebaBBDD.obtenerInformacionAlumno(929);
		pruebaBBDD.obtenerListaAlumnos();
		pruebaBBDD.borrarAlumno(959);
		pruebaBBDD.obtenerListaAlumnos();

	}

}
