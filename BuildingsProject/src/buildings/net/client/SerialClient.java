package buildings.net.client;

import buildings.Buildings;
import buildings.interfaces.Building;
import factory.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SerialClient {

    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
        int serverPort = 6666;
        String address = InetAddress.getLocalHost().getHostAddress();
        InetAddress ipAddress = InetAddress.getByName(address);
        Socket socket = new Socket(ipAddress, serverPort);
        System.out.println("подключился к серверу");
        
        InputStream sin = socket.getInputStream();
        OutputStream sout = socket.getOutputStream();

        BufferedReader bufR = new BufferedReader(new FileReader("C:\\Users\\Ilya\\Desktop\\test.txt"));

        int lineCount = 0;
        while ((bufR.readLine()) != null) {
            lineCount++;
        }
        
        bufR.close();
        DataOutputStream out = new DataOutputStream(sout);
        out.writeInt(lineCount);
        System.out.println("отправил кол строк");

        String otv = null;
        DataInputStream in3 = new DataInputStream(sin);
        otv = in3.readUTF();
        System.out.println("получил ок");

        bufR = new BufferedReader(new FileReader("C:\\Users\\Ilya\\Desktop\\type.txt"));
        BufferedWriter bufW = new BufferedWriter(new FileWriter("C:\\Users\\Ilya\\Desktop\\otv.txt"));
        ObjectOutputStream oos = new ObjectOutputStream(sout);

        ObjectInputStream inn = new ObjectInputStream(sin);
        for (int i = 1; i <= lineCount; i++) {
            String line = bufR.readLine();
            switch (line) {
                case "Dwelling":
                    Buildings.setBuildingFactory(new DwellingFactory());
                    break;
                case "OfficeBuilding":
                    Buildings.setBuildingFactory(new OfficeFactory());
                    break;
                case "HotelBuilding":
                    Buildings.setBuildingFactory(new HotelFactory());
                    break;
            }

            DataInputStream in = new DataInputStream(new FileInputStream("C:\\Users\\Ilya\\Desktop\\test.txt"));
            
            Building test = Buildings.inputBuilding2(in, i);

            oos.writeObject(test);
            oos.flush();
            System.out.println("отправил здание");

            
            Object otv2 = inn.readObject();
            System.out.println("получил ответ: " + otv2.toString());
            
            bufW.write(otv2.toString());
            bufW.flush();
            bufW.newLine();
            System.out.println("записал ответ");
            in.close();
        }

        out.close();
        in3.close();
        sin.close();
        sout.close();
        bufW.close();
        bufR.close();
        socket.close();
    }
}
