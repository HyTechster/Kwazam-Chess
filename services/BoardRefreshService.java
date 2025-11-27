package services;

import pieces.Square;
import utils.Constants;
import javax.swing.JPanel;

/**
 * Service for refreshing board display based on game state
 */
public class BoardRefreshService {

    private Square[] square;
    private JPanel boardFrame;

    public BoardRefreshService(Square[] square, JPanel boardFrame) {
        this.square = square;
        this.boardFrame = boardFrame;
    }

    /**
     * Refresh the board based on who moved and who started
     */
    public void refreshBoard(int startVal, boolean redMoved, boolean blueMoved) {
        removeAllSquaresFromBoard();

        if (startVal == Constants.RED_STARTS) {
            handleRedStartsRefresh(redMoved, blueMoved);
        } else if (startVal == Constants.BLUE_STARTS) {
            handleBlueStartsRefresh(redMoved, blueMoved);
        }

        // Force the board to revalidate and repaint
        boardFrame.revalidate();
        boardFrame.repaint();
    }

    /**
     * Handle refresh when Red started the game
     */
    private void handleRedStartsRefresh(boolean redMoved, boolean blueMoved) {
        if (redMoved || blueMoved) {
            // After moves: reverse order
            int noSq = 39;
            for (int row = 7; row >= 0; row--) {
                for (int col = 4; col >= 0; col--) {
                    boardFrame.add(square[noSq]);
                    // Set the icon facing direction based on the piece type and move
                    if (square[noSq].getName().equals("RedRam") && redMoved) {
                        square[noSq].setIconFacingDown();
                    } else if (square[noSq].getName().equals("RedRam") && blueMoved) {
                        square[noSq].setIconFacingUp();
                    } else if (square[noSq].getName().equals("RedSau") && redMoved) {
                        square[noSq].setIconFacingDown();
                    } else if (square[noSq].getName().equals("RedSau") && blueMoved) {
                        square[noSq].setIconFacingUp();
                    } else if (square[noSq].getName().equals("BlueRam") && redMoved) {
                        square[noSq].setIconFacingUp();
                    } else if (square[noSq].getName().equals("BlueRam") && blueMoved) {
                        square[noSq].setIconFacingDown();
                    } else if (square[noSq].getName().equals("BlueSau") && redMoved) {
                        square[noSq].setIconFacingUp();
                    } else if (square[noSq].getName().equals("BlueSau") && blueMoved) {
                        square[noSq].setIconFacingDown();
                    }
                    noSq--;
                }
            }
        } else {
            // Before moves: normal order
            int noSq = 0;
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 5; col++) {
                    boardFrame.add(square[noSq]);
                    // Set the icon facing direction based on the piece type
                    if (square[noSq].getName().equals("RedRam")) {
                        square[noSq].setIconFacingUp();
                    } else if (square[noSq].getName().equals("RedSau")) {
                        square[noSq].setIconFacingUp();
                    } else if (square[noSq].getName().equals("BlueRam")) {
                        square[noSq].setIconFacingDown();
                    } else if (square[noSq].getName().equals("BlueSau")) {
                        square[noSq].setIconFacingDown();
                    }
                    noSq++;
                }
            }
        }
    }

    /**
     * Handle refresh when Blue started the game
     */
    private void handleBlueStartsRefresh(boolean redMoved, boolean blueMoved) {
        if (redMoved || blueMoved) {
            // After moves: normal order
            int noSq = 0;
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 5; col++) {
                    boardFrame.add(square[noSq]);
                    // Set the icon facing direction based on the piece type and move
                    if (square[noSq].getName().equals("RedRam") && blueMoved) {
                        square[noSq].setIconFacingUp();
                    } else if (square[noSq].getName().equals("RedRam") && redMoved) {
                        square[noSq].setIconFacingDown();
                    } else if (square[noSq].getName().equals("RedSau") && blueMoved) {
                        square[noSq].setIconFacingUp();
                    } else if (square[noSq].getName().equals("RedSau") && redMoved) {
                        square[noSq].setIconFacingDown();
                    } else if (square[noSq].getName().equals("BlueRam") && blueMoved) {
                        square[noSq].setIconFacingDown();
                    } else if (square[noSq].getName().equals("BlueRam") && redMoved) {
                        square[noSq].setIconFacingUp();
                    } else if (square[noSq].getName().equals("BlueSau") && blueMoved) {
                        square[noSq].setIconFacingDown();
                    } else if (square[noSq].getName().equals("BlueSau") && redMoved) {
                        square[noSq].setIconFacingUp();
                    }
                    noSq++;
                }
            }
        } else {
            // Before moves: reverse order
            int noSq = 39;
            for (int row = 7; row >= 0; row--) {
                for (int col = 4; col >= 0; col--) {
                    boardFrame.add(square[noSq]);
                    // Set the icon facing direction based on the piece type
                    if (square[noSq].getName().equals("RedRam")) {
                        square[noSq].setIconFacingDown();
                    } else if (square[noSq].getName().equals("BlueRam")) {
                        square[noSq].setIconFacingUp();
                    } else if (square[noSq].getName().equals("RedSau")) {
                        square[noSq].setIconFacingDown();
                    } else if (square[noSq].getName().equals("BlueSau")) {
                        square[noSq].setIconFacingUp();
                    }
                    noSq--;
                }
            }
        }
    }

    /**
     * Remove all squares from the board frame
     */
    private void removeAllSquaresFromBoard() {
        for (int a = 0; a < 40; a++) {
            boardFrame.remove(square[a]);
        }
    }
}
