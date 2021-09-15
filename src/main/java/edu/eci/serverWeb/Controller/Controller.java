package edu.eci.serverWeb.Controller;
import java.util.Random;

import edu.eci.myFramework.anotacion.Controlador;
import edu.eci.myFramework.anotacion.Parametros;
import edu.eci.myFramework.anotacion.RequestMapping;

@Controlador
public class Controller {
	
	static String[] imgs = new String[] {"1.jpg","demonio.jpg","game_over.png","muerta.jpg"};
	
	
	@RequestMapping("/")
	public static String index() {
		return "Greetings from Spring Boot!";
	}
	
	
	
	
	@RequestMapping("/imagenes")
	public static String obtenerImagenes() {
		
		Random r=new Random();
		
		return imgs[r.nextInt(imgs.length-1)];
	}
	
	
	
}
