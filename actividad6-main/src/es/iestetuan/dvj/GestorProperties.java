package es.iestetuan.dvj;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class GestorProperties {

		
		static Properties propiedades;
		
		public static Properties cargarConfiguracion() throws Exception{
			
			if(propiedades==null) {
				propiedades = new Properties();
				InputStream in = new FileInputStream("./configuracion.properties");
				propiedades.load(in);
			}
			return propiedades;
		}
		
		public static String claveConfig(String clave) throws Exception{
			
			String propiedad = cargarConfiguracion().getProperty(clave);
			return propiedad;
			
		}

}

