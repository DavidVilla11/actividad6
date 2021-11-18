package es.iestetuan.dvj.implement;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import es.iestetuan.dvj.dao.IAlumno;
import es.iestetuan.dvj.vo.Alumno;

public class ImplementacionXML implements IAlumno {

	List<Alumno> ListaAlumnos = new ArrayList<Alumno>();
	
	public List<Alumno> listaAlumnotxt(){
		String ruta = "./alumnos-dam2-inicial.txt";
		File file = new File(ruta);
		FileReader fr = null;
		BufferedReader bf = null;
		String Linea = null;
		
		boolean primeraVez = true;
		try {
			fr= new FileReader(file, StandardCharsets.UTF_8);
			bf = new BufferedReader(fr);
			
			while((Linea=bf.readLine())!=null) {
				String[] partes = Linea.split(",");
				if(primeraVez) {
					primeraVez=false;
				} else {
					if(partes.length > 3) {
						Alumno AlumnoEJ = new Alumno();
						int ID = Integer.parseInt(partes[0]);
						AlumnoEJ.setId_alumno(ID);
						AlumnoEJ.setNombre(partes[1]);
						AlumnoEJ.setApellido1(partes[2]);
						AlumnoEJ.setApellido2(partes[3]);
						ListaAlumnos.add(AlumnoEJ);
						
					} else if(partes.length <= 3) {
						Alumno AlumnoEJ = new Alumno();
						int ID = Integer.parseInt(partes[0]);
						AlumnoEJ.setId_alumno(ID);
						AlumnoEJ.setNombre(partes[1]);
						AlumnoEJ.setApellido1(partes[2]);
						ListaAlumnos.add(AlumnoEJ);
					}
				}

			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
		}
		return ListaAlumnos;
	}
	
	public void guardarUsuariosXML() {
		
		try {
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();
			
			Element eRaiz = doc.createElement("Alumnos");
			doc.appendChild(eRaiz);

			for(Alumno lista  : ListaAlumnos) {
				
				Element eAlumno = doc.createElement("Alumno");
				eRaiz.appendChild(eAlumno);
				
				Attr Attr = doc.createAttribute("id");
				eAlumno.setAttributeNode(Attr);
				Attr.setValue(String.valueOf(lista.getId_alumno()));
				
				Element eNombre = doc.createElement("Nombre");
				eAlumno.appendChild(eNombre);
				eNombre.setTextContent(lista.getNombre());
				
				Element eApellido1 = doc.createElement("Apellido1");
				eAlumno.appendChild(eApellido1);
				eApellido1.setTextContent(lista.getApellido1());
				
				Element eApellido2 = doc.createElement("Apellido2");
				eAlumno.appendChild(eApellido2);
				eApellido2.setTextContent(lista.getApellido2());
			
			}
			
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("./alumnos-dam2.xml"));
			
			
			transformer.transform(source, result);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void crearAlumno(Alumno alumno) {
		
		
		for(Alumno lista : ListaAlumnos) {
			if(lista.getId_alumno() != alumno.getId_alumno()) {
				ListaAlumnos.add(alumno);
				guardarUsuariosXML();
			}
			if(lista.getId_alumno() == alumno.getId_alumno()) {
				System.out.println("Ya hay un usuario con ese ID de alumno");
			}
			
		}
		
	}

	@Override
	public void borrarAlumno(int idAlumno) {
		
		
		
	}

	@Override
	public void modificarAlumno(Alumno alumno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void obtenerInformacionAlumno(int idAlumno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void obtenerListaAlumnos() {
		// TODO Auto-generated method stub
		
	}

}
