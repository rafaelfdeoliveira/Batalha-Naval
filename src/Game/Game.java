package Game;

import Board.Ship;
import Board.Submarine;
import Player.Computer;
import Player.Player;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Game {

    Scanner sc = new Scanner(System.in);

    public Game() {
        String humanPlayerName = getPlayerName();
        List<Ship> fleet = getFleet();
        Player humanPlayer = new Player(humanPlayerName, fleet);
        Computer computerPlayer = new Computer(fleet);
        playGame(humanPlayer, computerPlayer);
    }

    private String getPlayerName() {
        String humanPlayerName;
        while (true) {
            System.out.print("Insert the Player's name: ");
            humanPlayerName = sc.nextLine();

            if (humanPlayerName.toLowerCase().equals("computer")) {
                System.out.println(" this name already used your opponent");
                continue;
            }
            break;
        }
        return humanPlayerName.length() >= 40 ? humanPlayerName.substring(0, 41) : humanPlayerName;
    }

    private List<Ship> getFleet() {
        Ship[] fleet = new Ship[10];
        for (int i = 0; i < fleet.length; i++) {
            fleet[i] = new Submarine();
        }
        return Arrays.asList(fleet);
    }

    private Player getStartingPlayer(Player humanPlayer, Computer computerPlayer) {
        while (true) {
            System.out.printf("Who starts the game, %s? (h = human or c = computer): ", humanPlayer.name);
            String answer = sc.nextLine();

            if (answer.length() == 0) continue;

            if (answer.toLowerCase().charAt(0) == 'h') return humanPlayer;
            if (answer.toLowerCase().charAt(0) == 'c') return computerPlayer;
            System.out.println("Invalid answer");
        }
    }

    private Player getOtherPlayer(Player player, Player humanPlayer, Computer computerPlayer) {
        if (player == humanPlayer) return computerPlayer;
        return humanPlayer;
    }


    // Uncomment the line below to make sure the human player will always win
//    private void shootAtAllPositionsInOpponentBoard(Player winnerPlayer, Player loserPlayer) {
//        String[] rows = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
//        for (int i = 0; i < rows.length; i++) {
//            for (int column = 0; column < 10; column++) {
//                try {
//                    winnerPlayer.board.placeShot(rows[i] + column, loserPlayer.board);
//                } catch (Exception ignore) {
//                }
//            }
//        }
//    }

    private void playGame(Player humanPlayer, Computer computerPlayer) {
        Player currentPlayer = getStartingPlayer(humanPlayer, computerPlayer);
        Player opponentPlayer = getOtherPlayer(currentPlayer, humanPlayer, computerPlayer);

        while (true) {

            boolean gameOver = false;
            while (!gameOver) {
                // Uncomment the line below to make sure the computer will always win as the humanPlayer will never play
//                if (currentPlayer == humanPlayer) {
//                    currentPlayer = computerPlayer;
//                    opponentPlayer = humanPlayer;
//                    continue;
//                }

                // Uncomment the lines below to make sure the human player will always win
//                if (currentPlayer == humanPlayer) {
//                    shootAtAllPositionsInOpponentBoard(humanPlayer, computerPlayer);
//                    gameOver = true;
//                    opponentPlayer = humanPlayer;
//                    continue;
//                }

                currentPlayer.shoot(opponentPlayer.board);
                gameOver = currentPlayer.board.hasWon();
                if (currentPlayer == humanPlayer) {
                    currentPlayer.board.showBoard();
                    computerPlayer.board.showScore("Player");
                    humanPlayer.board.showScore(computerPlayer.name);
                }
                opponentPlayer = currentPlayer;
                currentPlayer = getOtherPlayer(currentPlayer, humanPlayer, computerPlayer);
            }

            humanPlayer.board.clearScreen();
            humanPlayer.board.showBoard();

            System.out.printf("%s won the Game!%n", opponentPlayer.name);

            if (getBooleanAnswer("Would you like to see the enemy board, %s? (y or n): ", humanPlayer.name))
                computerPlayer.board.showBoard();

            if (!getBooleanAnswer("Do you want to play again, %s? (y or n): ", humanPlayer.name)) break;

            humanPlayer.board.clearScreen();
            humanPlayer.resetPlayer();
            computerPlayer.resetPlayer();
        }
    }

    private boolean getBooleanAnswer(String question, String playerName) {
        while (true) {
            System.out.printf(question, playerName);
            String answer = sc.nextLine();
            if (answer.toLowerCase().equals("y")) return true;
            if (answer.toLowerCase().equals("n")) return false;
            System.out.println("Answer y or n");
        }
    }

}
