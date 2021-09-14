package edu.eci.myFramework;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.eci.myFramework.ControllerAnotaton;
import edu.eci.myFramework.httpServer.HttpServerFramework;


/**
 * Hello world!
 *
 */
public class App 
{
public static void main(String [] args) {
		
		
		try {
			ControllerAnotaton.start(args[0]);
			HttpServerFramework.getInstance().start(getPort());
		}catch(IOException ex) {
			Logger.getLogger(HttpServerFramework.class.getName()).log(Level.SEVERE,null,ex);
		} catch (URISyntaxException ex) {
			// TODO Auto-generated catch block
			Logger.getLogger(HttpServerFramework.class.getName()).log(Level.SEVERE,null,ex);
		}
	}

	private static int getPort() {
		if(System.getenv("PORT")!=null) {
			return Integer.parseInt(System.getenv("PORT"));
		}
		return 35000;
	}
}
