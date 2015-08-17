package buildings.dwelling;

import buildings.interfaces.*;
import buildings.Buildings;
import buildings.office.Office;
import buildings.dwelling.Flat;
import buildings.dwelling.hotel.HotelFloor;
import java.io.*;

//import java.util.Arrays;
public class BuildingsProject {

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        System.out.println("TEST_1_Flat");
        System.out.println("Конструктор без параметров");
        Space obj = new Flat();
        System.out.println("Площадь:" + obj.getArea());
        System.out.println("Комнат:" + obj.getQuantityRoom());

        System.out.println("Конструктор с одним параметром площади");
        Space obj1 = new Flat(10);
        System.out.println("Площадь:" + obj1.getArea());
        System.out.println("Комнат:" + obj1.getQuantityRoom());

        System.out.println("Конструктор с одним параметров комнат");
        Space obj2 = new Flat(8, 20);
        System.out.println("Площадь:" + obj2.getArea());
        System.out.println("Комнат:" + obj2.getQuantityRoom());

        System.out.println("меттоды set");
        obj2.setArea(0);
        obj2.setQuantityRoom(0);
        System.out.println("Площадь:" + obj2.getArea());
        System.out.println("Комнат:" + obj2.getQuantityRoom());

        System.out.println("");
        System.out.println("TEST_2_DwellingFloor_2");

        System.out.println("Конструктор без параметров");
        DwellingFloor floor1 = new DwellingFloor(4);
        System.out.println("Количество квартир:" + floor1.getSpaceQuantity());
        System.out.println("Площадь 1 кв:" + floor1.getSpace(0).getArea());
        System.out.println("Кол комн 1 кв:" + floor1.getSpace(0).getQuantityRoom());

        System.out.println("Конструктор c массивом квартир");
        Space[] massFlat = new Flat[2];

        for (int i = 0; i < massFlat.length; i++) {
            massFlat[i] = new Flat(1.5, 12);
        }
        DwellingFloor floor2 = new DwellingFloor(massFlat);
        System.out.println("Кол комн 1 кв:" + floor2.getSpace(0).getQuantityRoom());
        System.out.println("Площадь 1 кв:" + floor2.getSpace(0).getArea());
        System.out.println("");
        System.out.println("Проверка остальных методов");
        System.out.println("Количество квартир на этаже:" + floor2.getSpaceQuantity());
        System.out.println("Площаль всех квартир этажа:" + floor2.getAreaTotal());
        System.out.println("Количество комнат на этаже:" + floor2.getRoomQuantity());
        System.out.println("");
        System.out.println("Добавим 3 квартиру на этаж с 15 комнатами площадью 55");
        int index = 1;
        Space newFlat_3 = new Flat(55, 15);
        floor2.insertSpace(index, newFlat_3);
        System.out.println("Проверим кол кв:" + floor2.getSpaceQuantity());

        for (int i = 0; i < floor2.getSpaceQuantity(); i++) {
            System.out.println("Кол комн" + i + "кв:" + floor2.getSpace(i).getQuantityRoom());
        }
        for (int i = 0; i < floor2.getSpaceQuantity(); i++) {
            System.out.println("Площадь" + i + "кв:" + floor2.getSpace(i).getArea());
        }

        Space BestSpace = floor2.getBestSpace();
        System.out.println("Самая большая площадь кв на этаже:" + BestSpace.getArea());
        Office ofice = new Office();
        floor2.setSpace(0, ofice);

        System.out.println("");
        System.out.println("TEST_3_Dwelling_2");

        System.out.println("Конструктор с массивом этажей");
        DwellingFloor massFloor[] = new DwellingFloor[2];
        for (int i = 0; i < massFloor.length; i++) {
            massFloor[i] = new DwellingFloor(4);
        }
        Dwelling dwelling = new Dwelling(massFloor);
        System.out.println("Количество этажей:" + dwelling.getFloorQuantity());
        System.out.println("Количество квартир в здании:" + dwelling.getSpaceQuantity());
        System.out.println("Общая площадь в здании:" + dwelling.getAreaTotal());
        System.out.println("Количество комнат в здании:" + dwelling.getRoomQuantity());

        System.out.println("Конструктор c количеством этажей и массивом квартир по этажам");
        int flat[] = new int[3];
        for (int i = 0; i < 3; i++) {
            flat[i] = i + 1;
        }
        Dwelling dwelling_2 = new Dwelling(3, flat);
        System.out.println("Количество этажей:" + dwelling_2.getFloorQuantity());
        System.out.println("Количество квартир в здании:" + dwelling_2.getSpaceQuantity());
        System.out.println("Общая площадь в здании:" + dwelling_2.getAreaTotal());
        System.out.println("Количество комнат в здании:" + dwelling_2.getRoomQuantity());

        Space temp = new Flat(20);
        dwelling_2.setSpace(3, temp);
        dwelling_2.insertSpace(200, temp);

        Space sortMass[] = dwelling_2.getSortingSpaceByArea();
        System.out.println("Проверка сортировки");
        for (int i = 0; i < sortMass.length; i++) {
            System.out.println(sortMass[i].getArea());
        }
        Space sp = new Office();
        Building bild = dwelling_2;
        //bild.setSpace(0, sp);
        System.out.println("Проверка");
        for (int i = 0; i < bild.getFloorQuantity(); i++) {
            System.out.println("Floor:" + i);
            for (int j = 0; j < bild.getFloor(i).getSpaceQuantity(); j++) {
                double area = bild.getFloor(i).getSpace(j).getArea();
                System.out.println("    Office " + j + ":" + area + "area");
            }
        }
        //биты
        System.out.println("");
        DataOutputStream out = new DataOutputStream(new FileOutputStream("C:\\Users\\Ilya\\Desktop\\test.txt"));
        Buildings.outputBuilding(bild, out);//System.out

        DataInputStream in = new DataInputStream(new FileInputStream("C:\\Users\\Ilya\\Desktop\\test.txt"));
        Building test = Buildings.inputBuilding(in);//System.in

        System.out.println("Проверка");
        for (int i = 0; i < test.getFloorQuantity(); i++) {
            System.out.println("Floor:" + i);
            for (int j = 0; j < test.getFloor(i).getSpaceQuantity(); j++) {
                double area = test.getFloor(i).getSpace(j).getArea();
                System.out.println("    Office " + j + ":" + area + "area");
            }
        }

        //Сериализация 
        FileOutputStream fos = new FileOutputStream("C:\\Users\\Ilya\\Desktop\\test.out");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(bild);
        oos.flush();
        oos.close();

        //десериализация 
        ObjectInputStream inn = new ObjectInputStream(new FileInputStream("C:\\Users\\Ilya\\Desktop\\test.out"));
        bild = (Building) inn.readObject();
        inn.close();
        System.out.println("Проверка десериализация");
        for (int i = 0; i < bild.getFloorQuantity(); i++) {
            System.out.println("Floor:" + i);
            for (int j = 0; j < bild.getFloor(i).getSpaceQuantity(); j++) {
                double area = bild.getFloor(i).getSpace(j).getArea();
                System.out.println("    Office " + j + ":" + area + "area");
            }
        }
        
        //строки
//        System.out.println("");
//        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\Ilya\\Desktop\\test.txt")));
//        Buildings.writeBuilding(bild, out);
//        
//        BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Ilya\\Desktop\\test.txt"));
//        Building test = Buildings.readBuilding(in);
//        
//        System.out.println("Проверка");
//        for (int i = 0; i < test.getFloorQuantity(); i++) {
//            System.out.println("Floor:" + i);
//            for (int j = 0; j < test.getFloor(i).spaceQuantity(); j++) {
//                double area = test.getFloor(i).getSpace(j).getArea();
//                System.out.println("    Office " + j + ":" + area + "area");
//            }
//        }
//        
//        //Сериализация 
//        FileOutputStream fos = new FileOutputStream("C:\\Users\\Ilya\\Desktop\\test.out");
//        ObjectOutputStream oos = new ObjectOutputStream(fos);
//        oos.writeObject(bild);
//        oos.flush();
//        oos.close();
//
//        //десериализация 
//        ObjectInputStream inn = new ObjectInputStream(new FileInputStream("C:\\Users\\Ilya\\Desktop\\test.out"));
//        bild = (Building) inn.readObject();
//        inn.close();
//        System.out.println("Проверка");
//        for (int i = 0; i < bild.getFloorQuantity(); i++) {
//            System.out.println("Floor:" + i);
//            for (int j = 0; j < bild.getFloor(i).spaceQuantity(); j++) {
//                double area = bild.getFloor(i).getSpace(j).getArea();
//                System.out.println("    Office " + j + ":" + area + "area");
//            }
//        }

        
        
        
               //проверка этажа
//        Office office = new Office(500, 3);
//        OfficeFloor floor = new OfficeFloor(5);
//        floor.changeSpace(4, office);
//        for (int i = 0; i < floor.spaceQuantity(); i++) 
//        {
//            System.out.println("Area office " +i+ " ="+floor.getSpace(i).getArea());
//        }
//        System.out.println("Area best ="+floor.getBestSpace().getArea());
//
//        //проверка здания
//        int[] countOffices = new int[4];
//        countOffices[0] = 3;
//        countOffices[1] = 2;
//        countOffices[2] = 1;
//        countOffices[3] = 1;
//
//        Building building = new OfficeBuilding(4, countOffices);//2 этажа в здании
//        Office[] officesArrey = new Office[3];
//        officesArrey[0] = new Office(1, 1);//area/room
//        officesArrey[1] = new Office(600, 2);
//        officesArrey[2] = new Office(3, 3);
//        OfficeFloor newFloor = new OfficeFloor(officesArrey);
//
//        System.out.println("Area total =" + building.getSpaceQuantity());
//        for (int i = 0; i < building.getFloorQuantity(); i++) {
//            System.out.println("Floor:" + i);
//            for (int j = 0; j < building.getFloor(i).spaceQuantity(); j++) {
//                double area = building.getFloor(i).getSpace(j).getArea();
//                System.out.println("    Office " + j + ":" + area + "area");
//            }
//
//        }
//
//        building.insertSpace(4, officesArrey[1]);
//        //building.changeFloor(2, newFloor);
//        building.setSpace(0, officesArrey[1]);
//        building.removeSpace(0);
//        System.out.println("после");
//        for (int i = 0; i < building.getFloorQuantity(); i++) {
//            System.out.println("Floor:" + i);
//            for (int j = 0; j < building.getFloor(i).spaceQuantity(); j++) {
//                double area = building.getFloor(i).getSpace(j).getArea();
//                System.out.println("    Office " + j + ":" + area + "area");
//            }
//        }
//        //Сериализация 
//        FileOutputStream fos = new FileOutputStream("C:\\Users\\Ilya\\Desktop\\test.out");
//        ObjectOutputStream oos = new ObjectOutputStream(fos);
//        oos.writeObject(building);
//        oos.flush();
//        oos.close();
//
//        //десериализация 
//        ObjectInputStream inn = new ObjectInputStream(new FileInputStream("C:\\Users\\Ilya\\Desktop\\test.out"));
//        building = (Building) inn.readObject();
//        inn.close();
//        System.out.println("Проверка");
//        for (int i = 0; i < building.getFloorQuantity(); i++) {
//            System.out.println("Floor:" + i);
//            for (int j = 0; j < building.getFloor(i).spaceQuantity(); j++) {
//                double area = building.getFloor(i).getSpace(j).getArea();
//                System.out.println("    Office " + j + ":" + area + "area");
//            }
//        }
        
        
        
        
    }
}
