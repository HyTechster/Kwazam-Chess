package ui;

import core.Controller;
import services.AudioService;
import utils.Constants;
import java.awt.*;
import javax.swing.*;

/**
 * View class - Main UI container
 */
public class View extends JFrame {

    private JPanel mainFrame;
    private JPanel gameFrame;
    private JPanel boardFrame;
    private JPanel menuFrame;

    private Board board;
    private Menu menu;
    private DialogService dialogService;

    // Constructor to initialize the view
    public View() {
        // Base frame
        super(Constants.GAME_TITLE);
        setSize(Constants.WINDOW_SIZE);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);

        // Initialize dialog service
        this.dialogService = new DialogService();

        // Main frame
        mainFrame = new JPanel(new BorderLayout());
        mainFrame.setBackground(Constants.CLOUD);
        mainFrame.setMinimumSize(Constants.MAIN_FRAME_SIZE);
        add(mainFrame);

        // Game frame
        gameFrame = new JPanel(new GridBagLayout());
        gameFrame.setBackground(Constants.CLOUD);
        gameFrame.setPreferredSize(Constants.GAME_FRAME_SIZE);
        gameFrame.setMinimumSize(Constants.GAME_FRAME_SIZE);
        mainFrame.add(gameFrame, BorderLayout.CENTER);

        // Board frame
        boardFrame = new JPanel(new GridLayout(Constants.BOARD_ROWS, Constants.BOARD_COLS));
        boardFrame.setBackground(Constants.CACAO);
        boardFrame.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        boardFrame.setPreferredSize(Constants.BOARD_FRAME_SIZE);
        boardFrame.setMinimumSize(Constants.BOARD_FRAME_SIZE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gameFrame.add(boardFrame, gbc);

        // Menu frame
        menuFrame = new JPanel(new BorderLayout());
        menuFrame.setBackground(Constants.CLOUD);
        menuFrame.setLayout(new BoxLayout(menuFrame, BoxLayout.Y_AXIS));
        menuFrame.setPreferredSize(Constants.MENU_FRAME_SIZE);
        mainFrame.add(menuFrame, BorderLayout.EAST);

        // Initialize Board and Menu
        board = new Board(this, boardFrame);
        menu = new Menu(this, menuFrame);
    }

    // Getter for board
    public Board getBoard() {
        return board;
    }

    // Getter for menu
    public Menu getMenu() {
        return menu;
    }

    // Method to refresh the view
    public void refreshView() {
        this.revalidate();
        this.repaint();
    }

    // Method to display game over dialog
    public void gameOverDialog(String winner) {
        AudioService.getInstance().playSound(Constants.SOUND_WIN);

        dialogService.showGameOverDialog(winner, e -> {
            menu.getOption1().setEnabled(true);
            menu.getOption2().setEnabled(false);
            board.restartPos();
            Controller.getController().resetGame();
            menu.getOption4().setEnabled(false);
            refreshView();
        });
    }

    // Method to display window closing dialog
    public void windowClosingDialog() {
        dialogService.showWindowClosingDialog(() -> System.exit(0));
    }

    // Method to display restart game dialog
    public void restartGameDialog() {
        dialogService.showRestartGameDialog(() -> {
            board.restartPos();
            Controller.getController().resetGame();
            menu.getOption1().setEnabled(true);
            menu.getOption2().setEnabled(false);
            refreshView();
        });
    }

    // Method to display save game dialog
    public void saveDialog() {
        dialogService.showSaveDialog(() -> {
            Controller.getController().saveGame();
            menu.getOption1().setEnabled(false);
            menu.getOption2().setEnabled(true);
            menu.getOption3().setEnabled(false);
            menu.getOption4().setEnabled(false);
        });
    }

    // Method to display load game dialog
    public void loadDialog() {
        dialogService.showLoadDialog(() -> {
            menu.getOption1().setEnabled(false);
            menu.getOption2().setEnabled(true);
            menu.getOption3().setEnabled(false);
            menu.getOption4().setEnabled(false);
            Controller.getController().loadGame();
        });
    }

    // Method to display no saved game dialog
    public void noSavedGameDialog() {
        dialogService.showNoSavedGameDialog(() -> {
            menu.getOption1().setEnabled(true);
            menu.getOption2().setEnabled(false);
            menu.getOption3().setEnabled(true);
            menu.getOption4().setEnabled(false);
        });
    }
}
