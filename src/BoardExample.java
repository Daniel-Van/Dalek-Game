
import java.awt.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author vand5950
 */
public class BoardExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // create a board
        Board board = new Board(12, 12);
        // put a message at the bottom of the board
        board.displayMessage("click me");
        // put down peg
        board.putPeg(Color.BLUE, 2, 5);
        //remove a peg
        board.removePeg(2, 5);

        while (true) {
            // wait for a  click
            Coordinate click = board.getClick();
            int clickRow = click.getRow();
            int clickCol = click.getCol();
            board.putPeg(Color.yellow, clickRow, clickCol);
        }
    }

}
