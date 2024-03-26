package bai_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
        	
           Socket socket = new Socket("localhost", 5678);
          
          BufferedReader in= new BufferedReader(new InputStreamReader(socket.getInputStream()));
          
          PrintWriter out= new PrintWriter(socket.getOutputStream(), true);
          
          BufferedReader consoleInput= new BufferedReader(new InputStreamReader(System.in));
          
          String serverInput;
          
          while(true) {
        	  
        	  System.out.println("Cline: ");
        	  String clientInput= consoleInput.readLine();
        	  out.println(clientInput);
        	  
        	  serverInput= in.readLine();
        	  System.out.println("Server: "+ serverInput);
          }

        } catch (Exception e) {
            e.printStackTrace();
        }
    	
    	
    }
}

