package bai_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server_time {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(45678);
            System.out.println("Server đã khởi động và đang chờ Client");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Server đã nhận và đã kết nối...");
                
                new Thread(new ClientHandler(socket)).start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable {
        private Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                
                String clientInput;
                
                while ((clientInput = in.readLine()) != null) {
                    if ("time".equalsIgnoreCase(clientInput.trim())) {
                        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                        String response = format.format(new Date());
                        
                        out.println(response);
                    } else {
                        out.println("Không hiểu lệnh. Gửi 'time' để lấy thời gian hiện tại.");
                    }
                }
                
                in.close();
                out.close();
                socket.close();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
