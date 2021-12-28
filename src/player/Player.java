package player;

import board.Board;
import board.Ship;
import board.Submarine;

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
        this.board = new Board(getHitsToWin(fleet));
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
        for (Ship ship : fleet) {
            if (ship instanceof Submarine) placeSubmarine();
            // Can implement the logic to position ships bigger than a submarine here (ships with length greater than 1)
        }
    }

    void placeSubmarine () {
        while (true) {
            System.out.print("Choose a spot to place a submarine (eg. B2): ");
            String shipSpot = sc.nextLine();
            try {
                board.placeShip(shipSpot);
                board.showBoard();
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
                board.placeShot(shotSpot, opponentBoard);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
