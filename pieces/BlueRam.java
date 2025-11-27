package pieces;

import core.Controller;
import java.awt.event.*;
import javax.swing.*;

/**
 * BlueRam piece class
 */
public class BlueRam extends ChessPiece {

    private final String type = "BluePiece";
    private final String name = "BlueRam";
    private ImageIcon Icon;
    private boolean rotated;

    // Constructor to initialize the BlueRam piece
    public BlueRam(Square[] square, int noSquare, int row, int col, boolean rotation) {
        super(square, noSquare, row, col);
        this.rotated = rotation;

        // Set the initial icon for the BlueRam piece
        this.Icon = new ImageIcon("./images/blue_ram1.png");
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

    @Override
    public void checkRotation() {
        if (this.getRow() + 1 > 7) {
            this.rotated = true;
        } else if (this.getRow() - 1 < 0) {
            this.rotated = false;
        }
    }

    @Override
    public void setRotate() {
        this.rotated = true;
    }

    @Override
    public void resetRotate() {
        this.rotated = false;
    }

    @Override
    public boolean getRotate() {
        return rotated;
    }

    // Method to set the icon facing up
    @Override
    public void setIconFacingUp() {
        Icon = new ImageIcon("./images/blue_ram2.png");
        this.setIcon(Icon);
        this.setDisabledIcon(Icon);
    }

    // Method to set the icon facing down
    @Override
    public void setIconFacingDown() {
        Icon = new ImageIcon("./images/blue_ram1.png");
        this.setIcon(Icon);
        this.setDisabledIcon(Icon);
    }
}
