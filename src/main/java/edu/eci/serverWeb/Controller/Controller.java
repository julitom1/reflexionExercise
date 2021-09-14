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
	public static String obtenerImagenes() {
		return "Greetings from Spring Boot777777!";
	}
	@Parametros(value = {"pokemon"})
	@RequestMapping("/imageness")
	public static String obtenerImagenes(Object parametros) {
		return "Greetings from Spring Boot121212!";
	}
	
}
