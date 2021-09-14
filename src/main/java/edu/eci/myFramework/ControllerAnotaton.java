package edu.eci.myFramework;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;

import edu.eci.myFramework.anotacion.RequestMapping;
import edu.eci.myFramework.anotacion.Comenzar;
import edu.eci.myFramework.anotacion.Controlador;
import edu.eci.myFramework.anotacion.Parametros;

public class ControllerAnotaton {
	
	

	private static ArrayList<Class> clasesApp;
	

	
	public static void start(String application) {
		String rutaDeLaClase=application; 
		try {
			Method[] metodos = Class.forName(application).getMethods();
			for (Method m : metodos) {
				if (m.isAnnotationPresent(Comenzar.class)) {
					Comenzar comenzar = m.getAnnotation(Comenzar.class);
					clasesApp=(ArrayList<Class>) m.invoke(null);
				}
			}
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
		
	}
	
	public static String buscarConParametros(String pagina,String[] params) {
		String pag="";
		try {
			for(Class c:clasesApp) {
				if(c.isAnnotationPresent(Controlador.class)) {
					Method[] metodos=c.getMethods();
					for (Method m : metodos) {
						if (m.isAnnotationPresent(RequestMapping.class) && m.isAnnotationPresent(Parametros.class)) {
							RequestMapping requestMapping = m.getAnnotation(RequestMapping.class);
							String recibir = requestMapping.value();
							if(recibir.equals(pagina)) {
									pag=(String) m.invoke(params);
								
							}
						}
					}
				}
			}
				
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pag;
		
	}
	public static String run(String pagina,String[] params) {
		String pag="";
		if(params!=null) {
			System.out.println("HELLO");
			pag=buscarConParametros(pagina,params);
		}else {
		
			try {
				for(Class c:clasesApp) {
					if(c.isAnnotationPresent(Controlador.class)) {
						Method[] metodos=c.getMethods();
						for (Method m : metodos) {
							if (m.isAnnotationPresent(RequestMapping.class)) {
								RequestMapping requestMapping = m.getAnnotation(RequestMapping.class);
								String recibir = requestMapping.value();
								if(recibir.equals(pagina)) {
										pag=(String) m.invoke(null);
									
								}
							}
						}
					}
				}
					
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return pag;
	}
}
