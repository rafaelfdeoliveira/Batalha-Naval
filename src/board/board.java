package board;

import java.util.Arrays;
import java.io.IOException;

public class board {

    private String[][] board = new String[11][11];

    public board() {
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

    public boolean makeAMove(char row, int column) {

        int columnHeader = fields.valueOf(Character.toString(row)).rowTitle;

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
