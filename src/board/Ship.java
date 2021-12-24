package board;

public abstract class Ship {
    int length;
    String name;
    public Ship (String name, int length) {
        this.length = length;
        this.name = name;
    }
}
