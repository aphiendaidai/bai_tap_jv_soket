package bai_2;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Client_time extends JFrame {

    private JLabel clockLabel;

    public Client_time() {
        setTitle("Đồng Hồ");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        clockLabel = new JLabel();
        clockLabel.setHorizontalAlignment(JLabel.CENTER);
        clockLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(clockLabel);

        Timer timer = new Timer(1000, e -> {
            try {
                Socket socket = null;
                BufferedReader in = null;
                PrintWriter out = null;

                int attempts = 0;
                while (attempts < 5) {  // Tạo một thời gian chờ cho client
                    try {
                        socket = new Socket("127.0.0.1", 45678);  // Đổi "localhost" thành "127.0.0.1"
                        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        out = new PrintWriter(socket.getOutputStream(), true);
                        break;
                    } catch (Exception ex) {
                        attempts++;
                        Thread.sleep(2000);  // Đợi 2 giây trước khi thử kết nối lại
                    }
                }

                if (attempts == 5) {
                    clockLabel.setText("Không thể kết nối đến server");
                    return;
                }

                out.println("time");
                String serverResponse = in.readLine();

                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                String currentTime = formatter.format(new Date());

                clockLabel.setText(currentTime);

                in.close();
                out.close();
                socket.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Client_time client = new Client_time();
            client.setVisible(true);
        });
    }
}
