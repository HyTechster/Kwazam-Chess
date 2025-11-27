package pieces;

import core.Controller;
import java.awt.event.*;
import javax.swing.*;

/**
 * RedBiz piece class
 */
public class RedBiz extends ChessPiece {

    private final String type = "RedPiece";
    private final String name = "RedBiz";

    // Constructor to initialize the RedBiz piece
    public RedBiz(Square[] square, int noSquare, int row, int col) {
        super(square, noSquare, row, col);

        // Set the icon for the RedBiz piece
        setIconWithPath("./images/red_biz.png");

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
