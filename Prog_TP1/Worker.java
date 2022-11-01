package Prog_TP1;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.Callable;

class Worker implements Callable<Integer>{
	private Socket socket;
	private CountLines countLines = CountLines.getInstance();
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
		return 0;
		//System.out.println(request);
	}
	
	private byte[] getContent(String req) {
		String s = req;
		String text = "";
		text += "HTTP/1.1 200 OK\n";
		text += "Content-Type: text/html\n";
		text += "\r\n";
		text+= "Number of Line ="+count(req)+"\t";
		text+="Total Lines :" + countLines.getLineNumbers();
		countLines.addlinesNumber(count(req));
		text+="\r\n";
		return text.getBytes();
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
