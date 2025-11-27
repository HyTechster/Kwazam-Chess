package ui;

import utils.Constants;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Service for creating and managing all dialog windows
 */
public class DialogService {

    /**
     * Show game over dialog
     */
    public void showGameOverDialog(String winner, ActionListener closeAction) {
        JDialog dialog = createDialog("Game Over", 300, 150);

        JPanel panel = createDialogPanel();
        dialog.add(panel);

        String winnerTeam = winner.equals("RedSau") ? "Red" : "Blue";
        JLabel label = createLabel(winnerTeam + " has won the game!");
        label.setBorder(BorderFactory.createEmptyBorder(0, 50, 10, 50));
        panel.add(label);

        JButton closeButton = createButton("Close");
        closeButton.addActionListener(e -> {
            closeAction.actionPerformed(e);
            dialog.dispose();
        });
        panel.add(closeButton);

        showDialog(dialog);
    }

    /**
     * Show window closing confirmation dialog
     */
    public void showWindowClosingDialog(Runnable onYes) {
        JDialog dialog = createDialog("Exit Kwazam Chess?", 450, 200);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Constants.CLOUD);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        dialog.add(mainPanel);

        JLabel label = createLabel("Are you sure you want to exit this game?");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        mainPanel.add(label);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Constants.CLOUD);

        JButton yesButton = createButton("Yes");
        yesButton.addActionListener(e -> {
            dialog.dispose();
            onYes.run();
        });
        buttonPanel.add(yesButton);

        JButton noButton = createButton("No");
        noButton.addActionListener(e -> dialog.dispose());
        buttonPanel.add(noButton);

        mainPanel.add(buttonPanel);
        showDialog(dialog);
    }

    /**
     * Show restart game confirmation dialog
     */
    public void showRestartGameDialog(Runnable onYes) {
        JDialog dialog = createDialog("Reset the game?", 450, 200);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Constants.CLOUD);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        dialog.add(mainPanel);

        JLabel label = createLabel("Are you sure you want to reset the game?");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        mainPanel.add(label);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Constants.CLOUD);

        JButton yesButton = createButton("Yes");
        yesButton.addActionListener(e -> {
            dialog.dispose();
            onYes.run();
        });
        buttonPanel.add(yesButton);

        JButton noButton = createButton("No");
        noButton.addActionListener(e -> dialog.dispose());
        buttonPanel.add(noButton);

        mainPanel.add(buttonPanel);
        showDialog(dialog);
    }

    /**
     * Show save game confirmation dialog
     */
    public void showSaveDialog(Runnable onYes) {
        JDialog dialog = createDialog("Save Game?", 450, 200);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Constants.CLOUD);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        dialog.add(mainPanel);

        JLabel label = createLabel("Are you sure you want to save this game?");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        mainPanel.add(label);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Constants.CLOUD);

        JButton yesButton = createButton("Yes");
        yesButton.addActionListener(e -> {
            onYes.run();
            dialog.dispose();
        });
        buttonPanel.add(yesButton);

        JButton noButton = createButton("No");
        noButton.addActionListener(e -> dialog.dispose());
        buttonPanel.add(noButton);

        mainPanel.add(buttonPanel);
        showDialog(dialog);
    }

    /**
     * Show load game confirmation dialog
     */
    public void showLoadDialog(Runnable onYes) {
        JDialog dialog = createDialog("Load Game?", 500, 200);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Constants.CLOUD);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        dialog.add(mainPanel);

        JLabel label = createLabel("Are you sure you want to load last saved game?");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        mainPanel.add(label);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Constants.CLOUD);

        JButton yesButton = createButton("Yes");
        yesButton.addActionListener(e -> {
            onYes.run();
            dialog.dispose();
        });
        buttonPanel.add(yesButton);

        JButton noButton = createButton("No");
        noButton.addActionListener(e -> dialog.dispose());
        buttonPanel.add(noButton);

        mainPanel.add(buttonPanel);
        showDialog(dialog);
    }

    /**
     * Show no saved game dialog
     */
    public void showNoSavedGameDialog(Runnable onClose) {
        JDialog dialog = createDialog("No Saved Game", 300, 150);

        JPanel panel = createDialogPanel();
        dialog.add(panel);

        JLabel label = createLabel("No saved game found!");
        label.setBorder(BorderFactory.createEmptyBorder(0, 50, 10, 50));
        panel.add(label);

        JButton closeButton = createButton("Close");
        closeButton.addActionListener(e -> {
            onClose.run();
            dialog.dispose();
        });
        panel.add(closeButton);

        showDialog(dialog);
    }

    /**
     * Show who starts first dialog
     */
    public void showStartFirstDialog(Runnable onRed, Runnable onBlue, Runnable onRandom) {
        JDialog dialog = createDialog("Who Starts First?", 400, 200);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Constants.CLOUD);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        dialog.add(mainPanel);

        JLabel label = createLabel("Who is going to start first?");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        mainPanel.add(label);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Constants.CLOUD);

        JButton redButton = createColoredButton("Red", Constants.RED, Constants.MERINGUE);
        redButton.addActionListener(e -> {
            onRed.run();
            dialog.dispose();
        });
        buttonPanel.add(redButton);

        JButton blueButton = createColoredButton("Blue", Constants.COBALT_BLUE, Constants.MERINGUE);
        blueButton.addActionListener(e -> {
            onBlue.run();
            dialog.dispose();
        });
        buttonPanel.add(blueButton);

        JButton randomButton = createColoredButton("Random", Constants.CACAO, Constants.MERINGUE);
        randomButton.addActionListener(e -> {
            onRandom.run();
            dialog.dispose();
        });
        buttonPanel.add(randomButton);

        mainPanel.add(buttonPanel);
        showDialog(dialog);
    }

    // ========== Helper Methods ==========

    private JDialog createDialog(String title, int width, int height) {
        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setSize(width, height);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setTitle(title);
        return dialog;
    }

    private JPanel createDialogPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(Constants.CLOUD);
        panel.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));
        return panel;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(Constants.FONT_LABEL);
        label.setForeground(Constants.CACAO);
        return label;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setForeground(Constants.CACAO);
        button.setBackground(Constants.CLOUD);
        return button;
    }

    private JButton createColoredButton(String text, Color fg, Color bg) {
        JButton button = new JButton(text);
        button.setForeground(fg);
        button.setBackground(bg);
        button.setFont(Constants.FONT_BUTTON);
        return button;
    }

    private void showDialog(JDialog dialog) {
        dialog.revalidate();
        dialog.repaint();
        dialog.setVisible(true);
    }
}
