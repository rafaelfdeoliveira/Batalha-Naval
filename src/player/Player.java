package player;

import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Player {
    Scanner sc = new Scanner(System.in);
    Random random = new Random();

    private String name;
  //  private Board board;
  //  private list<Ship> ship;

    /*
    public Player(String name, List<Ship> ships,Board board) {
        this.name;
        this.ships = ships;
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }
    */

    public String[] setShip() {
        System.out.println("Pronto para posicionar seu navio...");
        System.out.print("Informe uma linha e uma coluna (Ex: A1):");
        String impPlayer = this.sc.nextLine();
        //Excluir Linha
        System.out.println(impPlayer.toUpperCase(Locale.ROOT));
        String[] ship = setArrayShot(impPlayer);
        return ship;
    }

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
    private String[] setArrayShot(String impPlayer) {
        String[] arrayShot = new String[2];
        for (int i = 0; i < arrayShot.length; i++) {
            arrayShot[i] = impPlayer.toUpperCase(Locale.ROOT).substring(i, i + 1);
        }
        return arrayShot;
    }


}
