package board;

import java.util.Arrays;
import java.io.IOException;

public class Board {

    private final String[][] board = new String[11][11];
    private final int hitsToWin;

    public Board (int hitsToWin) {
        fillBoard();
        this.hitsToWin = hitsToWin;
    }

    public void showBoard() {
        clearScreen();
        getBoardHeader();
        for (String[] column : this.board) {
            System.out.print("| ");
            for (String row : column) {
                System.out.print(row + " | ");
            }
            System.out.println("\n-------------------------------------------------------");
        }
    }

    private void checkValidCoordinate (String coordinate) throws Exception {
        if ((coordinate.length() != 2 && coordinate.length() != 3) || !Fields.containsRow(coordinate.substring(0, 1)) || !Fields.containsColumn(coordinate.substring(1))) {
            throw new Exception("Incorrect Board Coordinate");
        }
    }

    public void placeShip (String shipSpot) throws Exception {
        checkValidCoordinate(shipSpot);
        int row = Fields.valueOf(shipSpot.substring(0, 1)).rowTitle;
        int column = Integer.parseInt(shipSpot.substring(1));
        if (hasShipInSpot(row, column)) throw new Exception("Board Coordinate unavailable");
        this.board[row][column] = "N";
    }

    private boolean hasShipInSpot (int row, int column) {
        return this.board[row][column].equals("N") || this.board[row][column].equals("n");
    }

    public void placeShot (String shotCoordinate, Board opponentBoard) throws Exception {
        checkValidCoordinate(shotCoordinate);
        int rowNumber = Fields.valueOf(shotCoordinate.substring(0, 1)).rowTitle;
        int column = Integer.parseInt(shotCoordinate.substring(1));
        if (!board[rowNumber][column].equals("  ") &&
            !board[rowNumber][column].equals("N")) throw new Exception("Already shot at this spot");
        boolean shotHit = opponentBoard.getOpponentShot(rowNumber, column);
        if (board[rowNumber][column].equals("  ")) {
            if (shotHit) board[rowNumber][column] = "*";
            else board[rowNumber][column] = "-";
        }
        else {
            if (shotHit) board[rowNumber][column] = "X";
            else board[rowNumber][column] = "n";
        }
    }

    private boolean getOpponentShot (int rowNumber, int column) {
        if (board[rowNumber][column].equals("N")) {
            board[rowNumber][column] = "  ";
            return true;
        }
        if (board[rowNumber][column].equals("X")) {
            board[rowNumber][column] = "*";
            return true;
        }
        if (board[rowNumber][column].equals("n")) {
            board[rowNumber][column] = "-";
            return true;
        }
        return false;
    }

    private void fillBoard () {
        char[] columnHead = new char[] { ' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J' };
        for (String[] row : board)
            Arrays.fill(row, "  ");
        for (int i = 0; i < 11; i++) {
            String header = i == 0 ? " " : Integer.toString(i);
            header = header.length() < 2 && !header.equals(" ") ? "0" + header : header;
            board[0][i] = header;
        }
        for (int i = 1; i < 11; i++)
            board[i][0] = Character.toString(columnHead[i]);
    }

    private void getBoardHeader () {
        System.out.println("-------------------------------------------------------");
        System.out.println("|                       JOGADOR                       |");
        System.out.println("-------------------------------------------------------");
    }

    public boolean hasWon () {
        int hitsOnEnemyShips = 0;
        for (int i = 1; i < board.length; i++) {
            for (int j = 1; j < board[i].length; j++) {
                if (board[i][j].equals("*") || board[i][j].equals("X")) hitsOnEnemyShips++;
            }
        }
        return hitsOnEnemyShips == hitsToWin;
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

}
