package player;

import java.util.Locale;
import java.util.Scanner;

public class Human implements Player {
    Scanner sc = new Scanner(System.in);

    private String name;
    private String[] shot = new String[2];
    private String[] ship = new  String[2];
    private String[] shotList;

    public void setShip() {
        System.out.println("Pronto para posicionar seu navio...");
        System.out.print("Informe uma linha e uma coluna (Ex: A1):");
        String impPlayer = this.sc.nextLine();
        //Excluir Linha
        System.out.println(impPlayer.toUpperCase(Locale.ROOT));
        this.ship = setArrayShot(impPlayer);
    }

    public void setShot() {
        System.out.println("Pronto para fazer sua jogada...");
        System.out.print("Informe uma linha e uma coluna (Ex: A1):");
        String impPlayer = this.sc.nextLine();
        //Excluir Linha
        System.out.println(impPlayer.toUpperCase(Locale.ROOT));
        this.shot = setArrayShot(impPlayer);
    }

    //TRATAMENTO DA ENTRADA DO JOGADOR
    private String[] setArrayShot(String impPlayer) {
        String[] arrayShot = new String[2];
        for (int i = 0; i < arrayShot.length; i++) {
            arrayShot[i] = impPlayer.toUpperCase(Locale.ROOT).substring(i, i + 1);
        }
        return arrayShot;
    }

    public String[] getShot() {
        return shot;
    }
}
