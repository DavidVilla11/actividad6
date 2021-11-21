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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import es.iestetuan.dvj.GestorProperties;
import es.iestetuan.dvj.dao.IAlumno;
import es.iestetuan.dvj.vo.Alumno;

public class ImplementacionXML implements IAlumno {

	List<Alumno> ListaAlumnos = new ArrayList<Alumno>();
	
	public List<Alumno> obtenerListaAlumnos() {
		
		try {
				
				File fichero = new File(GestorProperties.claveConfig("xmlEscrito"));
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(fichero);
				doc.getDocumentElement().normalize();
				NodeList listaAlumnos = doc.getElementsByTagName("Alumno");
				
				for(int i=0; i<listaAlumnos.getLength(); i++) {
					
					Alumno alumnoXML = new Alumno();
					
					Node nodoAlumno = listaAlumnos.item(i);
					Element alumno = (Element) nodoAlumno;
					
					String id = alumno.getAttribute("id");
					int id_alumno = Integer.parseInt(id);
					
					NodeList contenidoNombre = alumno.getElementsByTagName("Nombre");
					String nombre = contenidoNombre.item(0).getTextContent();
					
					NodeList contenidoApellido1 = alumno.getElementsByTagName("Apellido1");
					String apellido1 = contenidoApellido1.item(0).getTextContent();
					
					NodeList contenidoApellido2 = alumno.getElementsByTagName("Apellido2");
					String apellido2 = contenidoApellido2.item(0).getTextContent();
					
					alumnoXML.setId_alumno(id_alumno);
					alumnoXML.setNombre(nombre);
					alumnoXML.setApellido1(apellido1);
					alumnoXML.setApellido2(apellido2);
					
					ListaAlumnos.add(alumnoXML);
					
				}
			}	
		
		catch (Exception e) {
			e.printStackTrace();
		}
		return ListaAlumnos;
		
	}
	
	public void guardarAlumnosXML() {
		
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
			StreamResult result = new StreamResult(new File(GestorProperties.claveConfig("xml")));
			
			
			transformer.transform(source, result);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void crearAlumno(Alumno alumno) {
		obtenerListaAlumnos();
		boolean comprobar = false;
		for(Alumno lista : ListaAlumnos) {
			
			if(lista.getId_alumno() == alumno.getId_alumno()) {
				System.out.println("Ya hay un alumno con ese id");
				comprobar = true;
				break;
			}
			
		}
		
		if (comprobar == false) {
			ListaAlumnos.add(alumno);
			guardarAlumnosXML();
			System.out.println("pero aun así lo meto porque soy bobo");
		}
		
	}

	@Override
	public void borrarAlumno(int idAlumno) {
		obtenerListaAlumnos();
		int i = 0;
		for(Alumno lista : ListaAlumnos) {
			if(lista.getId_alumno() == idAlumno) {
				ListaAlumnos.remove(i);
				guardarAlumnosXML();
			}

			i++;
		}
		
	}

	@Override
	public void modificarAlumno(Alumno alumno) {
		obtenerListaAlumnos();
		
		int i = 0;
		for(Alumno lista : ListaAlumnos) {
			boolean comprobado = false;
			if(lista.getId_alumno() == alumno.getId_alumno()) {
				ListaAlumnos.set(i, alumno);
				guardarAlumnosXML();
				comprobado = true;
			}
			
			if(comprobado == true) {
				System.out.println("El alumno ha actualizado.");
				break;
			}
			i++;
		}
		
	}

	@Override
	public void obtenerInformacionAlumno(int idAlumno) {
		obtenerListaAlumnos();
		 
		for(Alumno lista : ListaAlumnos) {
			boolean comprobado = false;
			if(lista.getId_alumno() == idAlumno) {
				System.out.println("El alumno se llama: " + lista.getNombre());
				System.out.println("Su primer apellido es: " + lista.getApellido1());
				System.out.println("Su segundo apellido es: " + lista.getApellido2());
				comprobado = true;
			}
			
			if(comprobado == true) {
				System.out.println("El alumno ha sido encontrado.");
				break;
			}
			
		}
		
		
		
	}
	
	public List<Alumno> obtenerListaAlumnosTXT() {
		
		List<Alumno> ListaAlumnos = new ArrayList<Alumno>();
		
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


}
