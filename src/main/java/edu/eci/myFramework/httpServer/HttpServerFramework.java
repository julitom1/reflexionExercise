package edu.eci.myFramework.httpServer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

import edu.eci.myFramework.ControllerAnotaton;

public class HttpServerFramework {
	private static final HashMap<String,String> content=new HashMap<String,String>();
	private static final HttpServerFramework instance=new HttpServerFramework();
	public static HttpServerFramework getInstance() {
		return instance;
	}
	
	private HttpServerFramework() {
		content.put("css","text/css");
		content.put("js","text/javascript");
		content.put("jpeg","image/jpeg");
		content.put("jpg","image/jpg");
		content.put("doc","application/msword");
		content.put("gif","image/gif");
		content.put("htm","text/html");
		content.put("html","text/html");
		content.put("json","application/json");
		content.put("pdf","application/pdf");
		content.put("png","image/png");
		
	}
	
public void start(Integer puerto) throws IOException, URISyntaxException {
		
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(puerto);
		} catch (IOException e) {
			System.err.println("Could not listen on port:."+ puerto);
			System.exit(1);
		}
		boolean running=true;
		while (running){
			Socket clientSocket = null;
			try {
				System.out.println("Listo para recibir ...");
				clientSocket = serverSocket.accept();
			} catch (IOException e) {
				System.err.println("Accept failed.");
				System.exit(1);
			}
			serveConnection(clientSocket);
		}
		serverSocket.close();
	}
	public void resourceText(String path,String extension,PrintWriter out) throws IOException{
	
		BufferedReader br = null;
		//String uri = Paths.get("public_html" + resourceURL.getPath());
		File archivo = new File ("src/main/resources/"+extension+"/"+path+"."+extension);
		String rta=httpOk(extension);
		
			
			FileReader fr = new FileReader (archivo);
			br = new BufferedReader(fr);
	         // Lectura del fichero
	         String linea;
	         
	         while((linea=br.readLine())!=null)
	        	 rta=rta+linea;
		
		out.println(rta);
	}
	public String httpOk(String key) {
		return "HTTP/1.1 200 OK \r\n" 
 				+ "Content-Type: "+ content.get(key) + "\r\n"
				+ "\r\n";
	}

	private void serveConnection(Socket clientSocket) throws IOException, URISyntaxException{
		OutputStream outStream=clientSocket.getOutputStream();
		PrintWriter out = new PrintWriter(outStream, true);
		InputStream inputStream=clientSocket.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
		String inputLine;
		ArrayList<String> request=new ArrayList<String>();
		
		while ((inputLine = in.readLine()) != null) {
			System.out.println("Received: " + inputLine);
			request.add(inputLine);
			if (!in.ready()) {
				break;
			}
		}
		String uriStr="";
		String uri="";
		String[] params=null;
		try {
			
			uriStr=request.get(0).split(" ")[1];
		
			URI resourceURI = new URI(uriStr);
			try {
				params = resourceURI.getQuery().split("&");
			}catch(Exception e) {}
			String[] ls=resourceURI.getPath().split("/");
			uri=ls[ls.length-1];
			System.out.println(resourceURI.getPath());
			String[] ur=uri.split("\\.");
			
			if(content.get(ur[1]).split("/")[0].equals("image")) {
				
				//resourceImage(ur[0],ur[1],outStream);
			}else {
				System.out.println(ur[0]);
				String algo=ControllerAnotaton.run("/"+ur[0],params);
				out.println(filesHTML(algo));
				//resourceText(ur[0],ur[1],out);
				
			}
			
			
		}catch(Exception e) {
			String algo=ControllerAnotaton.run("/",params);
			out.println(filesHTML(algo));
		}
			
		out.close();
		in.close();
		clientSocket.close();
		
	}
	public String filesHTML(String algo) {
		return "HTTP/1.1 200 OK\r\n" 
			+ "Content-Type: text/html\r\n"
			+ "\r\n"
			+ "<h1>"+ algo + "</h1>";
			
	}

}
