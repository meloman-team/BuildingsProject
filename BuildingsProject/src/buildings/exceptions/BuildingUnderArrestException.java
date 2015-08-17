
package buildings.exceptions;

public class BuildingUnderArrestException extends Exception {

    public BuildingUnderArrestException() {
        super("Здание под арестом");
    }
    
    @Override
    public String toString(){
        return "Здание под арестом";
    }
}
