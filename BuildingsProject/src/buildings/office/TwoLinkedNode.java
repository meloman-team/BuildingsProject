package buildings.office;

import buildings.interfaces.Floor;
import java.io.Serializable;

public class TwoLinkedNode implements Serializable, Cloneable {

    private TwoLinkedNode nextNode;
    private Floor floor;
    private TwoLinkedNode previousNode;

    TwoLinkedNode() {
        nextNode = null;
        previousNode = null;
        floor = new OfficeFloor();
    }

    TwoLinkedNode(int officeQuantity) {
        nextNode = null;
        previousNode = null;
        floor = new OfficeFloor(officeQuantity);
    }

    TwoLinkedNode(Floor floor) {
        this.floor = floor;
        nextNode = null;
        previousNode = null;
    }

    TwoLinkedNode(TwoLinkedNode nextNode, Floor floor) {
        this.floor = floor;
        this.nextNode = nextNode;
        previousNode = null;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public void setNextNode(TwoLinkedNode nextNode) {
        this.nextNode = nextNode;
    }

    public void setPreviousNode(TwoLinkedNode previousNode) {
        this.previousNode = previousNode;
    }

    public Floor getFloor() {
        return floor;
    }

    public TwoLinkedNode getNextNode() {
        return nextNode;
    }

    public TwoLinkedNode getPreviousNode() {
        return previousNode;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (!object.getClass().getCanonicalName().equals("buildings.office.TwoLinkedNode")) {
            return false;
        }
        TwoLinkedNode node = (TwoLinkedNode) object;
        if (!floor.equals(node.getFloor())) {
            return false;
        }
        return true;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        TwoLinkedNode node = (TwoLinkedNode) super.clone();
        node.floor = (Floor) floor.clone();
        return node;
    }
    
}
