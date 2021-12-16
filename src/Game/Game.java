package Game;

import java.util.Scanner;

public class Game {
    public static void Game () {
        Player humanPlayer = new Player();
        ComputerPlayer computerPlayer = new ComputerPlayer();
        Ship[] fleet= getFleet();
        humanPlayer.placeShips(fleet);
        computerPlayer.placeShips(fleet);
        playGame(humanPlayer, computerPlayer);
    }

    private static Ship[] getFleet () {
        Ship[] fleet = new Ship[10];
        for (int i = 0; i < fleet.length; i++) {
            fleet[i] = new Submarine();
        }
        return fleet;
    }

    private static Player getStartingPlayer (Player humanPlayer, ComputerPlayer computerPlayer) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.printf("Who starts the game, %s? (human or computer): ", humanPlayer.name);
            String answer = sc.nextLine();
            if (answer == "human") return humanPlayer;
            if (answer == "computer") return computerPlayer;
            System.out.println("Invalid answer");
        }
    }

    private static Player getOtherPlayer (Player player, Player humanPlayer, ComputerPlayer computerPlayer) {
        if (player == humanPlayer) return computerPlayer;
        return humanPlayer;
    }

    private static void playGame (Player humanPlayer, ComputerPlayer computerPlayer) {
        Player currentPlayer = getStartingPlayer(humanPlayer, computerPlayer);
        Player opponentPlayer = getOtherPlayer(currentPlayer, humanPlayer, computerPlayer);
        boolean gameOver = false;
        while (!gameOver) {
            String shot = currentPlayer.shoot();
            opponentPlayer.board.getOpponentShot(shot);
            gameOver = currentPlayer.board.hasWon();
            opponentPlayer = currentPlayer;
            currentPlayer = getOtherPlayer(currentPlayer, humanPlayer, computerPlayer);
        }
    }
}
