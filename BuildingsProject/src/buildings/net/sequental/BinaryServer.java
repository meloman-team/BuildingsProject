package buildings.net.sequental;

import buildings.Buildings;
import buildings.exceptions.BuildingUnderArrestException;
import buildings.interfaces.Building;
import factory.*;
import java.io.*;
import java.net.*;

public class BinaryServer {

    private static double valuationOfBuilding(Building bild) throws BuildingUnderArrestException {
        checkForArrest();
        switch (bild.getClass().getName()) {
            case "buildings.dwelling.Dwelling":
                return bild.getAreaTotal() * 1000;
            case "buildings.office.OfficeBuilding":
                return bild.getAreaTotal() * 1500;
            case "buildings.dwelling.hotel.HotelBuilding":
                return bild.getAreaTotal() * 2000;
        }
        return 0;
    }

    private static void checkForArrest() throws BuildingUnderArrestException {
        int x = (int) (Math.random() * 100);
        if (x > 90) {
            throw new BuildingUnderArrestException();
        }
    }

    public static void main(String[] args) throws UnknownHostException {
        
        int port = 6666; // случайный порт (может быть любое число от 1025 до 65535)
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("жду клиента...");
            Socket socket = serverSocket.accept(); 
            System.out.println("клиент подключился");
            System.out.println();

            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            Building bild = null;
            String line = null;
            System.out.println("жду кол строк");
            int lineCount = in.readInt();
            System.out.println("получил кол строк");

            out.writeUTF("ok");
            out.flush();
            System.out.println("отправил ок");

            for (int i = 0; i < lineCount; i++) {

                System.out.println("жду строку");
                line = in.readUTF();
                System.out.println("получил строку");

                out.writeUTF("ok");
                out.flush();
                System.out.println("отправил ок");

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

                System.out.println("жду здание");
                bild = Buildings.inputBuilding(sin);
                System.out.println("получил здание");

                try {

                    double otv = valuationOfBuilding(bild);

                    out.writeUTF(Double.toString(otv));
                    out.flush();
                    System.out.println("отправил ответ: " + otv);

                } catch (Exception e) {
                    out.writeUTF(e.getMessage());
                    out.flush();
                    System.out.println("отправил: " + e.getMessage());
                }
            }

            sin.close();
            sout.close();
            out.close();
            in.close();
            socket.close();
            serverSocket.close();

        } catch (Exception x) {
            x.printStackTrace();
        }
    }

}
