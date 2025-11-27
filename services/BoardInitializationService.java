package services;

import pieces.Square;
import factories.PieceFactory;
import utils.Constants;
import javax.swing.JPanel;

/**
 * Service for initializing and resetting the board
 */
public class BoardInitializationService {

    private Square[] square;
    private JPanel boardFrame;

    public BoardInitializationService(Square[] square, JPanel boardFrame) {
        this.square = square;
        this.boardFrame = boardFrame;
    }

    /**
     * Initialize the board with pieces in starting positions
     */
    public void initializeBoard() {
        int noSq = 0;
        for (int row = 0; row < Constants.BOARD_ROWS; row++) {
            for (int col = 0; col < Constants.BOARD_COLS; col++) {
                Square piece = PieceFactory.createPieceForInitialSetup(noSq, square, row, col);

                if (piece != null) {
                    boardFrame.remove(square[noSq]);
                    square[noSq] = piece;
                }

                noSq++;
            }
        }
    }

    /**
     * Reset the board to empty squares
     */
    public void resetBoard() {
        int noSq = 0;
        for (int row = 0; row < Constants.BOARD_ROWS; row++) {
            for (int col = 0; col < Constants.BOARD_COLS; col++) {
                boardFrame.remove(square[noSq]);
                square[noSq] = PieceFactory.createEmptySquare(noSq, row, col);
                boardFrame.add(square[noSq]);
                noSq++;
            }
        }
    }
}
