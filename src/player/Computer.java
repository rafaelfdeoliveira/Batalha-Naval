package player;

import board.Board;
import board.Fields;
import board.Ship;


import java.util.List;

public class Computer extends Player {

    public Computer (List<Ship> fleet) {
        super("Computer", fleet);
    }

    @Override
    void placeSubmarine () {
        while (true) {
            String shipSpot = Fields.getRowLetter(random.nextInt(10) + 1) + (random.nextInt(10) + 1);
            try {
                board.placeShip(shipSpot);
                break;
            } catch (Exception ignored) { }
        }
    }

    @Override
    public void shoot (Board opponentBoard) {
        while (true) {
            String shotSpot = Fields.getRowLetter(random.nextInt(10) + 1) + (random.nextInt(10) + 1);
            try {
                board.placeShot(shotSpot, opponentBoard);
                break;
            } catch (Exception ignored) { }
        }
    }

}
