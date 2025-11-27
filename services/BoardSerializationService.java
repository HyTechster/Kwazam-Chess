package services;

import pieces.Square;
import factories.PieceFactory;
import javax.swing.JPanel;

/**
 * Service for serializing and deserializing board state
 */
public class BoardSerializationService {

    private Square[] square;

    public BoardSerializationService(Square[] square) {
        this.square = square;
    }

    /**
     * Build a string representation of the board state for saving
     */
    public String buildBoardState() {
        StringBuilder boardState = new StringBuilder();
        int squareNumber = 0;

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 5; col++) {
                boardState.append(getPieceCode(squareNumber));
                if (col < 4) {
                    boardState.append("|");
                }
                squareNumber++;
            }
            boardState.append("\n");
        }

        return boardState.toString();
    }

    /**
     * Get the code for a piece at a specific square
     */
    private String getPieceCode(int squareNumber) {
        String squareName = square[squareNumber].getName();

        switch (squareName) {
            case "RedRam":
                return square[squareNumber].getRotate() ? "r1b" : "r1a";
            case "RedTor":
                return "r2a";
            case "RedXor":
                return "r3a";
            case "RedBiz":
                return "r4a";
            case "RedSau":
                return "r5a";
            case "BlueRam":
                return square[squareNumber].getRotate() ? "b1b" : "b1a";
            case "BlueTor":
                return "b2a";
            case "BlueXor":
                return "b3a";
            case "BlueBiz":
                return "b4a";
            case "BlueSau":
                return "b5a";
            default:
                return "sqa";
        }
    }

    /**
     * Restore board state from a string representation
     */
    public void restoreBoardState(String boardStateString, JPanel boardFrame) {
        String[] lines = boardStateString.split("\n");
        int squareNumber = 0;

        for (String line : lines) {
            String[] splitLine = line.split("\\|");
            for (String pieceCode : splitLine) {
                int row = square[squareNumber].getRow();
                int col = square[squareNumber].getCol();

                Square newPiece = PieceFactory.createPieceFromCode(pieceCode, square, squareNumber, row, col);
                boardFrame.remove(square[squareNumber]);
                square[squareNumber] = newPiece;
                squareNumber++;
            }
        }
    }
}
