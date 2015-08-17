package buildings.net.server.parallel;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class BinaryServer {

    public static void main(String[] args) {
        ArrayList clientList = new ArrayList();
        int port = 6666; // случайный порт (может быть любое число от 1025 до 65535)
        try {

            ServerSocket serverSocket = new ServerSocket(port); 
            System.out.println("ждем клиента...");
            while (true) {
                Socket socket = serverSocket.accept(); 
                System.out.println("клиент подключился");
                System.out.println();

                ThreadBinaryServer r = new ThreadBinaryServer(socket);
                clientList.add(r);
                r.start();
            }

        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}
