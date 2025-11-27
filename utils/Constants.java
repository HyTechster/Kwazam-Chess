package utils;

import java.awt.*;

/**
 * Centralized constants for colors, fonts, dimensions, and magic numbers
 */
public class Constants {

    // ========== Colors ==========
    public static final Color CLOUD = Color.decode("#FFFCF5");
    public static final Color CACAO = Color.decode("#4E2511");
    public static final Color NAVY = Color.decode("#895159");
    public static final Color COBALT_BLUE = Color.decode("#010169");
    public static final Color RED = Color.decode("#FF0000");
    public static final Color MERINGUE = Color.decode("#E8EBED");
    public static final Color BEIGE = Color.decode("#F1E6D6");
    public static final Color LIGHT_BROWN = Color.decode("#A06949");

    // Board highlighting colors
    public static final Color HIGHLIGHT_SELECTED = new Color(255, 131, 131);
    public static final Color HIGHLIGHT_AVAILABLE = new Color(255, 214, 90);

    // ========== Fonts ==========
    public static final Font FONT_MENU = new Font("Courier New", Font.BOLD, 16);
    public static final Font FONT_LABEL = new Font("Courier New", Font.BOLD, 14);
    public static final Font FONT_BUTTON = new Font("Courier New", Font.BOLD, 14);
    public static final Font FONT_DISPLAY_TURN_LARGE = new Font("Courier New", Font.BOLD, 22);
    public static final Font FONT_DISPLAY_TURN_SMALL = new Font("Courier New", Font.BOLD, 16);

    // ========== Dimensions ==========
    public static final Dimension WINDOW_SIZE = new Dimension(800, 850);
    public static final Dimension MAIN_FRAME_SIZE = new Dimension(800, 850);
    public static final Dimension GAME_FRAME_SIZE = new Dimension(550, 850);
    public static final Dimension BOARD_FRAME_SIZE = new Dimension(500, 800);
    public static final Dimension MENU_FRAME_SIZE = new Dimension(250, 850);
    public static final Dimension SQUARE_SIZE = new Dimension(100, 100);

    // ========== Board Constants ==========
    public static final int BOARD_ROWS = 8;
    public static final int BOARD_COLS = 5;
    public static final int TOTAL_SQUARES = BOARD_ROWS * BOARD_COLS; // 40

    // ========== Player Start Values ==========
    public static final int RED_STARTS = 1;
    public static final int BLUE_STARTS = 2;

    // ========== Piece Names ==========
    public static final String RED_RAM = "RedRam";
    public static final String RED_TOR = "RedTor";
    public static final String RED_XOR = "RedXor";
    public static final String RED_BIZ = "RedBiz";
    public static final String RED_SAU = "RedSau";

    public static final String BLUE_RAM = "BlueRam";
    public static final String BLUE_TOR = "BlueTor";
    public static final String BLUE_XOR = "BlueXor";
    public static final String BLUE_BIZ = "BlueBiz";
    public static final String BLUE_SAU = "BlueSau";

    // ========== Piece Types ==========
    public static final String RED_PIECE = "RedPiece";
    public static final String BLUE_PIECE = "BluePiece";
    public static final String SQUARE = "Square";

    // ========== Text Labels ==========
    public static final String GAME_TITLE = "Kwazam Chess";
    public static final String START_GAME_TEXT = "Start the Game!";
    public static final String GROUP_CREDIT = "By Group: Abrar TT7L F";

    // ========== Sound Paths ==========
    public static final String SOUND_MOVE = "./sounds/move.wav";
    public static final String SOUND_MOVED = "./sounds/moved.wav";
    public static final String SOUND_START = "./sounds/start.wav";
    public static final String SOUND_WIN = "./sounds/win.wav";

    // ========== Image Paths ==========
    public static final String IMAGE_LOGO = "./images/logo.png";
    public static final String IMAGE_RED_RAM = "./images/red_ram1.png";
    public static final String IMAGE_RED_RAM_ROTATED = "./images/red_ram2.png";
    public static final String IMAGE_BLUE_RAM = "./images/blue_ram1.png";
    public static final String IMAGE_BLUE_RAM_ROTATED = "./images/blue_ram2.png";

    // Private constructor to prevent instantiation
    private Constants() {
        throw new AssertionError("Constants class should not be instantiated");
    }
}
