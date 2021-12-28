package Board;

public abstract class Ship {
    public final int length;
    public final String name;
    public Ship (String name, int length) {
        this.length = length;
        this.name = name;
    }
}
