package board;

public class main {

    public static void main(String[] args) {
        board board = new board();
        board.fillBoard();
        board.showBoard();

        boolean moveResult;

        moveResult = board.makeAMove('G', 2);
        validateMovement(moveResult, board);
        moveResult = board.makeAMove('E', 3);
        validateMovement(moveResult, board);
        moveResult = board.makeAMove('C', 5);
        validateMovement(moveResult, board);
        moveResult = board.makeAMove('B', 4);
        validateMovement(moveResult, board);
        moveResult = board.makeAMove('I', 4);
        validateMovement(moveResult, board);
        moveResult = board.makeAMove('A', 7);
        validateMovement(moveResult, board);
        moveResult = board.makeAMove('H', 7);
        validateMovement(moveResult, board);
        moveResult = board.makeAMove('G', 9);
        validateMovement(moveResult, board);
        moveResult = board.makeAMove('E', 4);
        validateMovement(moveResult, board);
        moveResult = board.makeAMove('D', 3);
        validateMovement(moveResult, board);
        moveResult = board.makeAMove('D', 3);
        validateMovement(moveResult, board);

    }

    public static void validateMovement(boolean moveResult, board board) {
        board.showBoard();

        if (moveResult) {
            System.out.print("Tiro válido");
        } else {
            System.out.print("Tiro inválido");
        }

    }

}
