package Game;

import Board.Ship;
import Board.Submarine;
import Player.Computer;
import Player.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Game {

    Scanner sc = new Scanner(System.in);

    public Game () {
        System.out.print("Insert the Player's name: ");
        String humanPlayerName = sc.nextLine();
        List<Ship> fleet= getFleet();
        Player humanPlayer = new Player(humanPlayerName, fleet);
        Computer computerPlayer = new Computer(fleet);
        playGame(humanPlayer, computerPlayer);
    }

    private List<Ship> getFleet () {
        Ship[] fleet = new Ship[10];
        for (int i = 0; i < fleet.length; i++) {
            fleet[i] = new Submarine();
        }
        return Arrays.asList(fleet);
    }

    private Player getStartingPlayer (Player humanPlayer, Computer computerPlayer) {
        while (true) {
            System.out.printf("Who starts the game, %s? (human or computer): ", humanPlayer.name);
            String answer = sc.nextLine();
            if (answer.equals("human")) return humanPlayer;
            if (answer.equals("computer")) return computerPlayer;
            System.out.println("Invalid answer");
        }
    }

    private Player getOtherPlayer (Player player, Player humanPlayer, Computer computerPlayer) {
        if (player == humanPlayer) return computerPlayer;
        return humanPlayer;
    }

    private void playGame (Player humanPlayer, Computer computerPlayer) {
        Player currentPlayer = getStartingPlayer(humanPlayer, computerPlayer);
        Player opponentPlayer = getOtherPlayer(currentPlayer, humanPlayer, computerPlayer);
        do {
            boolean gameOver = false;
            while (!gameOver) {
                currentPlayer.shoot(opponentPlayer.board);
                gameOver = currentPlayer.board.hasWon();
                if (currentPlayer == humanPlayer) currentPlayer.board.showBoard();
                opponentPlayer = currentPlayer;
                currentPlayer = getOtherPlayer(currentPlayer, humanPlayer, computerPlayer);
            }
            humanPlayer.board.showBoard();
            System.out.printf("%s won the Game!%n", opponentPlayer.name);
        } while (shouldPlayAgain(humanPlayer.name));
    }

    private boolean shouldPlayAgain (String playerName) {
        while (true) {
            System.out.printf("Do you want to play again, %s? (y or n)", playerName);
            String answer = sc.nextLine();
            if (answer.equals("y")) return true;
            if (answer.equals("n")) return false;
            System.out.println("Answer y or n");
        }
    }
}
