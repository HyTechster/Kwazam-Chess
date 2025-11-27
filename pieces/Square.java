package pieces;

import java.awt.*;
import javax.swing.*;
import utils.Constants;

public class Square extends JButton {

    private final String type = Constants.SQUARE;
    private final String name = Constants.SQUARE;
    private int noSquare;
    private int row;
    private int col;
    private boolean clicked;

    // Constructor to initialize a square with its position and color
    public Square(int noSquare, int row, int col) {
        this.noSquare = noSquare;
        this.row = row;
        this.col = col;

        // Set the background color based on the square number
        if (noSquare % 2 == 0) {
            this.setBackground(Constants.BEIGE);
            this.setOpaque(true);
        } else {
            this.setBackground(Constants.LIGHT_BROWN);
            this.setOpaque(true);
        }
        this.setBorder(BorderFactory.createBevelBorder(1, Color.BLACK, Color.BLACK));
    }

    // Getter for square number
    public int getNoSquare() {
        return noSquare;
    }

    // Getter for type
    public String getType() {
        return type;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for row
    public int getRow() {
        return row;
    }

    // Getter for column
    public int getCol() {
        return col;
    }

    // Method to reset the clicked state
    public void resetClick() {
        this.clicked = false;
    }

    // Method to reset the background color
    public void resetBackground() {
        if (noSquare % 2 == 0) {
            this.setBackground(Constants.BEIGE);
            this.setOpaque(true);
        } else {
            this.setBackground(Constants.LIGHT_BROWN);
            this.setOpaque(true);
        }
    }

    // Method to set the icon facing up
    public void setIconFacingUp() {
    }

    // Method to set the icon facing down
    public void setIconFacingDown() {
    }

    // Method to add a listener to the square
    public void addListener() {
    }

    // Method to check the rotation state of the square
    public void checkRotation() {
    }

    // Method to set the rotation state of the square
    public void setRotate() {
    }

    // Method to reset the rotation state of the square
    public void resetRotate() {
    }

    // Method to get the rotation state of the square
    public boolean getRotate() {
        return false;
    }
}
