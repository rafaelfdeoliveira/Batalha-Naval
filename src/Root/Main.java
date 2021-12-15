package Root;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Board humanBoard = new Board();
        Board computerBoard = new Board();
        String humanName = askHumanPlayerName();
        // It would be better if each player creates its own board when instantiated and keeps it stored as an attribute
        HumanPlayer humanPlayer = new HumanPlayer(humanName, humanBoard);
        ComputerPlayer computerPlayer = new ComputerPlayer(computerBoard);
        Ship[] shipsArray = new Ship[10];
        for (int i = 0; i < shipsArray.length; i++) {
            shipsArray[i] = new Submarine();
        }
        placeShips(humanPlayer, shipsArray, humanBoard);
        placeShips(computerPlayer, shipsArray, computerBoard);
        new Game(humanPlayer, computerPlayer, humanBoard, computerBoard);
    }

    private static void placeShips (Player player, Ship[] shipsArray, Board board) {
        for (Ship ship : shipsArray) {
            player.placeShip(ship, board);
        }
    }

    private static String askHumanPlayerName () {
        Scanner sc = new Scanner(System.in);
        System.out.print("What is the Human Player name? ");
        return sc.nextLine();
    }

//    private static void placeShip (Submarine submarine, Board board) {
//        Scanner sc = new Scanner(System.in);
//        while (true) {
//            System.out.printf("%s, choose a spot to place a submarine (ex. B2): ", this.name);
//            String spot = sc.nextLine();
//            // board.isRowLetter and board.isColumnNumber return boolean depending on if the given Char corresponds to a row letter or not, or if it corresponds to a column number, respectively
//            if (spot.length() != 2 || !board.isRowLetter(spot.charAt(0)) || !board.isColumnNumber(spot.charAt(1))) {
//                System.out.println("Invalid answer");
//                continue;
//            }
//            boolean success = board.tryPlaceShip(submarine, spot);
//            if (success) break;
//            System.out.printf("You already have a ship in %s, %s%n", spot, this.name);
//        }
//    }

//    private static void makeComputerPlayerPlaceShip (ComputerPlayer computerPlayer, Submarine submarine, Board board) {
//        Random random = new Random();
//        boolean success = false;
//        while (!success) {
//             // int column = random.nextInt(board.columnNumbersArray.length()) + 1;
//             // char row = board.rowLettersArray[random.nextInt(board.rowLettersArray.length())];
//             String spot = computerPlayer.
//             success = board.tryPlaceShip(submarine, spot);
//        }
//    }
}
