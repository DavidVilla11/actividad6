package es.iestetuan.dvj.implement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import es.iestetuan.dvj.dao.IAlumno;
import es.iestetuan.dvj.vo.Alumno;

public class ImplementacionBBDD implements IAlumno {
	
	
	public static Connection getConexion() {
		Connection conexion = null;
        try
        {
        	Class.forName("org.mariadb.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mariadb://localhost:3306/aadd", "DVilla11", "admin");
            if (conexion != null)            
                System.out.println("Connected");           
            else          
                System.out.println("Not Connected");         
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return conexion;
		
	}

	@Override
	public void crearAlumno(Alumno alumno) {
		
		int idAlumno = alumno.getId_alumno();
		String nombre = alumno.getNombre();
		String apellido1 = alumno.getApellido1();
		String apellido2 = alumno.getApellido2();
		String nie = alumno.getNie();
		String email = alumno.getEmail();
		int telefono = alumno.getTelefono();
		
		
		try
        {
            Connection conexion =getConexion();
            String SentenciaSQL = "insert into t_alumno values(?,?,?,?,?,?,?,?)";
            PreparedStatement statement = conexion.prepareStatement(SentenciaSQL);
            statement.setInt(1, idAlumno);
            statement.setString(2, nombre);
            statement.setString(3, apellido1);
            statement.setString(4, apellido2);
            statement.setString(5, nie);
            statement.setString(6, email);
            statement.setInt(7, telefono);
            
            int x = statement.executeUpdate();
            if (x > 0)           
                System.out.println("Successfully Inserted");           
            else          
                System.out.println("Insert Failed");            
            conexion.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
		
	}

	
	public void borrarAlumno(int idAlumno) {
		
		try
        {
            Connection conexion =getConexion();
            String SentenciaSQL = "DELETE from t_alumnos WHERE id_Alumno = ?";     
            PreparedStatement statement = conexion.prepareStatement(SentenciaSQL);
            statement.setInt(1, idAlumno);
            int x = statement.executeUpdate(SentenciaSQL);
            if (x > 0)           
                System.out.println("Successfully delete");           
            else          
                System.out.println("Delete Failed");            
            conexion.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
		
	}
	
	
	public void modificarAlumno(Alumno alumno) {
		
		int idAlumno = alumno.getId_alumno();
		String emailNuevo = alumno.getEmail();
		int telefonoNuevo = alumno.getTelefono();

		try
        {
            Connection conexion =getConexion();
            String SentenciaSQL = "UPDATE t_alumnos set email = ?, AND telefono = ?, WHERE id_alumno = ?";
            PreparedStatement statement = conexion.prepareStatement(SentenciaSQL);
            statement.setString(1, emailNuevo);
            statement.setInt(2, telefonoNuevo);
            statement.setInt(3, idAlumno);
            
            int x = statement.executeUpdate();
            if (x > 0)           
                System.out.println("Successfully Inserted");           
            else          
                System.out.println("Insert Failed");            
            conexion.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
		
	}

	
	public void obtenerInformacionAlumno(int idAlumno) {
		
        try
        {
            Connection conexion =getConexion();
            String SentenciaSQL = "select * from empleados WHERE emp_no = '" + idAlumno + "'" ;
            PreparedStatement statement = conexion.prepareStatement(SentenciaSQL);
			ResultSet rs = statement.executeQuery();
			 if (rs.next())
			   {
				   int id_Alumno = rs.getInt("id_alumno");
				   String nombre = rs.getString("nombre");
				   String apellido1 = rs.getString("apellido1");
				   String apellido2 = rs.getString("apellido2");
				   String nie = rs.getString("nie");
				   String email = rs.getString("email");
				   int telefono = rs.getInt("telefono");
			       System.out.println(id_Alumno + nombre + apellido1 + apellido2 + nie + email + telefono);
			       
			    }
			else
			{
			 System.out.println("No such user id is already registered");
			}    
            conexion.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
	}

	
	public void obtenerListaAlumnos() {
		
        try
        {
            Connection conexion =getConexion();
            String SentenciaSQL = "select * from empleados" ;
            PreparedStatement stmt = conexion.prepareStatement(SentenciaSQL);
			ResultSet rs = stmt.executeQuery();
			 if (rs.next())
			   {
				   int id_Alumno = rs.getInt("id_alumno");
				   String nombre = rs.getString("nombre");
				   String apellido1 = rs.getString("apellido1");
				   String apellido2 = rs.getString("apellido2");
				   String nie = rs.getString("nie");
				   String email = rs.getString("email");
				   int telefono = rs.getInt("telefono");
			       System.out.println(id_Alumno + nombre + apellido1 + apellido2 + nie + email + telefono);
			       
			    }
			else
			{
			 System.out.println("No such user id is already registered");
			}    
            conexion.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
		
	}


}
