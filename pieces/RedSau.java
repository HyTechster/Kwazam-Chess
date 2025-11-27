package pieces;

import core.Controller;
import java.awt.event.*;
import javax.swing.*;

/**
 * RedSau piece class
 */
public class RedSau extends ChessPiece {

    private final String type = "RedPiece";
    private final String name = "RedSau";
    private ImageIcon Icon;

    // Constructor to initialize the RedSau piece
    public RedSau(Square[] square, int noSquare, int row, int col) {
        super(square, noSquare, row, col);

        // Set the initial icon for the RedSau piece
        this.Icon = new ImageIcon("./images/red_sau1.png");
        this.setIcon(Icon);
        this.setDisabledIcon(Icon);

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

    // Method to set the icon facing up
    @Override
    public void setIconFacingUp() {
        Icon = new ImageIcon("./images/red_sau1.png");
        this.setIcon(Icon);
        this.setDisabledIcon(Icon);
    }

    // Method to set the icon facing down
    @Override
    public void setIconFacingDown() {
        Icon = new ImageIcon("./images/red_sau2.png");
        this.setIcon(Icon);
        this.setDisabledIcon(Icon);
    }
}
