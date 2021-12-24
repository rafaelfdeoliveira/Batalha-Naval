package player;

import board.Board;
import board.Ship;
import board.Submarine;

import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Player {
    Scanner sc = new Scanner(System.in);
    Random random = new Random();

    String name;
    public Board board;
    List<Ship> fleet;


    public Player(String name, List<Ship> fleet) {
        this.name = name;
        this.fleet = fleet;
        this.board = new Board();
        this.positionFleet();
    }

    public String getName () { return name; }

    public Board getBoard() { return board; }

    void positionFleet () {
        for (Ship ship : this.fleet) {
            if (ship instanceof Submarine) placeSubmarine();
            // Can implement the logic to position ships bigger than a submarine here (ships with length greater than 1)
        }
    }

    void placeSubmarine () {
        while (true) {
            System.out.print("Choose a spot to place a submarine (eg. B2): ");
            String shipSpot = sc.nextLine();
            try {
                this.board.placeShip(shipSpot);
                break;
            } catch (Exception e) { }
        }
    }

//    public String[] setShip() {
//        System.out.println("Pronto para posicionar seu navio...");
//        System.out.print("Informe uma linha e uma coluna (Ex: A1):");
//        String impPlayer = this.sc.nextLine();
//        //Excluir Linha
//        System.out.println(impPlayer.toUpperCase(Locale.ROOT));
//        String[] ship = setArrayShot(impPlayer);
//        return ship;
//    }

    public String[] setShot() {
        System.out.println("Pronto para fazer sua jogada...");
        System.out.print("Informe uma linha e uma coluna (Ex: A1):");
        String impPlayer = this.sc.nextLine();
        //Excluir Linha
        System.out.println(impPlayer.toUpperCase(Locale.ROOT));
        String[] shot = setArrayShot(impPlayer);
        return shot;
    }

    //TRATAMENTO DA ENTRADA DO JOGADOR
    String[] setArrayShot(String impPlayer) {
        String[] arrayShot = new String[2];
        for (int i = 0; i < arrayShot.length; i++) {
            arrayShot[i] = impPlayer.toUpperCase(Locale.ROOT).substring(i, i + 1);
        }
        return arrayShot;
    }


}
