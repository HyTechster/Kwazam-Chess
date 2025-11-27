package pieces;

import core.Controller;
import java.awt.event.*;
import javax.swing.*;

/**
 * Abstract base class for all chess pieces
 */
public abstract class ChessPiece extends Square {

    protected Square[] square;
    protected boolean clicked;
    protected Controller controller;

    // Constructor for chess pieces
    public ChessPiece(Square[] square, int noSquare, int row, int col) {
        super(noSquare, row, col);
        this.square = square;
        this.clicked = false;
        this.controller = Controller.getController();
    }

    // Abstract methods to be implemented by subclasses
    public abstract String getType();
    public abstract String getName();

    // Common method to reset click state
    @Override
    public void resetClick() {
        this.clicked = false;
    }

    // Template method for adding listener
    @Override
    public void addListener() {
        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleClick();
            }
        });
    }

    // Common click handling logic
    protected void handleClick() {
        if (!clicked) {
            controller.handlePieceClick(getNoSquare());
            clicked = true;
        } else {
            controller.handlePieceClick2(getNoSquare());
            clicked = false;
        }
    }

    // Method to set icon with given path
    protected void setIconWithPath(String iconPath) {
        ImageIcon icon = new ImageIcon(iconPath);
        this.setIcon(icon);
        this.setDisabledIcon(icon);
    }
}
