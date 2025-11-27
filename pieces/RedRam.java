package pieces;

import core.Controller;
import java.awt.event.*;
import javax.swing.*;

/**
 * RedRam piece class
 */
public class RedRam extends ChessPiece {

    private final String type = "RedPiece";
    private final String name = "RedRam";
    private ImageIcon Icon;
    private boolean rotated;

    // Constructor to initialize the RedRam piece
    public RedRam(Square[] square, int noSquare, int row, int col, boolean rotation) {
        super(square, noSquare, row, col);
        this.rotated = rotation;

        // Set the icon for the piece
        this.Icon = new ImageIcon("./images/red_ram1.png");
        this.setIcon(Icon);
        this.setDisabledIcon(Icon);

        // Add action listener using inherited method
        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleClick();
            }
        });
    }

    // Get the type of the piece
    @Override
    public String getType() {
        return type;
    }

    // Get the name of the piece
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void checkRotation() {
        if (this.getRow() - 1 < 0) {
            this.rotated = true;
        } else if (this.getRow() + 1 > 7) {
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

    // Set the icon facing up
    @Override
    public void setIconFacingUp() {
        Icon = new ImageIcon("./images/red_ram1.png");
        this.setIcon(Icon);
        this.setDisabledIcon(Icon);
    }

    // Set the icon facing down
    @Override
    public void setIconFacingDown() {
        Icon = new ImageIcon("./images/red_ram2.png");
        this.setIcon(Icon);
        this.setDisabledIcon(Icon);
    }
}
