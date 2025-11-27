package pieces;

import core.Controller;
import java.awt.event.*;
import javax.swing.*;

/**
 * RedXor piece class
 */
public class RedXor extends ChessPiece {

    private final String type = "RedPiece";
    private final String name = "RedXor";

    // Constructor to initialize the RedXor piece
    public RedXor(Square[] square, int noSquare, int row, int col) {
        super(square, noSquare, row, col);

        // Set the initial icon for the RedXor piece
        setIconWithPath("./images/red_xor.png");

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
