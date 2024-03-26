package bai_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

//Chạy Server trước để khởi động server.

//Sau đó, chạy Client để kết nối và bắt đầu trò chuyện.

public class Server {
    public static void main(String[] args) {
      try {
          // Tạo một ServerSocket với cổng 12345
    	  ServerSocket serverSocket= new ServerSocket(5678);
    	  System.out.println("Server đã khởi động và đang chờ Client");
    	  
          // Chấp nhận kết nối từ client và trả về một Socket
    	  Socket clinetsocket= serverSocket.accept();
    	  System.out.println("Client đã kết nối.");
    	  
          // Tạo một BufferedReader để đọc dữ liệu từ client
    	  BufferedReader in= new BufferedReader(new InputStreamReader(clinetsocket.getInputStream()));
    	  
          // Tạo một PrintWriter để gửi dữ liệu tới client
    	  PrintWriter ou= new PrintWriter(clinetsocket.getOutputStream(), true);
    	  
          // Tạo một BufferedReader để đọc dữ liệu từ bàn phím của server
    	  BufferedReader consoleInput= new BufferedReader(new InputStreamReader(System.in));
    	  
          String clientInput;

          // Vòng lặp vô tận để liên tục nhận và gửi dữ liệu giữa server và client
          while((clientInput= in.readLine()) != null) {
        	  
              // In dữ liệu nhận được từ client ra console của server
        	  System.out.println("Cline :" + clientInput);
        	  
              // Nhập dữ liệu từ bàn phím của server và gửi tới client
        	  System.out.println("Server : ");
        	  String serverInput= consoleInput.readLine();
        	  ou.println(serverInput);
          }
          
          in.close();
          ou.close();
          clinetsocket.close();
          serverSocket.close();
    	  consoleInput.close();
	} catch (Exception e) {
		e.printStackTrace();
		// TODO: handle exception
	}
    
    	
  
    }
}
