package pieces;

import core.Controller;
import java.awt.event.*;
import javax.swing.*;

/**
 * BlueBiz piece class
 */
public class BlueBiz extends ChessPiece {

    private final String type = "BluePiece";
    private final String name = "BlueBiz";

    // Constructor to initialize the BlueBiz piece
    public BlueBiz(Square[] square, int noSquare, int row, int col) {
        super(square, noSquare, row, col);

        // Set the icon for the BlueBiz piece
        setIconWithPath("./images/blue_biz.png");

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
