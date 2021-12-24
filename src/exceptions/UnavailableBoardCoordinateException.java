package exceptions;

public class UnavailableBoardCoordinateException extends Exception {
    public UnavailableBoardCoordinateException () {
        super();
        System.out.println("Board Coordinate unavailable");
    }
}
