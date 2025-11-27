package ui;

import pieces.Square;
import utils.Constants;
import java.awt.*;
import javax.swing.*;

/**
 * Menu class - Manages the game menu UI
 */
public class Menu {

    private View view;
    private Board board;
    private Square[] square;
    private int startFirstVal;
    private DialogService dialogService;

    private JPanel menuFrame;
    private JPanel optionsContainer;

    private JButton option1;
    private JButton option2;
    private JButton option3;
    private JButton option4;
    private JLabel turnDisplay;
    private JLabel noTurnDisplay;

    private JPanel option1Container;
    private JPanel option2Container;
    private JPanel option3Container;
    private JPanel option4Container;
    private JPanel noTurnDisplayContainer;

    private JPanel moveHistoryContainer;
    private JTextArea moveHistory;

    // Constructor to initialize the Menu
    public Menu(View view, JPanel menuFrame) {
        this.view = view;
        this.menuFrame = menuFrame;
        this.board = view.getBoard();
        this.square = board.getSquare();
        this.dialogService = new DialogService();

        initializeLogo();
        initializeOptions();
        initializeMoveHistory();
        initializeFooter();
    }

    // Initialize the logo panel
    private void initializeLogo() {
        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(Constants.CACAO);
        imagePanel.setPreferredSize(new Dimension(250, 100));
        menuFrame.add(imagePanel);

        JLabel imageLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon(Constants.IMAGE_LOGO);
        Image img = imageIcon.getImage();
        Image resizedImg = img.getScaledInstance(225, 100, Image.SCALE_DEFAULT);
        imageIcon = new ImageIcon(resizedImg);
        imageLabel.setIcon(imageIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imagePanel.add(imageLabel);
    }

    // Initialize the options panel
    private void initializeOptions() {
        optionsContainer = new JPanel(new FlowLayout());
        optionsContainer.setBorder(BorderFactory.createEmptyBorder(20, 0, 50, 0));
        optionsContainer.setBackground(Constants.CACAO);
        optionsContainer.setPreferredSize(new Dimension(250, 350));
        menuFrame.add(optionsContainer);

        // Turn display label
        turnDisplay = new JLabel(Constants.START_GAME_TEXT);
        turnDisplay.setFont(Constants.FONT_DISPLAY_TURN_LARGE);
        turnDisplay.setForeground(Constants.CLOUD);
        turnDisplay.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        optionsContainer.add(turnDisplay);

        // Option 1: New Game
        option1Container = createOptionContainer();
        option1 = createMenuButton("New Game", 65);
        option1Container.add(option1);
        optionsContainer.add(option1Container);

        // Option 2: Reset Game
        option2Container = createOptionContainer();
        option2 = createMenuButton("Reset Game", 55);
        option2.setEnabled(false);
        option2Container.add(option2);
        optionsContainer.add(option2Container);

        // Option 3: Load Last Saved Game
        option3Container = createOptionContainer();
        option3 = createMenuButton("Load Last Saved Game", 5);
        option3Container.add(option3);
        optionsContainer.add(option3Container);

        // Option 4: Save Game
        option4Container = createOptionContainer();
        option4 = createMenuButton("Save Game", 60);
        option4.setEnabled(false);
        option4Container.add(option4);
        optionsContainer.add(option4Container);
    }

    // Initialize move history panel
    private void initializeMoveHistory() {
        moveHistoryContainer = new JPanel();
        moveHistoryContainer.setBackground(Constants.CACAO);
        moveHistoryContainer.setPreferredSize(new Dimension(200, 350));
        menuFrame.add(moveHistoryContainer);

        moveHistory = new JTextArea();
        moveHistory.setEditable(false);
        moveHistory.setLineWrap(true);
        moveHistory.setWrapStyleWord(true);
        moveHistory.setFont(Constants.FONT_LABEL);
        moveHistory.setForeground(Constants.CACAO);
        moveHistory.setBackground(Constants.CLOUD);
        moveHistory.setAlignmentX(JTextArea.CENTER_ALIGNMENT);

        JScrollPane moveHistoryScroll = new JScrollPane(moveHistory);
        moveHistoryScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        moveHistoryScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        moveHistoryScroll.setPreferredSize(new Dimension(220, 350));
        moveHistoryContainer.add(moveHistoryScroll);
    }

    // Initialize footer panel
    private void initializeFooter() {
        noTurnDisplayContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        noTurnDisplayContainer.setBackground(Constants.CACAO);
        noTurnDisplayContainer.setPreferredSize(new Dimension(250, 50));
        noTurnDisplayContainer.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        menuFrame.add(noTurnDisplayContainer, BorderLayout.SOUTH);

        noTurnDisplay = new JLabel(Constants.GROUP_CREDIT);
        noTurnDisplay.setFont(Constants.FONT_DISPLAY_TURN_SMALL);
        noTurnDisplay.setForeground(Constants.CLOUD);
        noTurnDisplay.setHorizontalAlignment(SwingConstants.CENTER);
        noTurnDisplay.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        noTurnDisplayContainer.add(noTurnDisplay);
    }

    // Helper method to create option container
    private JPanel createOptionContainer() {
        JPanel container = new JPanel();
        container.setBackground(Constants.CLOUD);
        container.setPreferredSize(new Dimension(220, 50));
        return container;
    }

    // Helper method to create menu button
    private JButton createMenuButton(String text, int padding) {
        JButton button = new JButton(text);
        button.setForeground(Constants.CLOUD);
        button.setFont(Constants.FONT_MENU);
        button.setBackground(Constants.CACAO);
        button.setBorder(BorderFactory.createEmptyBorder(12, padding, 10, padding));
        return button;
    }

    // Method to start the first option dialog
    public void startFirstOption() {
        dialogService.showStartFirstDialog(
            // On Red selected
            () -> {
                startFirstVal = Constants.RED_STARTS;
                view.getBoard().setStartVal(startFirstVal);
                view.getBoard().initializeBoard();
                view.getBoard().refreshBoard(false, false);
                option1.setEnabled(false);
                option2.setEnabled(true);
            },
            // On Blue selected
            () -> {
                startFirstVal = Constants.BLUE_STARTS;
                view.getBoard().setStartVal(startFirstVal);
                view.getBoard().initializeBoard();
                view.getBoard().refreshBoard(false, false);
                option1.setEnabled(false);
                option2.setEnabled(true);
            },
            // On Random selected
            () -> {
                int secs = (int) System.currentTimeMillis() / 1000;
                int randomStartVal = Math.abs(secs) % 2 + 1;
                System.out.println(randomStartVal);

                startFirstVal = randomStartVal;
                view.getBoard().setStartVal(startFirstVal);
                view.getBoard().initializeBoard();
                view.getBoard().refreshBoard(false, false);
                option1.setEnabled(false);
                option2.setEnabled(true);
            }
        );
    }

    // Getter for startFirstVal
    public int getStartFirstVal() {
        return startFirstVal;
    }

    // Setter for startFirstVal
    public void setStartFirstVal(int startFirstVal) {
        this.startFirstVal = startFirstVal;
    }

    // Getters for option buttons
    public JButton getOption1() {
        return option1;
    }

    public JButton getOption2() {
        return option2;
    }

    public JButton getOption3() {
        return option3;
    }

    public JButton getOption4() {
        return option4;
    }

    // Update the turn display label with the current player's turn
    public void updateTurnDisplay(String currentPlayer) {
        turnDisplay.setText(currentPlayer + "'s Turn!");
    }

    // Reset the turn display label to the default text
    public void resetTurnDisplay() {
        turnDisplay.setText(Constants.START_GAME_TEXT);
    }

    // Getter for turnDisplay label
    public JLabel getTurnDisplay() {
        return turnDisplay;
    }

    // Update the no turn display label with the current turn number
    public void updateNoTurnDisplay(int currentTurn) {
        noTurnDisplay.setFont(Constants.FONT_DISPLAY_TURN_LARGE);
        noTurnDisplay.setText("Turn " + (currentTurn + 1));
    }

    // Reset the no turn display label to the default text
    public void resetNoTurnDisplay() {
        noTurnDisplay.setFont(Constants.FONT_DISPLAY_TURN_SMALL);
        noTurnDisplay.setText(Constants.GROUP_CREDIT);
    }

    // Getter for noTurnDisplay label
    public JLabel getNoTurnDisplay() {
        return noTurnDisplay;
    }

    // Update the move history with a move action
    public void updateMoveHistoryMove(int from, int to) {
        moveHistory.append(square[from].getName() + " (" + square[from].getRow() + "," + square[from].getCol() + ") to ("
                + square[to].getRow() + "," + square[to].getCol() + ")" + "\n");
    }

    // Update the move history with an eat action
    public void updateMoveHistoryEat(int from, int to) {
        moveHistory.append(square[from].getName() + " (" + square[from].getRow() + "," + square[from].getCol() + ") eat ("
                + square[to].getRow() + "," + square[to].getCol() + ")" + "\n");
    }

    // Reset the move history text area
    public void resetMoveHistory() {
        moveHistory.setText("");
    }

    // Getter for moveHistory text area
    public JTextArea getMoveHistory() {
        return moveHistory;
    }
}
