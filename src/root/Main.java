package root;

import player.Computer;
import player.Human;

public class Main {

    public static void main(String[] args) {
        Human human = new Human();
        Computer computer = new Computer();

        human.setShip();
        computer.setShip();

        human.setShot();
        computer.setShot();
    }
}
