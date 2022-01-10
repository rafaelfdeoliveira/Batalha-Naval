package Board;

import java.util.Arrays;
import java.io.IOException;

public class Board {

    private final char[][] board = new char[11][11];
    private final int hitsToWin;
    private final String playerName;

    public Board (String playerName, int hitsToWin) {
        fillBoard();
        this.playerName = playerName;
        this.hitsToWin = hitsToWin;
    }

    public void showBoard() {
        clearScreen();
        showBoardHeader();
        for (char[] column : board) {
            System.out.print("| ");
            for (char row : column) {
                System.out.print(row + " | ");
            }
            System.out.println("\n---------------------------------------------");
        }
    }

    private void clearScreen () {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ignored) {
        }
    }

    private void checkValidCoordinate (String coordinate) throws Exception {
        if ((coordinate.length() != 2) || !Fields.containsRow(coordinate.substring(0, 1)) || !Fields.containsColumn(coordinate.substring(1))) {
            throw new Exception("Incorrect Board Coordinate");
        }
    }

    public void placeShip (String shipSpot) throws Exception {
        checkValidCoordinate(shipSpot);
        int row = Fields.valueOf(shipSpot.substring(0, 1)).rowTitle;
        int column = Integer.parseInt(shipSpot.substring(1)) + 1;
        if (hasShipInSpot(row, column)) throw new Exception("Unavailable Board Coordinate");
        this.board[row][column] = 'N';
    }

    private boolean hasShipInSpot (int row, int column) {
        return board[row][column] == 'N' || board[row][column] == 'n';
    }

    public void placeShot (String shotCoordinate, Board opponentBoard) throws Exception {
        checkValidCoordinate(shotCoordinate);
        int rowNumber = Fields.valueOf(shotCoordinate.substring(0, 1)).rowTitle;
        int column = Integer.parseInt(shotCoordinate.substring(1)) + 1;
        if (board[rowNumber][column] != ' ' &&
            board[rowNumber][column] != 'N') throw new Exception("Already shot at this spot");
        boolean shotHit = opponentBoard.getOpponentShot(rowNumber, column);
        if (shotHit) System.out.printf("%s hit an Enemy Ship%n", playerName);
        if (board[rowNumber][column] == ' ') {
            if (shotHit) board[rowNumber][column] = '*';
            else board[rowNumber][column] = '-';
        }
        else {
            if (shotHit) board[rowNumber][column] = 'X';
            else board[rowNumber][column] = 'n';
        }
    }

    private boolean getOpponentShot (int rowNumber, int column) {
        if (board[rowNumber][column] == 'N') {
            board[rowNumber][column] = ' ';
            return true;
        }
        if (board[rowNumber][column] == 'X') {
            board[rowNumber][column] = '*';
            return true;
        }
        if (board[rowNumber][column] == 'n') {
            board[rowNumber][column] = '-';
            return true;
        }
        return false;
    }

    private void fillBoard () {
        for (char[] row : board) Arrays.fill(row, ' ');
        for (int i = 1; i <= Fields.values().length; i++) board[0][i] = Integer.toString(i - 1).charAt(0);
        for (int i = 1; i <= Fields.values().length; i++) board[i][0] = Fields.values()[i - 1].toString().charAt(0);
    }

    private void showBoardHeader () {
        String emptySpacesBefore = " ".repeat((43 - playerName.length()) / 2);
        String emptySpacesAfter = playerName.length() % 2 == 0 ? emptySpacesBefore + " " : emptySpacesBefore;
        System.out.println("---------------------------------------------");
        System.out.println("|" + emptySpacesBefore + playerName.toUpperCase() + emptySpacesAfter + "|");
        System.out.println("---------------------------------------------");
    }

    public boolean hasWon () {
        int hitsOnEnemyShips = 0;
        for (int i = 1; i < board.length; i++) {
            for (int j = 1; j < board[i].length; j++) {
                if (board[i][j] == '*' || board[i][j] == 'X') hitsOnEnemyShips++;
            }
        }
        return hitsOnEnemyShips == hitsToWin;
    }

}
