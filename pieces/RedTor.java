package pieces;

import core.Controller;
import java.awt.event.*;
import javax.swing.*;

/**
 * RedTor piece class
 */
public class RedTor extends ChessPiece {

    private final String type = "RedPiece";
    private final String name = "RedTor";

    // Constructor to initialize the RedTor piece
    public RedTor(Square[] square, int noSquare, int row, int col) {
        super(square, noSquare, row, col);

        // Set the initial icon for the RedTor piece
        setIconWithPath("./images/red_tor.png");

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
