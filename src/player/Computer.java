package player;

import java.util.Locale;

public class Computer extends Player {

    private final String name = "Computer";

    /*
    public Computer(String name, List<Ship> ships, Board board) {
        super(name, ships, board);
    }
    */
    @Override
    public String[] setShip() {
        String arrayLetter = "ABCDEFGHIJ";
        String impPlayer = (String) (String.valueOf(arrayLetter.charAt(random.nextInt(arrayLetter.length()))) + (random.nextInt(9) + 1));
        //Excluir Linha
        System.out.println(impPlayer);
        String[] ship = setArrayShot(impPlayer);
        return ship;
    }

    @Override
    public String[] setShot() {
        String arrayLetter = "ABCDEFGHIJ";
        String impPlayer = (String) (String.valueOf(arrayLetter.charAt(random.nextInt(arrayLetter.length()))) + (random.nextInt(9) + 1));
        //Excluir Linha
        System.out.println(impPlayer);
        String[] shot = setArrayShot(impPlayer);
        return shot;
    }

    //TRATAMENTO DA ENTRADA DO JOGADOR
    @
    private String[] setArrayShot(String impPlayer) {
        String[] arrayShot = new String[2];
        for (int i = 0; i < arrayShot.length; i++) {
            arrayShot[i] = impPlayer.toUpperCase(Locale.ROOT).substring(i, i + 1);
        }
        return arrayShot;
    }


}
