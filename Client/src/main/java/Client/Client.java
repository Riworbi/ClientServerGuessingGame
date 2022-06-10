package Client;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	 private static Socket socket;
	 
	public Client() throws UnknownHostException, IOException {
		Client.socket   = new Socket("localhost",2555);
	}
	
	
	public static void main(String[] args) throws UnknownHostException, IOException{
		
		Client client = new Client();
		GUI Window = new GUI(socket);
	}
}
