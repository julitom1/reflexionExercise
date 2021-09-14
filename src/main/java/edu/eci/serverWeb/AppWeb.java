package edu.eci.serverWeb;

import edu.eci.myFramework.anotacion.Comenzar;
import edu.eci.myFramework.anotacion.RequestMapping;
import edu.eci.serverWeb.Controller.Controller;

import java.util.ArrayList;

import edu.eci.myFramework.ControllerAnotaton;


public class AppWeb {
	
	@Comenzar
	public static ArrayList<Class> aquiComienzo() {
		System.out.println("###################################3");
		ArrayList<Class> clases= new ArrayList<Class>();
		clases.add(Controller.class);
		return clases;
	}
}
