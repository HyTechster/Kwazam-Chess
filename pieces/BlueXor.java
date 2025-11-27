package pieces;

import core.Controller;
import java.awt.event.*;
import javax.swing.*;

/**
 * BlueXor piece class
 */
public class BlueXor extends ChessPiece {

    private final String type = "BluePiece";
    private final String name = "BlueXor";

    // Constructor to initialize the BlueXor piece
    public BlueXor(Square[] square, int noSquare, int row, int col) {
        super(square, noSquare, row, col);

        // Set the initial icon for the BlueXor piece
        setIconWithPath("./images/blue_xor.png");

        // Add action listener using inherited method
        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleClick();
            }
        });
    }

    // Getter for the type of the piece
    @Override
    public String getType() {
        return type;
    }

    // Getter for the name of the piece
    @Override
    public String getName() {
        return name;
    }
}
