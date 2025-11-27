package services;

import pieces.*;
import javax.swing.JPanel;

/**
 * Service for transforming pieces every 2 turns
 */
public class PieceTransformationService {

    private Square[] square;
    private JPanel boardFrame;

    public PieceTransformationService(Square[] square, JPanel boardFrame) {
        this.square = square;
        this.boardFrame = boardFrame;
    }

    // Transform Tor to Xor and vice versa
    public void transformTorXorPieces() {
        for (int i = 0; i < square.length; i++) {
            String pieceName = square[i].getName();

            switch (pieceName) {
                case "RedTor":
                    transformPiece(i, new RedXor(square, square[i].getNoSquare(),
                                                 square[i].getRow(), square[i].getCol()));
                    break;
                case "RedXor":
                    transformPiece(i, new RedTor(square, square[i].getNoSquare(),
                                                 square[i].getRow(), square[i].getCol()));
                    break;
                case "BlueTor":
                    transformPiece(i, new BlueXor(square, square[i].getNoSquare(),
                                                  square[i].getRow(), square[i].getCol()));
                    break;
                case "BlueXor":
                    transformPiece(i, new BlueTor(square, square[i].getNoSquare(),
                                                  square[i].getRow(), square[i].getCol()));
                    break;
            }
        }
    }

    // Helper method to transform a single piece
    private void transformPiece(int index, Square newPiece) {
        boardFrame.remove(square[index]);
        square[index] = newPiece;
    }
}
