package es.iestetuan.dvj;

import es.iestetuan.dvj.dao.IAlumno;
import es.iestetuan.dvj.implement.ImplementacionXML;
import es.iestetuan.dvj.vo.Alumno;

public class GestorProcesos {

	public static void main(String[] args) {
		
		IAlumno prueba = new ImplementacionXML();
		Alumno alumnoPrueba = new Alumno(99, "David", "Villa", "Jimenez");
		prueba.crearAlumno(alumnoPrueba);

	}

}
