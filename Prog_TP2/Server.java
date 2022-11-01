package Prog_TP2;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

public class Server extends Thread{
	private ServerSocket serverSocket ;
	private ServerSocket commandSocket;
	public Server() {
		
	}
	public void run(){
		try {
			serverSocket = new ServerSocket(2134);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ExecutorService executorService = Executors.newFixedThreadPool(2);

		System.out.println("Sevrer started !");
		
		while(!serverSocket.isClosed()) {
				try {
					System.out.println("waiting ...");
					Socket socket = serverSocket.accept();
					System.out.println("socket accepted");
					Worker w = new Worker(socket);
					executorService.submit(w);
					System.out.println("socket submited");
				}
				catch(IOException e) {
					System.out.println(e);
				}
		}
		
		
	}
	public void closeSocket() throws IOException {
		commandSocket = new ServerSocket(8080);
		
		if (!serverSocket.isClosed()) {
			Socket socket = commandSocket.accept();
			OutputStream os = socket.getOutputStream();
			os.flush();
			socket.close();
			serverSocket.close();
		}
		
	}

	

	public static void main(String args[]) throws InterruptedException {
		Server server = new Server();
		try {
			server.start();
			Thread.sleep(2000);
			server.closeSocket();
			System.out.println("closed server socket !");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("error staring the server ! ! ");
			e.printStackTrace();
		}
			
	}

}
