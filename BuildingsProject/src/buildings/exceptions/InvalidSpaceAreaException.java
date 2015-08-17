
package buildings.exceptions;

public class InvalidSpaceAreaException 
    extends IllegalArgumentException
{
    public InvalidSpaceAreaException()
    {
        super ("Ошибка некорретной площади помещения");   
    }
}
