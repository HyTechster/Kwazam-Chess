package ui;

import core.ModelObserver;
import pieces.Square;
import services.BoardRefreshService;
import services.BoardInitializationService;
import utils.Constants;
import java.awt.*;
import javax.swing.*;

/**
 * Board class - Manages the chess board display
 */
public class Board extends JFrame implements ModelObserver {

    private View view;
    private JPanel boardFrame;
    private Square[] square = new Square[Constants.TOTAL_SQUARES];
    private int startVal;

    // Services
    private BoardRefreshService refreshService;
    private BoardInitializationService initService;

    // Constructor to initialize the board with squares
    public Board(View view, JPanel boardFrame) {
        this.view = view;
        this.boardFrame = boardFrame;

        int noSq = 0;
        for (int row = 0; row < Constants.BOARD_ROWS; row++) {
            for (int col = 0; col < Constants.BOARD_COLS; col++) {
                // Initialize each square and add it to the board frame
                square[noSq] = new Square(noSq, row, col);
                square[noSq].setPreferredSize(Constants.SQUARE_SIZE);
                boardFrame.add(square[noSq]);
                noSq++;
            }
        }

        // Initialize services
        this.refreshService = new BoardRefreshService(square, boardFrame);
        this.initService = new BoardInitializationService(square, boardFrame);

        view.refreshView();
    }

    // Method to initialize the board with specific pieces
    public void initializeBoard() {
        initService.initializeBoard();
    }

    // Method to reset the board to its initial state
    public void restartPos() {
        initService.resetBoard();
        view.refreshView();
    }

    // Method to refresh the board based on the moves made
    public void refreshBoard(boolean redMoved, boolean blueMoved) {
        refreshService.refreshBoard(startVal, redMoved, blueMoved);
        view.refreshView();
    }

    // Getter for square array
    public Square[] getSquare() {
        return square;
    }

    // Getter for board frame
    public JPanel getBoardFrame() {
        return boardFrame;
    }

    // Setter for start value
    public void setStartVal(int val) {
        startVal = val;
    }

    // Method to handle model updates
    @Override
    public void onModelUpdate(boolean rm, boolean bm) {
        refreshBoard(rm, bm);
    }
}
