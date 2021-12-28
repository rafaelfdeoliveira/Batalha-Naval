package Player;

import Board.Board;
import Board.Fields;
import Board.Ship;
import Board.Submarine;

import java.util.List;

public class Computer extends Player {

    public Computer (List<Ship> fleet) {
        super("Computer", fleet);
    }

    @Override
    void positionFleet () {
        for (Ship ship : fleet) {
            if (ship instanceof Submarine) placeSubmarineAutomatically();
            // Can implement the logic to position ships bigger than a submarine here (ships with length greater than 1)
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
