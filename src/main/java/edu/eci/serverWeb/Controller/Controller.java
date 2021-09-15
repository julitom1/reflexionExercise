package edu.eci.serverWeb.Controller;
import edu.eci.myFramework.anotacion.Controlador;
import edu.eci.myFramework.anotacion.Parametros;
import edu.eci.myFramework.anotacion.RequestMapping;

@Controlador
public class Controller {
	
	@RequestMapping("/")
	public static String index() {
		return "Greetings from Spring Boot!";
	}
	
	
	
	
	@RequestMapping("/imagenes")
	public static String[] obtenerImagenes() {
		return new String[] {"aa","dsd"};
	}
	
}
