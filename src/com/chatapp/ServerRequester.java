package com.chatapp;

import com.chatapp.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Iterator;

public class ServerRequester implements Runnable {
	BufferedReader reader;
	Socket sock;
	ServerStart s = new ServerStart();

	public ServerRequester(Socket clientSocket) {
		try {
			sock = clientSocket;
			InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
			reader = new BufferedReader(isReader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		String message;
		try {
			while ((message = reader.readLine()) != null) {
				System.out.println("Read : " + message);
				notifyAll(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void notifyAll(String message) {
		Iterator<PrintWriter> it = s.clientOutputStreams.iterator();
		while (it.hasNext()) {
			try {
				PrintWriter writer = (PrintWriter) it.next();
				writer.println(message);
				writer.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
