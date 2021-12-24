package player;

import board.Fields;
import board.Ship;


import java.util.List;

public class Computer extends Player {

    public Computer (List<Ship> fleet) {
        super("Computer", fleet);
    }

    @Override
    void placeSubmarine () {
        while (true) {
            String shipSpot = Fields.getRowLetter(random.nextInt(10) + 1) + Integer.toString(random.nextInt(10) + 1);
            try {
                this.board.placeShip(shipSpot);
                break;
            } catch (Exception e) { }
        }
    }

//    @Override
//    public String[] setShip() {
//        String arrayLetter = "ABCDEFGHIJ";
//        String impPlayer = (String) (String.valueOf(arrayLetter.charAt(random.nextInt(arrayLetter.length()))) + (random.nextInt(9) + 1));
//        //Excluir Linha
//        System.out.println(impPlayer);
//        String[] ship = setArrayShot(impPlayer);
//        return ship;
//    }

    @Override
    public String[] setShot() {
        String arrayLetter = "ABCDEFGHIJ";
        String impPlayer = (String) (String.valueOf(arrayLetter.charAt(random.nextInt(arrayLetter.length()))) + (random.nextInt(9) + 1));
        //Excluir Linha
        System.out.println(impPlayer);
        String[] shot = setArrayShot(impPlayer);
        return shot;
    }

}
