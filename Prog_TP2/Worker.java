package Prog_TP2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.Callable;

class Worker implements Callable<Integer>{
	private Socket socket;
	Worker(Socket socket){
		this.socket = socket;
	}
	
	@Override
	public Integer call() throws Exception{
		try {			
			InputStream is = socket.getInputStream();
			byte bytes[] = new byte[1024];
			int byteRead;
			String request = "";
			while(true) {
				byteRead = is.read(bytes);
				request += new String(bytes);
				if (request.indexOf("\r\n") !=-1) {
					break;
				}
			}
				OutputStream os = socket.getOutputStream();
				os.write(getContent(request));
				os.flush();
				socket.close();			
		}			
		catch(Exception e) {
			System.out.println(e);
		}
		Thread.sleep(10000);
		return 0;
		//System.out.println(request);
	}
	
	private byte[] getContent(String req) throws IOException {
		String s = req;
		String text = "";
		text += "HTTP/1.1 200 OK\n";
		text += "Content-Type: text/html\n";
		text += "\r\n";
		System.out.println(req);
		/* text +=  req;
		text += req.indexOf("/");
		text += req.indexOf("HTTP")-1;
		text += req.substring(req.indexOf("/"), req.indexOf("HTTP")-1);*/
		String str = req.substring(req.indexOf("/"), req.indexOf("HTTP")-1);
		System.out.println(str);
		if (str.contains("?")) {
			String path = str.substring(0, str.indexOf("?"));
			
			String name = str.substring(str.indexOf("=")+1);
			text+=readFile(path,name);
			
		}
		else {
			text +=readFile(str,"");
		}
		
		text+="\r\n";
		return text.getBytes();
	}
	
	String readFile(String path, String name)  {
		System.out.println("------"+path);
			File file = new File(
					System.getProperty("user.dir")+"/src/Prog_TP2"+path);
	        BufferedReader br;
			try {
				br = new BufferedReader(new FileReader(file));
			} catch (FileNotFoundException e) {
				return "<h1>404 Not Found</h1>";
			}
	 
	        // Declaring a string variable
	        String st;
	        String str="";
	        // Condition holds true till
	        // there is character in a string
	        
	        try {
				while ((st = br.readLine()) != null) {
					
					str+=st;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        System.out.println(name);
	 return str.replace("${name}",name);
	            
	}
	
	
	public int count(String s) {
		int i=0;
		int c=0;
		
		while(i<s.length()) {
			if(s.charAt(i) == '\n') {
				c++;
			}
			i++;
		}
		
		return c-1;
	}



}
