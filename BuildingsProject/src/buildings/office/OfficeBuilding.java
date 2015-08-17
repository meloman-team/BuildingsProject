package buildings.office;

import buildings.interfaces.Building;
import buildings.interfaces.Space;
import buildings.interfaces.Floor;
import buildings.exceptions.SpaceIndexOutOfBoundsException;
import buildings.exceptions.FloorIndexOutOfBoundsException;
import iterator.OfficeBuildingIterator;
import java.io.Serializable;
import java.util.Iterator;

public class OfficeBuilding implements Building, Serializable {

    public class NumberInBuilding {

        private Floor floor;
        private int numberOffice;

        NumberInBuilding() {
        }

        NumberInBuilding(Floor floor, int numberOffice) {
            this.floor = floor;
            this.numberOffice = numberOffice;
        }

        public int getNumberOffice() {
            return numberOffice;
        }

        public Floor getFloor() {
            return floor;
        }

        public void setNumberOffice(int numberOffice) {
            this.numberOffice = numberOffice;
        }

        public void setFloor(Floor floor) {
            this.floor = floor;
        }
    }

    private TwoLinkedNode head;
    
    private OfficeBuilding(TwoLinkedNode head) {
        this.head = head;
    }

    public OfficeBuilding(int floorQuantity, int[] officeQuantity) {
        if (floorQuantity > officeQuantity.length || floorQuantity < 1) {
            throw new SpaceIndexOutOfBoundsException();
        }

        TwoLinkedNode node;
        node = new TwoLinkedNode(officeQuantity[0]);
        head = node;
        for (int i = 1; i < floorQuantity; i++) {
            TwoLinkedNode tempNode = new TwoLinkedNode(officeQuantity[i]);
            tempNode.setPreviousNode(node);
            node.setNextNode(tempNode);
            node = node.getNextNode();
        }
        head.setPreviousNode(node);
        node.setNextNode(head);
    }

    public OfficeBuilding(Floor[] officeFloor) {
        TwoLinkedNode node = new TwoLinkedNode(officeFloor[0]);
        head = node;
        for (int i = 1; i < officeFloor.length; i++) {
            TwoLinkedNode tempNode = new TwoLinkedNode(officeFloor[i]);
            tempNode.setPreviousNode(node);
            node.setNextNode(tempNode);
            node = node.getNextNode();
        }
        head.setPreviousNode(node);
        node.setNextNode(head);
    }

    private TwoLinkedNode getNode(int numberNode) {
        if (numberNode > getFloorQuantity() - 1 || numberNode < 0) {
            throw new FloorIndexOutOfBoundsException();
        }

        TwoLinkedNode node = head;
        for (int i = 0; i < numberNode; i++) {
            node = node.getNextNode();
        }
        return node;
    }

    private void insertNode(TwoLinkedNode nodeForInssert, int numberNode) {
        if (numberNode > getFloorQuantity() - 1 || numberNode < 0) {
            throw new FloorIndexOutOfBoundsException();
        }

        TwoLinkedNode node = head;
        if (numberNode == 0) {
            nodeForInssert.setNextNode(head);
            nodeForInssert.setPreviousNode(head.getPreviousNode());
            head = nodeForInssert;
        } else {
            for (int i = 0; i < numberNode; i++) {
                node = node.getNextNode();
            }
            nodeForInssert.setNextNode(node);
            nodeForInssert.setPreviousNode(node.getPreviousNode());
            node.setNextNode(nodeForInssert);
        }
    }

    @Override
    public void setFloor(int numberFloor, Floor floor) {
        if (numberFloor > getFloorQuantity() - 1 || numberFloor < 0) {
            throw new FloorIndexOutOfBoundsException();
        }
        TwoLinkedNode newNode = new TwoLinkedNode(floor);
        TwoLinkedNode node = head;
        if (numberFloor == 0) {
            head.getPreviousNode().setNextNode(newNode);
            head.getNextNode().setPreviousNode(newNode);
            newNode.setNextNode(head.getNextNode());
            newNode.setPreviousNode(head.getPreviousNode());
            head = newNode;
        } else {
            for (int i = 0; i < numberFloor; i++) {
                node = node.getNextNode();
            }
            newNode.setPreviousNode(node.getPreviousNode());
            newNode.setNextNode(node.getNextNode());
            node.getPreviousNode().setNextNode(newNode);
            node.getNextNode().setPreviousNode(newNode);
        }
    }

    private void removeNode(int numberNode) {
        if (numberNode > getFloorQuantity() - 1 || numberNode < 0) {
            throw new FloorIndexOutOfBoundsException();
        }

        TwoLinkedNode node = head;
        if (numberNode == 0) {
            head.getPreviousNode().setNextNode(head.getNextNode());
            head.getNextNode().setPreviousNode(head.getPreviousNode());
            head = head.getNextNode();
        } else {
            for (int i = 0; i < numberNode; i++) {
                node = node.getNextNode();
            }
            node.getPreviousNode().setNextNode(node.getNextNode());
            node.getNextNode().setPreviousNode(node.getPreviousNode());
        }
    }

    private NumberInBuilding searchNumberFloorAndOffice(int numberOffice) {
        TwoLinkedNode node = head;
        NumberInBuilding buildingIndex = new NumberInBuilding();
        int countOffice = 0;
        met:
        for (int i = 0; i < getFloorQuantity(); i++) {
            for (int j = 0; j < node.getFloor().getSpaceQuantity(); j++) {
                if (countOffice == numberOffice) {
                    buildingIndex.setFloor(node.getFloor());
                    buildingIndex.setNumberOffice(j);
                    break met;
                }
                countOffice++;
            }
            node = node.getNextNode();
        }
        return buildingIndex;
    }

    @Override
    public int getFloorQuantity() {
        TwoLinkedNode node = head;
        int countFloor = 1;
        while (node.getNextNode() != head) {
            node = node.getNextNode();
            countFloor++;
        }
        return countFloor;
    }

    @Override
    public int getSpaceQuantity() {
        TwoLinkedNode node = head;
        int countOffice = node.getFloor().getSpaceQuantity();
        while (node.getNextNode() != head) {
            node = node.getNextNode();
            countOffice += node.getFloor().getSpaceQuantity();
        }
        return countOffice;
    }

    @Override
    public double getAreaTotal() {
        TwoLinkedNode node = head;
        double areaTotal = node.getFloor().getAreaTotal();
        while (node.getNextNode() != head) {
            node = node.getNextNode();
            areaTotal += node.getFloor().getAreaTotal();
        }
        return areaTotal;
    }

    @Override
    public int getRoomQuantity() {
        TwoLinkedNode node = head;
        int roomTotal = node.getFloor().getRoomQuantity();
        while (node.getNextNode() != head) {
            node = node.getNextNode();
            roomTotal += node.getFloor().getRoomQuantity();
        }
        return roomTotal;
    }

    @Override
    public Floor[] getFloorArray() {
        TwoLinkedNode node = head;
        Floor[] arrayFloor = new Floor[getFloorQuantity()];
        for (int i = 0; i < arrayFloor.length; i++) {
            arrayFloor[i] = node.getFloor();
            node = node.getNextNode();
        }
        return arrayFloor;
    }

    public Floor[] getFloorArrayClone() throws CloneNotSupportedException {
        TwoLinkedNode node = head;
        Floor[] arrayFloor = new Floor[getFloorQuantity()];
        for (int i = 0; i < arrayFloor.length; i++) {
            arrayFloor[i] = (Floor) node.getFloor().clone();
            node = node.getNextNode();
        }
        return arrayFloor;
    }

    @Override
    public Floor getFloor(int numberFloor) {
        if (numberFloor > getFloorQuantity() - 1 || numberFloor < 0) {
            throw new FloorIndexOutOfBoundsException();
        }

        return getNode(numberFloor).getFloor();
    }

    @Override
    public Space getSpace(int numberOffice) {
        if (numberOffice > getSpaceQuantity() - 1 || numberOffice < 0) {
            throw new SpaceIndexOutOfBoundsException();
        }
        NumberInBuilding buildingIndex = searchNumberFloorAndOffice(numberOffice);
        Space office = buildingIndex.getFloor().getSpace(buildingIndex.getNumberOffice());
        return office;
    }

    @Override
    public void setSpace(int numberOffice, Space office) {
        if (numberOffice > getSpaceQuantity() - 1 || numberOffice < 0) {
            throw new SpaceIndexOutOfBoundsException();
        }
        NumberInBuilding buildingIndex = searchNumberFloorAndOffice(numberOffice);
        buildingIndex.getFloor().setSpace(buildingIndex.getNumberOffice(), office);
    }

    @Override
    public void insertSpace(int numberOffice, Space office) {
        if (numberOffice > getSpaceQuantity() - 1 || numberOffice < 0) {
            throw new SpaceIndexOutOfBoundsException();
        }

        NumberInBuilding buildingIndex = searchNumberFloorAndOffice(numberOffice);
        buildingIndex.getFloor().insertSpace(buildingIndex.getNumberOffice(), office);
    }

    @Override
    public void removeSpace(int numberOffice) {
        if (numberOffice > getSpaceQuantity() - 1 || numberOffice < 0) {
            throw new SpaceIndexOutOfBoundsException();
        }

        NumberInBuilding buildingIndex = searchNumberFloorAndOffice(numberOffice);
        buildingIndex.getFloor().removeSpace(buildingIndex.getNumberOffice());
    }

    @Override
    public Space getBestSpace() {
        TwoLinkedNode node = head;
        Space bestOffice = head.getFloor().getBestSpace();
        for (int i = 0; i < getFloorQuantity(); i++) {
            if (bestOffice.getArea() < node.getFloor().getBestSpace().getArea()) {
                bestOffice = node.getFloor().getBestSpace();
            }
            node = node.getNextNode();
        }
        return bestOffice;
    }

    @Override
    public Space[] getSortingSpaceByArea() {
        Space[] array = new Office[getSpaceQuantity()];
        int index = 0;

        for (int i = 0; i < getSpaceQuantity(); i++) {
            Space[] floorOffices = getFloor(i).getSpaceArray();
            for (Space floorOffice : floorOffices) {
                array[index] = floorOffice;
                index++;
            }
        }

        Space temp;
        for (int i = 0; i < array.length; ++i) {
            for (int j = 0; j < array.length - i - 1; ++j) {
                if (array[j].getArea() < array[j + 1].getArea()) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }

    @Override
    public Iterator iterator() {
        return new OfficeBuildingIterator(head);
    }

    @Override
    public String toString() {
        Floor[] floors = getFloorArray();
        StringBuffer string = new StringBuffer("OfficeBuilding " + "(" + floors.length);
        for (Floor floor : floors) {
            string.append(", ").append(floor.toString());
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
        if (!object.getClass().getCanonicalName().equals("buildings.office.OfficeBuilding")) {
            return false;
        }
        OfficeBuilding officeBuilding = (OfficeBuilding) object;
        if (!(officeBuilding.getFloorQuantity() == this.getFloorQuantity())) {
            return false;
        }
        
        int spaceQuantity = this.getSpaceQuantity();
        TwoLinkedNode node = head;
        TwoLinkedNode node2 = officeBuilding.head;
        for (int i = 0; i < spaceQuantity; i++) {
            if (!node.equals(node2)){
                return false;
            }
            node = node.getNextNode();
            node2 = node2.getNextNode();
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hashCode = 0;
        TwoLinkedNode node = head;
        do{
            hashCode = hashCode ^ node.getFloor().hashCode();
            node = node.getNextNode();
        }while(node != head);
        return Integer.MAX_VALUE ^ hashCode ^ getSpaceQuantity();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        TwoLinkedNode newHead = (TwoLinkedNode) this.head.clone();
        TwoLinkedNode newNode = newHead;
        TwoLinkedNode thisNode = head;
        int floorQuantity = this.getFloorQuantity();
        for (int i = 1; i < floorQuantity; i++) {
            newNode.setNextNode((TwoLinkedNode) thisNode.getNextNode().clone());
            newNode.getNextNode().setPreviousNode(newNode);
            newNode = newNode.getNextNode();
            thisNode = thisNode.getNextNode();
        }
        newNode.setNextNode(newHead);
        newHead.setPreviousNode(newNode);
        return new OfficeBuilding(newHead);
    }
}
