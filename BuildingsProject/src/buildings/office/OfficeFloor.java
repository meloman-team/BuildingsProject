package buildings.office;

import buildings.exceptions.SpaceIndexOutOfBoundsException;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;
import iterator.OfficeFloorIterator;
import java.io.Serializable;
import java.util.Iterator;

public class OfficeFloor implements Floor, Serializable, Cloneable {

    private OneLinkedNode head;

    public OfficeFloor() {
        head = new OneLinkedNode();
        head.setNextNode(head);
    }
    
    private OfficeFloor(OneLinkedNode head) {
        this.head = head;
    }

    public OfficeFloor(int officeQuantity) {
        OneLinkedNode node = new OneLinkedNode();
        head = node;
        for (int i = 1; i < officeQuantity; i++) {
            OneLinkedNode newNode = new OneLinkedNode();
            node.setNextNode(newNode);
            node = node.getNextNode();
        }
        node.setNextNode(head);
    }

    public OfficeFloor(Space[] offices) {
        OneLinkedNode node = new OneLinkedNode(offices[0]);
        head = node;
        for (int i = 1; i < offices.length; i++) {
            OneLinkedNode newNode = new OneLinkedNode(offices[i]);
            node.setNextNode(newNode);
            node = node.getNextNode();
        }
        node.setNextNode(head);
    }

    private OneLinkedNode getNode(int numberNode) {
        if (numberNode > getSpaceQuantity() - 1 || numberNode < 0) {
            throw new SpaceIndexOutOfBoundsException();
        }

        OneLinkedNode node = head;
        for (int i = 0; i < numberNode; i++) {
            node = node.getNextNode();
        }
        return node;
    }

    private OneLinkedNode searchTailNode() {
        OneLinkedNode node = head;
        while (node.getNextNode() != head) {
            node = node.getNextNode();
        }
        return node;
    }

    //дописать еще один if для добавления в конец или вынести в отдельный метод?
    private void insertNode(OneLinkedNode nodeForInssert, int numberNode) {
        if (numberNode > getSpaceQuantity() - 1 || numberNode < 0) {
            throw new SpaceIndexOutOfBoundsException();
        }

        OneLinkedNode node;
        if (numberNode == 0) {
            nodeForInssert.setNextNode(head);
            node = searchTailNode();
            node.setNextNode(nodeForInssert);
            head = nodeForInssert;

        } else {
            node = head;
            for (int i = 0; i < numberNode - 1; i++) {
                node = node.getNextNode();
            }
            nodeForInssert.setNextNode(node.getNextNode());
            node.setNextNode(nodeForInssert);
        }
    }

    private void removeNode(int numberNode) {
        if (numberNode > getSpaceQuantity() - 1 || numberNode < 0) {
            throw new SpaceIndexOutOfBoundsException();
        }

        OneLinkedNode node = head;
        if (numberNode == 0) {
            while (node.getNextNode() != head) {
                node = node.getNextNode();
            }
            node.setNextNode(head.getNextNode());
            head = head.getNextNode();
        } else {
            for (int i = 0; i < numberNode - 1; i++) {
                node = node.getNextNode();
            }
            node.setNextNode(node.getNextNode().getNextNode());
        }
    }

    @Override
    public int getSpaceQuantity() {
        int countOffice = 1;
        OneLinkedNode node = head;
        while (node.getNextNode() != head) {
            node = node.getNextNode();
            countOffice++;
        }
        return countOffice;
    }

    @Override
    public double getAreaTotal() {
        OneLinkedNode node = head;
        Office office;
        office = (Office) node.getOffice();
        double areaTotal = office.getArea();
        while (node.getNextNode() != head) {
            node = node.getNextNode();
            office = (Office) node.getOffice();
            areaTotal += office.getArea();
        }
        return areaTotal;
    }

    @Override
    public int getRoomQuantity() {
        OneLinkedNode node = head;
        Office office;
        office = (Office) node.getOffice();
        int roomTotal = office.getQuantityRoom();
        while (node.getNextNode() != head) {
            node = node.getNextNode();
            office = (Office) node.getOffice();
            roomTotal += office.getQuantityRoom();
        }
        return roomTotal;
    }

    //проверить
    @Override
    public Office[] getSpaceArray() {
        OneLinkedNode node = head;
        Office[] offices = new Office[getSpaceQuantity()];
        int index = 0;
        offices[index] = (Office) node.getOffice();
        while (node.getNextNode() != head) {
            node = node.getNextNode();
            index++;
            offices[index] = (Office) node.getOffice();
        }
        return offices;
    }

    @Override
    public Space getSpace(int numberOffice) {
        if (numberOffice > getSpaceQuantity() - 1 || numberOffice < 0) {
            throw new SpaceIndexOutOfBoundsException();
        }

        return getNode(numberOffice).getOffice();
    }

    @Override
    public void setSpace(int numberOffice, Space office) {
        if (numberOffice > getSpaceQuantity() - 1 || numberOffice < 0) {
            throw new SpaceIndexOutOfBoundsException();
        }

        OneLinkedNode node;
        node = getNode(numberOffice);
        node.setOffice(office);
    }

    @Override
    public void insertSpace(int numberOffice, Space office) {
        if (numberOffice > getSpaceQuantity() - 1 || numberOffice < 0) {
            throw new SpaceIndexOutOfBoundsException();
        }

        OneLinkedNode node = new OneLinkedNode((Office) office);
        insertNode(node, numberOffice);
    }

    @Override
    public void removeSpace(int numberNode) {
        if (numberNode > getSpaceQuantity() - 1 || numberNode < 0) {
            throw new SpaceIndexOutOfBoundsException();
        }

        removeNode(numberNode);
    }

    @Override
    public Office getBestSpace() {
        Office bestOffice = (Office) head.getOffice();
        OneLinkedNode node = head;
        while (node.getNextNode() != head) {
            node = node.getNextNode();
            Office tempOffice = (Office) node.getOffice();
            if (tempOffice.getArea() > bestOffice.getArea()) {
                bestOffice = tempOffice;
            }
        }
        return bestOffice;
    }

    @Override
    public Iterator iterator() {
        return new OfficeFloorIterator(head);
    }

    @Override
    public String toString() {
        Office[] offices = getSpaceArray();
        StringBuffer string = new StringBuffer("OfficeFloor" + "(" + offices.length);
        for (Office office : offices) {
            string.append(", ").append(office.toString());
        }
        string.append(")");
        return string.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (!object.getClass().getCanonicalName().equals("buildings.office.OfficeFloor")) {
            return false;
        }
        OfficeFloor officeFloor = (OfficeFloor) object;
        if (!(officeFloor.getSpaceQuantity() == this.getSpaceQuantity())) {
            return false;
        }
        int spaceQuantity = this.getSpaceQuantity();
        OneLinkedNode node = head;
        OneLinkedNode node2 = officeFloor.head;
        for (int i = 0; i < spaceQuantity; i++) {
            if (!node.equals(node2)){
                return false;
            }
            node = node.getNextNode();
            node2 = node2.getNextNode();
        }
        return true;
    }

    //какой способ лучше? с ду вайлом или с фором?
    @Override
    public int hashCode() {
        int hashCode = 0;
        OneLinkedNode node = head;
        do{
            hashCode = hashCode ^ node.getOffice().hashCode();
            node = node.getNextNode();
        }while(node != head);
        return hashCode ^ getSpaceQuantity();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {        
        OneLinkedNode newHead = (OneLinkedNode) this.head.clone();
        OneLinkedNode newNode = newHead;
        OneLinkedNode thisNode = head;
        int officeQuantity = this.getSpaceQuantity();
        for (int i = 1; i < officeQuantity; i++) {
            newNode.setNextNode((OneLinkedNode) thisNode.getNextNode().clone());
            newNode = newNode.getNextNode();
            thisNode = thisNode.getNextNode();
        }
        newNode.setNextNode(newHead);
        return new OfficeFloor(newHead);
    }

//    public Office[] spaceArrayClone() throws CloneNotSupportedException {
//        OneLinkedNode node = head;
//        Office[] offices = new Office[getSpaceQuantity()];
//        int index = 0;
//        offices[index] = (Office) node.getOffice().clone();
//        while (node.getNextNode() != head) {
//            node = node.getNextNode();
//            index++;
//            offices[index] = (Office) node.getOffice().clone();
//        }
//        return offices;
//    }
}
