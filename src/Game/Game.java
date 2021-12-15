package Game;

import java.util.Scanner;

public class Game {
    public static void main (HumanPlayer humanPlayer, ComputerPlayer computerPlayer, Board humanBoard, Board computerBoard) {
        Player currentPlayer = getStartingPlayer(humanPlayer, computerPlayer);
        Player opponentPlayer = getOpponentPlayer(currentPlayer, humanPlayer, computerPlayer);
        boolean gameOver = false;
        while (!gameOver) {
            boolean success = false;
            while (!success) {
                String shot = currentPlayer.shoot();
                success = currentPlayer.board.tryRegisterShot(opponentPlayer.board);
            }
            gameOver = currentPlayer.board.hasWon();
            currentPlayer = getOpponentPlayer(currentPlayer, humanPlayer, computerPlayer);
            opponentPlayer = getOpponentPlayer(currentPlayer, humanPlayer, computerPlayer);
        }
    }

    private static Player getStartingPlayer (HumanPlayer humanPlayer, ComputerPlayer computerPlayer) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.printf("Who starts the game, %s? (human or computer): ", humanPlayer.name);
            String answer = sc.nextLine();
            if (answer == "human") return humanPlayer;
            if (answer == "computer") return computerPlayer;
            System.out.println("Invalid answer");
        }
    }

    private static Player getOpponentPlayer (Player currentPlayer, HumanPlayer humanPlayer, ComputerPlayer computerPlayer) {
        if (currentPlayer == humanPlayer) return computerPlayer;
        return humanPlayer;
    }
}
