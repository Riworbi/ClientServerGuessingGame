package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class Handler extends Thread{

      private Socket socket;
      private BufferedReader in;
      public PrintWriter out;
      private int PlayerNumber;
      private int ServerNumber;
      
  	public static int Randomize() {
		  Random rand = new Random(); 
	     synchronized(rand) {
		  int upperbound = 25;
	      int int_random = rand.nextInt(upperbound); 
	    return int_random;
	     }
	}
      
      public Handler(Socket socket) {
          this.socket = socket;
      }
      
      public void run() {
	
    	  try {
			in = new BufferedReader(new InputStreamReader(
			          socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(),true);

			while(true) {
				String input = in.readLine();
				
						if(input.equals("Obstaw")) {
							PlayerNumber = Randomize();
							ServerNumber = Randomize();
							out.println(PlayerNumber);	
							out.println(ServerNumber);
						}
						if(input.equals("Losuj")) {
							PlayerNumber = Randomize();
							out.println(PlayerNumber);
						}
						if(input.equals("EndGame")) {
							socket.close();
						}
					}				
    	  } catch (IOException e) {
			// TODO Auto-generated catch block
		}
    	  
      }
}
