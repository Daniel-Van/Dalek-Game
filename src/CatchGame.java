
import java.awt.Color;

/**
 * This class manages the interactions between the different pieces of the game:
 * the Board, the Daleks, and the Doctor. It determines when the game is over
 * and whether the Doctor won or lost.
 */
public class CatchGame {

    /**
     * Instance variables go up here Make sure to create a Board, 3 Daleks, and
     * a Doctor
     */
    // create a board
    private Board board = new Board(12, 12);
    private Doctor doctor;
    private Dalek dalek1;
    private Dalek dalek2;
    private Dalek dalek3;

    /**
     * The constructor for the game. Use it to initialize your game variables.
     * (create people, set positions, etc.)
     */
    public CatchGame() {
        int doctorCol;
        int doctorRow;
        doctorRow = (int) (Math.random() * 12);
        doctorCol = (int) (Math.random() * 12);
        doctor = new Doctor(doctorRow, doctorCol);
        board.putPeg(Color.GREEN, doctorRow, doctorCol);

        int dalek1Col;
        int dalek1Row;
        dalek1Row = (int) (Math.random() * 12);
        dalek1Col = (int) (Math.random() * 12);
        dalek1 = new Dalek(dalek1Row, dalek1Col);
        board.putPeg(Color.BLACK, dalek1Row, dalek1Col);

        int dalek2Col;
        int dalek2Row;
        dalek2Row = (int) (Math.random() * 12);
        dalek2Col = (int) (Math.random() * 12);
        dalek2 = new Dalek(dalek2Row, dalek2Col);
        board.putPeg(Color.BLACK, dalek2Row, dalek2Col);

        int dalek3Col;
        int dalek3Row;
        dalek3Row = (int) (Math.random() * 12);
        dalek3Col = (int) (Math.random() * 12);
        dalek3 = new Dalek(dalek3Row, dalek3Col);
        board.putPeg(Color.BLACK, dalek3Row, dalek3Col);

        // makes sure the Dalek's do not spawn on each other
        while (true) {
            if (((dalek1Row == dalek2Row) && (dalek1Col == dalek2Col)) || ((dalek1Row == dalek3Row) && (dalek1Col == dalek3Col)) || ((dalek2Row == dalek3Row) && (dalek2Col == dalek3Col))) {
                if ((dalek1Row == dalek2Row) && (dalek1Col == dalek2Col)) {
                    dalek1Row = (int) (Math.random() * 12);
                    dalek1Col = (int) (Math.random() * 12);
                }
                if ((dalek1Row == dalek3Row) && (dalek1Col == dalek3Col)) {
                    dalek1Row = (int) (Math.random() * 12);
                    dalek1Col = (int) (Math.random() * 12);
                }
                if ((dalek2Row == dalek3Row) && (dalek2Col == dalek3Col)) {
                    dalek2Row = (int) (Math.random() * 12);
                    dalek2Col = (int) (Math.random() * 12);
                }
            } else {
                break;
            }
        }

        // makes sure the doctor does not spawn on a Dalek
        while (true) {
            if (((doctorRow == dalek1Row) && (doctorCol == dalek1Col)) || ((doctorRow == dalek2Row) && (doctorCol == dalek2Col)) || ((doctorRow == dalek3Row) && (doctorCol == dalek3Col))) {
                if ((doctorRow == dalek1Row) && (doctorCol == dalek1Col)) {
                    doctorRow = (int) (Math.random() * 12);
                    doctorCol = (int) (Math.random() * 12);
                }
                if ((doctorRow == dalek2Row) && (doctorCol == dalek2Col)) {
                    doctorRow = (int) (Math.random() * 12);
                    doctorCol = (int) (Math.random() * 12);
                }
                if ((doctorRow == dalek3Row) && (doctorCol == dalek3Col)) {
                    doctorRow = (int) (Math.random() * 12);
                    doctorCol = (int) (Math.random() * 12);
                }
            } else {
                break;
            }
        }
    }

    /**
     * The playGame method begins and controls a game: deals with when the user
     * selects a square, when the Daleks move, when the game is won/lost.
     */
    public void playGame() {
        while (true) {

            // check if the doctor has crashed with any of the Daleks
            // compares row and column of doctor with row and column of Daleks 1 to 3
            // if not captured, continue with the game until captured or all Dalek's are immobilized
            // user wins when all daleks are crashed 
            if ((doctor.getRow() == dalek1.getRow()) && (doctor.getCol() == dalek1.getCol())) {
                board.removePeg(doctor.getRow(), doctor.getCol());
                board.putPeg(Color.YELLOW, doctor.getRow(), doctor.getCol());
                board.displayMessage("                                  GAME OVER");
                break;
            }
            if ((doctor.getRow() == dalek2.getRow()) && (doctor.getCol() == dalek2.getCol())) {
                board.removePeg(doctor.getRow(), doctor.getCol());
                board.putPeg(Color.YELLOW, doctor.getRow(), doctor.getCol());
                board.displayMessage("                                  GAME OVER");
                break;
            }
            if ((doctor.getRow() == dalek3.getRow()) && (doctor.getCol() == dalek3.getCol())) {
                board.removePeg(doctor.getRow(), doctor.getCol());
                board.putPeg(Color.YELLOW, doctor.getRow(), doctor.getCol());
                board.displayMessage("                                  GAME OVER");
                break;
            }

            if (dalek1.hasCrashed() && dalek2.hasCrashed() && dalek3.hasCrashed()) {
                board.displayMessage("You win!");
                break;
            } else {
                board.displayMessage("Save the Doctor! Move the Doctor to crash the Daleks");
            }

            // wait for a click to move doctor
            Coordinate click = board.getClick();
            int clickRow = click.getRow();
            int clickCol = click.getCol();

            // move the Doctor
            board.removePeg(doctor.getRow(), doctor.getCol());
            doctor.move(clickRow, clickCol);
            board.putPeg(Color.GREEN, doctor.getRow(), doctor.getCol());

            // move Daleks if they are not in a crashed state
            if (dalek1.hasCrashed() == false) {
                board.removePeg(dalek1.getRow(), dalek1.getCol());
                dalek1.advanceTowards(doctor);
                board.putPeg(Color.BLACK, dalek1.getRow(), dalek1.getCol());
            }
            if (dalek2.hasCrashed() == false) {
                board.removePeg(dalek2.getRow(), dalek2.getCol());
                dalek2.advanceTowards(doctor);
                board.putPeg(Color.BLACK, dalek2.getRow(), dalek2.getCol());
            }
            if (dalek3.hasCrashed() == false) {
                board.removePeg(dalek3.getRow(), dalek3.getCol());
                dalek3.advanceTowards(doctor);
                board.putPeg(Color.BLACK, dalek3.getRow(), dalek3.getCol());
            }

            // check if any of the Dalek's have crashed with each other
            // if they are crahsed, set  Dalek's to a crashed state and place red peg down at current position
            if (dalek1.getRow() == dalek2.getRow() && dalek1.getCol() == dalek2.getCol()) {
                dalek1.crash();
                dalek2.crash();

                // set the crashed dalek 1 to a crashed state
                board.removePeg(dalek1.getRow(), dalek1.getCol());
                board.putPeg(Color.RED, dalek1.getRow(), dalek1.getCol());

                // set the crashed dalek 2 to a crashed state
                board.removePeg(dalek2.getRow(), dalek2.getCol());
                board.putPeg(Color.RED, dalek2.getRow(), dalek2.getCol());
            }

            if (dalek1.getRow() == dalek3.getRow() && dalek1.getCol() == dalek3.getCol()) {
                dalek1.crash();
                dalek3.crash();

                // set the crashed dalek 2 to a crashed state
                board.removePeg(dalek1.getRow(), dalek1.getCol());
                board.putPeg(Color.RED, dalek1.getRow(), dalek1.getCol());
                
                // set the crashed dalek 3 to a crashed state
                board.removePeg(dalek3.getRow(), dalek3.getCol());
                board.putPeg(Color.RED, dalek3.getRow(), dalek3.getCol());

            }

            if (dalek2.getRow() == dalek3.getRow() && dalek2.getCol() == dalek3.getCol()) {
                dalek2.crash();
                dalek3.crash();

                // set the crashed dalek 2 to a crashed state
                board.removePeg(dalek2.getRow(), dalek2.getCol());
                board.putPeg(Color.RED, dalek2.getRow(), dalek2.getCol());

                // set the crashed dalek 3 to a crashed state 
                board.removePeg(dalek3.getRow(), dalek3.getCol());
                board.putPeg(Color.RED, dalek3.getRow(), dalek3.getCol());
            }

        }

    }
}
