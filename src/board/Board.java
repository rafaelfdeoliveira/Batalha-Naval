package board;

import exceptions.IncorrectBoardCoordinateException;
import exceptions.UnavailableBoardCoordinateException;

import java.util.Arrays;
import java.io.IOException;

public class Board {

    private String[][] board = new String[11][11];

    public Board() {
        fillBoard();
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

    public void placeShip (String shipSpot) throws Exception {
        if (shipSpot.length() != 2 || !Fields.containsRow(shipSpot.substring(0, 1)) || !Fields.containsColumn(shipSpot.substring(1))) {
            throw new IncorrectBoardCoordinateException();
        }
        int row = Fields.valueOf(shipSpot.substring(0, 1)).rowTitle;
        int column = Integer.parseInt(shipSpot.substring(1));
        if (hasShipInSpot(row, column)) throw new UnavailableBoardCoordinateException();
        this.board[row][column] = "N";
    }

    private boolean hasShipInSpot (int row, int column) {
        if (this.board[row][column].equals("N") || this.board[row][column].equals("n")) return true;
        return false;
    }

    public boolean makeAMove(char row, int column) {

        int columnHeader = Fields.valueOf(Character.toString(row)).rowTitle;

        if (board[columnHeader][column] != "  ")
            return false;

        board[columnHeader][column] = "--";
        return true;
    }

    private void fillBoard() {
        char[] columnHead = new char[] { ' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J' };

        for (String[] row : board)
            Arrays.fill(row, "  ");

        for (int i = 0; i < 11; i++) {
            String header = i == 0 ? " " : Integer.toString(i);
            header = header.length() < 2 && header != " " ? "0" + header : header == " " ? " " : header;
            board[0][i] = header;
        }

        for (int i = 1; i < 11; i++)
            board[i][0] = Character.toString(columnHead[i]);

    }

    private void getBoardHeader() {
        System.out.println("-------------------------------------------------------");
        System.out.println("|                       JOGADOR                       |");
        System.out.println("-------------------------------------------------------");
    }

    private void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "clear").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {
        }
    }

}
