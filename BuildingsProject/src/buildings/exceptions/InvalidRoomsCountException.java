
package buildings.exceptions;

public class InvalidRoomsCountException 
    extends IllegalArgumentException
{
    public InvalidRoomsCountException()
    {
        super("Ошибка некорретного количества комнат в помещении");
    }
}
