package factories;

import pieces.*;

/**
 * Factory for creating chess pieces
 */
public class PieceFactory {

    /**
     * Create a piece from a code string (used for save/load)
     */
    public static Square createPieceFromCode(String code, Square[] square, int squareNumber, int row, int col) {
        switch (code) {
            case "r1a":
                return new RedRam(square, squareNumber, row, col, false);
            case "r1b":
                return new RedRam(square, squareNumber, row, col, true);
            case "r2a":
                return new RedTor(square, squareNumber, row, col);
            case "r3a":
                return new RedXor(square, squareNumber, row, col);
            case "r4a":
                return new RedBiz(square, squareNumber, row, col);
            case "r5a":
                return new RedSau(square, squareNumber, row, col);
            case "b1a":
                return new BlueRam(square, squareNumber, row, col, false);
            case "b1b":
                return new BlueRam(square, squareNumber, row, col, true);
            case "b2a":
                return new BlueTor(square, squareNumber, row, col);
            case "b3a":
                return new BlueXor(square, squareNumber, row, col);
            case "b4a":
                return new BlueBiz(square, squareNumber, row, col);
            case "b5a":
                return new BlueSau(square, squareNumber, row, col);
            default:
                return new Square(squareNumber, row, col);
        }
    }

    /**
     * Create a piece for initial board setup based on square position
     */
    public static Square createPieceForInitialSetup(int squareNumber, Square[] square, int row, int col) {
        // Blue pieces (top of board)
        if (squareNumber > 4 && squareNumber < 10) {
            return new BlueRam(square, squareNumber, row, col, false);
        } else if (squareNumber == 2) {
            return new BlueSau(square, squareNumber, row, col);
        } else if (squareNumber == 1 || squareNumber == 3) {
            return new BlueBiz(square, squareNumber, row, col);
        } else if (squareNumber == 0) {
            return new BlueTor(square, squareNumber, row, col);
        } else if (squareNumber == 4) {
            return new BlueXor(square, squareNumber, row, col);
        }
        // Red pieces (bottom of board)
        else if (squareNumber > 29 && squareNumber < 35) {
            return new RedRam(square, squareNumber, row, col, false);
        } else if (squareNumber == 37) {
            return new RedSau(square, squareNumber, row, col);
        } else if (squareNumber == 36 || squareNumber == 38) {
            return new RedBiz(square, squareNumber, row, col);
        } else if (squareNumber == 35) {
            return new RedXor(square, squareNumber, row, col);
        } else if (squareNumber == 39) {
            return new RedTor(square, squareNumber, row, col);
        }

        // Empty square (no piece)
        return null;
    }

    /**
     * Create an empty square
     */
    public static Square createEmptySquare(int squareNumber, int row, int col) {
        return new Square(squareNumber, row, col);
    }
}
