package services;

import pieces.Square;
import utils.Constants;
import java.awt.*;

/**
 * Manages board operations including enabling/disabling pieces
 */
public class BoardManager {

    private Square[] square;

    public BoardManager(Square[] square) {
        this.square = square;
    }

    // Enable/Disable operations for different piece types
    public void disableRed() {
        for (int i = 0; i < square.length; i++) {
            if (square[i].getType().equals(Constants.RED_PIECE)) {
                square[i].setEnabled(false);
            }
        }
    }

    public void disableBlue() {
        for (int i = 0; i < square.length; i++) {
            if (square[i].getType().equals(Constants.BLUE_PIECE)) {
                square[i].setEnabled(false);
            }
        }
    }

    public void disableSquares() {
        for (int i = 0; i < square.length; i++) {
            if (square[i].getType().equals(Constants.SQUARE)) {
                square[i].setEnabled(false);
            }
        }
    }

    public void enableRed() {
        for (int i = 0; i < square.length; i++) {
            if (square[i].getType().equals(Constants.RED_PIECE)) {
                square[i].setEnabled(true);
            }
        }
    }

    public void enableBlue() {
        for (int i = 0; i < square.length; i++) {
            if (square[i].getType().equals(Constants.BLUE_PIECE)) {
                square[i].setEnabled(true);
            }
        }
    }

    public void enableSquares() {
        for (int i = 0; i < square.length; i++) {
            if (square[i].getType().equals(Constants.SQUARE)) {
                square[i].setEnabled(true);
            }
        }
    }

    // Reset operations
    public void resetAllPieceClicks() {
        for (int i = 0; i < square.length; i++) {
            square[i].resetClick();
            square[i].resetBackground();
            if (square[i].getType().equals(Constants.SQUARE)) {
                removeAllListeners(i);
            }
        }
    }

    public void addAllListeners() {
        for (int i = 0; i < square.length; i++) {
            removeAllListeners(i);
            square[i].addListener();
        }
    }

    private void removeAllListeners(int squareIndex) {
        java.awt.event.ActionListener[] listeners = square[squareIndex].getActionListeners();
        for (java.awt.event.ActionListener listener : listeners) {
            square[squareIndex].removeActionListener(listener);
        }
    }

    public void removeSingleSquareListeners(int targetSquare) {
        java.awt.event.ActionListener[] listeners = square[targetSquare].getActionListeners();
        for (java.awt.event.ActionListener listener : listeners) {
            square[targetSquare].removeActionListener(listener);
        }
    }

    // Highlighting operations
    public void highlightSquare(int squareIndex, Color color) {
        square[squareIndex].setEnabled(true);
        square[squareIndex].setBackground(color);
    }

    public void resetSquareBackground(int squareIndex) {
        square[squareIndex].resetBackground();
    }
}
