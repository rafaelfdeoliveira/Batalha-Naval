package exceptions;

public class IncorrectBoardCoordinateException extends Exception {
    public IncorrectBoardCoordinateException () {
        super();
        System.out.println("Incorrect Board Coordinate");
    }
}