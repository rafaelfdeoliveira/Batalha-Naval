package game;

import board.Ship;
import board.Submarine;
import player.Computer;
import player.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Game {

    Scanner sc = new Scanner(System.in);

    public Game () {
        String humanPlayerName = getAnswer("Insert the Player's name: ");
        List<Ship> fleet= getFleet();
        Player humanPlayer = new Player(humanPlayerName, fleet);
        Computer computerPlayer = new Computer(fleet);
        playGame(humanPlayer, computerPlayer);
    }

    private String getAnswer (String message) {
        System.out.print(message);
        return sc.nextLine();
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
            System.out.printf("Who starts the game, %s? (human or computer): ", humanPlayer.getName());
            String answer = sc.nextLine();
            if (answer == "human") return humanPlayer;
            if (answer == "computer") return computerPlayer;
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
        boolean gameOver = false;
        while (!gameOver) {
            String shot = currentPlayer.shoot();
            opponentPlayer.board.getOpponentShot(shot);
            gameOver = currentPlayer.board.hasWon();
            currentPlayer.board.showBoard();
            opponentPlayer = currentPlayer;
            currentPlayer = getOtherPlayer(currentPlayer, humanPlayer, computerPlayer);
        }
    }
}
