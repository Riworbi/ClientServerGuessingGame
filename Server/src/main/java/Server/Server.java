package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class Server {
	
    public static void main(String[] args) throws IOException{

    	ServerSocket serversocket = new ServerSocket(2555);
		try {
			while(true) {	
				Socket socket = serversocket.accept();
				Handler user = new Handler(socket);
				user.start();
			}
			
		}finally {
			serversocket.close();
		}
		}
    }