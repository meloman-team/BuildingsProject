package buildings.net.server.parallel;

import buildings.exceptions.BuildingUnderArrestException;
import buildings.interfaces.Building;
import java.io.*;
import java.net.Socket;

public class ThreadSerialServer extends Thread {

    Socket socket;

    public ThreadSerialServer(Socket socket) {
        this.socket = socket;
    }

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
        if (x > 30) {//10% если >90
            throw new BuildingUnderArrestException();
        }
    }

    @Override
    public void run() {
        try {
            InputStream sin = socket.getInputStream();

            Building bild = null;

            System.out.println("жду кол строк");
            DataInputStream in = new DataInputStream(sin);
            int lineCount = in.readInt();
            System.out.println("получил кол строк");

            OutputStream sout = socket.getOutputStream();
            DataOutputStream out = new DataOutputStream(sout);
            out.writeUTF("ok");
            out.flush();

            System.out.println("отправил ок");
            ObjectInputStream inn = new ObjectInputStream(sin);
            ObjectOutputStream oos = new ObjectOutputStream(sout);

            for (int i = 0; i < lineCount; i++) {

                System.out.println("жду здание...");
                bild = (Building) inn.readObject();
                System.out.println("получил здание");

                try {

                    double otv = valuationOfBuilding(bild);

                    oos.writeObject(otv);
                    oos.flush();
                    System.out.println("отправил ответ: " + otv);

                } catch (BuildingUnderArrestException e) {
                    oos.writeObject(e);
                    oos.flush();
                    System.out.println("отправил: " + e.getMessage());
                }
            }

            sin.close();
            sout.close();
            out.close();
            in.close();
            socket.close();

        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}
