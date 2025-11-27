package services;

import pieces.*;
import factories.PieceFactory;
import javax.swing.JPanel;

/**
 * Handles piece movement and capture logic
 */
public class PieceMovementHandler {

    private Square[] square;
    private JPanel boardFrame;

    public PieceMovementHandler(Square[] square, JPanel boardFrame) {
        this.square = square;
        this.boardFrame = boardFrame;
    }

    // Execute a move from one square to another
    public void executeMove(int fromSquare, int toSquare, boolean isCapture) {
        String pieceName = square[fromSquare].getName();
        boolean rotation = square[fromSquare].getRotate();

        // Remove old pieces from board
        boardFrame.remove(square[fromSquare]);
        boardFrame.remove(square[toSquare]);

        // Create new pieces
        Square newPiece = createMovedPiece(pieceName, toSquare, rotation);

        // Update board state
        square[fromSquare] = PieceFactory.createEmptySquare(fromSquare, square[fromSquare].getRow(), square[fromSquare].getCol());
        square[toSquare] = newPiece;
    }

    // Create a piece that has been moved to a new position
    private Square createMovedPiece(String pieceName, int squareIndex, boolean rotation) {
        int row = square[squareIndex].getRow();
        int col = square[squareIndex].getCol();

        switch (pieceName) {
            case "RedRam":
                return new RedRam(square, squareIndex, row, col, rotation);
            case "BlueRam":
                return new BlueRam(square, squareIndex, row, col, rotation);
            case "RedTor":
                return new RedTor(square, squareIndex, row, col);
            case "BlueTor":
                return new BlueTor(square, squareIndex, row, col);
            case "RedXor":
                return new RedXor(square, squareIndex, row, col);
            case "BlueXor":
                return new BlueXor(square, squareIndex, row, col);
            case "RedBiz":
                return new RedBiz(square, squareIndex, row, col);
            case "BlueBiz":
                return new BlueBiz(square, squareIndex, row, col);
            case "RedSau":
                return new RedSau(square, squareIndex, row, col);
            case "BlueSau":
                return new BlueSau(square, squareIndex, row, col);
            default:
                return PieceFactory.createEmptySquare(squareIndex, row, col);
        }
    }

    // Check if captured piece is a Sau (game ending condition)
    public boolean isSauCaptured(int squareIndex) {
        String pieceName = square[squareIndex].getName();
        return pieceName.equals("RedSau") || pieceName.equals("BlueSau");
    }

    // Get the captured piece name
    public String getCapturedPieceName(int squareIndex) {
        return square[squareIndex].getName();
    }
}
