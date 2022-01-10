package Player;

import Board.Board;
import Board.Fields;
import Board.Ship;
import Board.Submarine;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Player {
    Scanner sc = new Scanner(System.in);
    Random random = new Random();

    public final String name;
    public final Board board;
    final List<Ship> fleet;

    public Player(String name, List<Ship> fleet) {
        this.name = name;
        this.fleet = fleet;
        this.board = new Board(name, getHitsToWin(fleet));
        this.positionFleet();
    }

    int getHitsToWin (List<Ship> fleet) {
        int hitsToWin = 0;
        for (Ship ship: fleet) {
            for (int i = 0; i < ship.length; i++) hitsToWin++;
        }
        return hitsToWin;
    }

    void positionFleet () {
        if (shouldPositionFleetRandomly()) {
            for (Ship ship : fleet) {
                if (ship instanceof Submarine) placeSubmarineAutomatically();
                // Can implement the logic to position ships bigger than a submarine here (ships with length greater than 1)
            }
            board.showBoard();
        }
        else {
            for (Ship ship : fleet) {
                if (ship instanceof Submarine) placeSubmarine();
                // Can implement the logic to position ships bigger than a submarine here (ships with length greater than 1)
                board.showBoard();
            }
        }
    }

    boolean shouldPositionFleetRandomly () {
        while (true) {
            System.out.printf("Do you want to position your fleet randomly, %s? (y or n) ", name);
            String answer = sc.nextLine();
            if (answer.toLowerCase().equals("y")) return true;
            if (answer.toLowerCase().equals("n")) return false;
            System.out.println("Answer y or n");
        }
    }

    void placeSubmarineAutomatically () {
        while (true) {
            String shipSpot = Fields.getRowLetter(random.nextInt(10) + 1) + (random.nextInt(10) + 1);
            try {
                board.placeShip(shipSpot);
                break;
            } catch (Exception ignored) { }
        }
    }

    void placeSubmarine () {
        while (true) {
            System.out.print("Choose a spot to place a submarine (eg. B2): ");
            String shipSpot = sc.nextLine();
            try {
                board.placeShip(shipSpot.toUpperCase());
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void shoot (Board opponentBoard) {
        while (true) {
            System.out.print("Choose a spot to shoot (eg. B2): ");
            String shotSpot = sc.nextLine();
            try {
                board.placeShot(shotSpot.toUpperCase(), opponentBoard);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
