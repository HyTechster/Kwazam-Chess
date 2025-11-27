package services;

import pieces.Square;
import utils.Constants;
import java.awt.event.*;

/**
 * Calculates valid moves for pieces
 */
public class PieceMoveCalculator {

    private Square[] square;
    private BoardManager boardManager;

    public PieceMoveCalculator(Square[] square, BoardManager boardManager) {
        this.square = square;
        this.boardManager = boardManager;
    }

    // Main method to calculate and highlight valid moves
    public void calculateAndHighlightMoves(int pieceSquare, MoveHandler moveHandler) {
        String pieceName = square[pieceSquare].getName();
        String pieceType = square[pieceSquare].getType();

        // Highlight selected piece
        boardManager.highlightSquare(pieceSquare, Constants.HIGHLIGHT_SELECTED);

        // Calculate moves based on piece type
        switch (pieceName) {
            case "RedRam":
            case "BlueRam":
                calculateRamMoves(pieceSquare, pieceType, moveHandler);
                break;
            case "RedTor":
            case "BlueTor":
                calculateTorMoves(pieceSquare, pieceType, moveHandler);
                break;
            case "RedXor":
            case "BlueXor":
                calculateXorMoves(pieceSquare, pieceType, moveHandler);
                break;
            case "RedBiz":
            case "BlueBiz":
                calculateBizMoves(pieceSquare, pieceType, moveHandler);
                break;
            case "RedSau":
            case "BlueSau":
                calculateSauMoves(pieceSquare, pieceType, moveHandler);
                break;
        }
    }

    // Ram movement calculation
    private void calculateRamMoves(int pieceSquare, String pieceType, MoveHandler moveHandler) {
        square[pieceSquare].checkRotation();
        boolean rotated = square[pieceSquare].getRotate();

        int direction = pieceType.equals(Constants.RED_PIECE) ?
                        (rotated ? 1 : -1) :
                        (rotated ? -1 : 1);

        for (int i = 0; i < square.length; i++) {
            if (square[i].getCol() == square[pieceSquare].getCol() &&
                square[i].getRow() == square[pieceSquare].getRow() + direction &&
                !square[i].getType().equals(pieceType)) {

                highlightAndAddHandler(i, pieceSquare, moveHandler);
            }
        }
    }

    // Tor movement calculation (horizontal and vertical lines)
    private void calculateTorMoves(int pieceSquare, String pieceType, MoveHandler moveHandler) {
        calculateLinearMoves(pieceSquare, pieceType, moveHandler, true, true);
    }

    // Xor movement calculation (diagonal lines)
    private void calculateXorMoves(int pieceSquare, String pieceType, MoveHandler moveHandler) {
        calculateDiagonalMoves(pieceSquare, pieceType, moveHandler);
    }

    // Biz movement calculation (L-shaped knight moves)
    private void calculateBizMoves(int pieceSquare, String pieceType, MoveHandler moveHandler) {
        int[][] offsets = {
            {-2, -1}, {-2, 1}, {2, -1}, {2, 1},
            {-1, -2}, {-1, 2}, {1, -2}, {1, 2}
        };

        for (int[] offset : offsets) {
            int targetCol = square[pieceSquare].getCol() + offset[0];
            int targetRow = square[pieceSquare].getRow() + offset[1];

            if (targetCol >= 0 && targetCol < Constants.BOARD_COLS && targetRow >= 0 && targetRow < Constants.BOARD_ROWS) {
                for (int i = 0; i < square.length; i++) {
                    if (square[i].getCol() == targetCol &&
                        square[i].getRow() == targetRow &&
                        !square[i].getType().equals(pieceType)) {

                        highlightAndAddHandler(i, pieceSquare, moveHandler);
                    }
                }
            }
        }
    }

    // Sau movement calculation (one square in any direction)
    private void calculateSauMoves(int pieceSquare, String pieceType, MoveHandler moveHandler) {
        int[][] offsets = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},           {0, 1},
            {1, -1},  {1, 0},  {1, 1}
        };

        for (int[] offset : offsets) {
            int targetCol = square[pieceSquare].getCol() + offset[0];
            int targetRow = square[pieceSquare].getRow() + offset[1];

            if (targetCol >= 0 && targetCol < Constants.BOARD_COLS && targetRow >= 0 && targetRow < Constants.BOARD_ROWS) {
                for (int i = 0; i < square.length; i++) {
                    if (square[i].getCol() == targetCol &&
                        square[i].getRow() == targetRow &&
                        !square[i].getType().equals(pieceType)) {

                        highlightAndAddHandler(i, pieceSquare, moveHandler);
                    }
                }
            }
        }
    }

    // Helper method for linear moves (Tor)
    private void calculateLinearMoves(int pieceSquare, String pieceType, MoveHandler moveHandler,
                                     boolean horizontal, boolean vertical) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // up, down, left, right

        for (int[] dir : directions) {
            boolean blocked = false;
            int multiplier = 1;

            while (!blocked) {
                int targetRow = square[pieceSquare].getRow() + (dir[0] * multiplier);
                int targetCol = square[pieceSquare].getCol() + (dir[1] * multiplier);

                if (targetRow < 0 || targetRow >= Constants.BOARD_ROWS || targetCol < 0 || targetCol >= Constants.BOARD_COLS) {
                    break;
                }

                for (int i = 0; i < square.length; i++) {
                    if (square[i].getRow() == targetRow && square[i].getCol() == targetCol) {
                        if (square[i].getType().equals(Constants.SQUARE)) {
                            highlightAndAddHandler(i, pieceSquare, moveHandler);
                            multiplier++;
                        } else if (square[i].getType().equals(pieceType)) {
                            blocked = true;
                        } else {
                            highlightAndAddHandler(i, pieceSquare, moveHandler);
                            blocked = true;
                        }
                        break;
                    }
                }
            }
        }
    }

    // Helper method for diagonal moves (Xor)
    private void calculateDiagonalMoves(int pieceSquare, String pieceType, MoveHandler moveHandler) {
        int[][] directions = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}}; // diagonals

        for (int[] dir : directions) {
            boolean blocked = false;
            int multiplier = 1;

            while (!blocked) {
                int targetRow = square[pieceSquare].getRow() + (dir[0] * multiplier);
                int targetCol = square[pieceSquare].getCol() + (dir[1] * multiplier);

                if (targetRow < 0 || targetRow >= Constants.BOARD_ROWS || targetCol < 0 || targetCol >= Constants.BOARD_COLS) {
                    break;
                }

                for (int i = 0; i < square.length; i++) {
                    if (square[i].getRow() == targetRow && square[i].getCol() == targetCol) {
                        if (square[i].getType().equals(Constants.SQUARE)) {
                            highlightAndAddHandler(i, pieceSquare, moveHandler);
                            multiplier++;
                        } else if (square[i].getType().equals(pieceType)) {
                            blocked = true;
                        } else {
                            highlightAndAddHandler(i, pieceSquare, moveHandler);
                            blocked = true;
                        }
                        break;
                    }
                }
            }
        }
    }

    // Helper method to highlight square and add click handler
    private void highlightAndAddHandler(int targetSquare, int fromSquare, MoveHandler moveHandler) {
        boardManager.highlightSquare(targetSquare, Constants.HIGHLIGHT_AVAILABLE);
        boardManager.removeSingleSquareListeners(targetSquare);

        square[targetSquare].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean isCapture = !square[targetSquare].getType().equals(Constants.SQUARE);
                moveHandler.handleMove(fromSquare, targetSquare, isCapture);
            }
        });
    }

    // Interface for handling moves
    public interface MoveHandler {
        void handleMove(int from, int to, boolean isCapture);
    }
}
