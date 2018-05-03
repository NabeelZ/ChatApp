package  com.chatapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class ServerStart {
	public	ArrayList<PrintWriter> clientOutputStreams;
		
	
	public static void main(String[] args) {
		new ServerStart().getStart();

	}
	
	public void getStart() {
		clientOutputStreams = new ArrayList<PrintWriter>();
		try {
			ServerSocket serverSock = new ServerSocket(5000);
			while(true){
				Socket clientSocket = serverSock.accept();
				PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
				clientOutputStreams.add(writer);
				
				Thread t = new Thread(new ServerRequester(clientSocket));
				t.start();
				System.out.println("Got a connection ! \n ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	}


