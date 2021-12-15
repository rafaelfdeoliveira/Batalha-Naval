package player;

import java.util.Locale;
import java.util.Random;

public class Computer implements Player {
    Random random = new Random();

    private final String name = "Computer";
    private String[] shot = new String[2];
    private String[] ship = new  String[2];
    private String shotList;

    public void setShip() {
        String arrayLetter = "ABCDEFGHIJ";
        String impPlayer = (String) (String.valueOf(arrayLetter.charAt(random.nextInt(arrayLetter.length()))) + (random.nextInt(9) + 1));
        //Excluir Linha
        System.out.println(impPlayer);
        this.ship = setArrayShot(impPlayer);
    }

    public void setShot() {
        String arrayLetter = "ABCDEFGHIJ";
        String impPlayer = (String) (String.valueOf(arrayLetter.charAt(random.nextInt(arrayLetter.length()))) + (random.nextInt(9) + 1));
        //Excluir Linha
        System.out.println(impPlayer);
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
