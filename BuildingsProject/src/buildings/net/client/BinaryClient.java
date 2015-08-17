package buildings.net.client;

import buildings.Buildings;
import buildings.interfaces.Building;
import java.io.*;
import java.net.*;

public class BinaryClient {

    public static void main(String[] args) throws IOException {

        int serverPort = 6666;
        String address = InetAddress.getLocalHost().getHostAddress();

        try {
            InetAddress ipAddress = InetAddress.getByName(address);
            Socket socket = new Socket(ipAddress, serverPort);
            System.out.println("подключился к серверу");
 
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            DataInputStream in;
            DataOutputStream out = new DataOutputStream(sout);
            BufferedWriter bufW = new BufferedWriter(new FileWriter("C:\\Users\\Ilya\\Desktop\\otv.txt"));

            BufferedReader bufR = new BufferedReader(new FileReader("C:\\Users\\Ilya\\Desktop\\test.txt"));
            int lineCount = 0;
            while ((bufR.readLine()) != null) {
                lineCount++;
            }
            out.writeInt(lineCount);
            System.out.println("отправил кол строк");
            bufR.close();
            bufR = new BufferedReader(new FileReader("C:\\Users\\Ilya\\Desktop\\type.txt"));
            
            String otv = null;
            DataInputStream in3 = new DataInputStream(sin);
            in3.readUTF();
            System.out.println("получил ок");

            for (int i = 1; i <= lineCount; i++) {

                String line = bufR.readLine();
                out.writeUTF(line);
                out.flush();
                System.out.println("отправил строку");

                in3.readUTF();
                System.out.println("получил ок");

                in = new DataInputStream(new FileInputStream("C:\\Users\\Ilya\\Desktop\\test.txt"));
                Building test = Buildings.inputBuilding2(in, i);

                Buildings.outputBuilding(test, sout);
                System.out.println("отправил здание");

                otv = in3.readUTF();
                System.out.println("получил ответ: " + otv);
                bufW.write(otv);
                bufW.flush();
                bufW.newLine();
                System.out.println("записал ответ");
                in.close();
            }

            out.close();
            in3.close();
            sin.close();
            sout.close();
            bufR.close();
            bufW.close();
            
            socket.close();

        } catch (Exception x) {
            x.printStackTrace();
        }
    }

}
