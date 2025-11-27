package services;

import models.GameState;
import java.io.*;

/**
 * Service class responsible for game file operations
 */
public class GameFileService {

    private static final String SAVE_FILE_PATH = "savedGame.txt";
    private static GameFileService instance;

    // Private constructor for singleton pattern
    private GameFileService() {
    }

    // Get singleton instance
    public static GameFileService getInstance() {
        if (instance == null) {
            instance = new GameFileService();
        }
        return instance;
    }

    // Save game state to file
    public void saveGame(GameState gameState) throws IOException {
        FileWriter saveFile = new FileWriter(SAVE_FILE_PATH);
        saveFile.write("Turn: " + gameState.getTurn() + "\n");
        saveFile.write("RedMoved: " + gameState.isRedMoved() + "\n");
        saveFile.write("BlueMoved: " + gameState.isBlueMoved() + "\n");
        saveFile.write("StartVal: " + gameState.getStartVal() + "\n");

        saveFile.write(gameState.getBoardState());
        saveFile.write(gameState.getMoveHistory());
        saveFile.close();
    }

    // Load game state from file
    public GameState loadGame() throws IOException {
        BufferedReader loadFile = new BufferedReader(new FileReader(SAVE_FILE_PATH));
        GameState gameState = new GameState();

        String line;
        String[] splitLine;
        int lineNumber = 1;
        StringBuilder boardState = new StringBuilder();
        StringBuilder moveHistory = new StringBuilder();

        while ((line = loadFile.readLine()) != null) {
            if (lineNumber == 1) {
                splitLine = line.split(" ");
                gameState.setTurn(Integer.parseInt(splitLine[1].trim()));
            } else if (lineNumber == 2) {
                splitLine = line.split(" ");
                gameState.setRedMoved(Boolean.parseBoolean(splitLine[1].trim()));
            } else if (lineNumber == 3) {
                splitLine = line.split(" ");
                gameState.setBlueMoved(Boolean.parseBoolean(splitLine[1].trim()));
            } else if (lineNumber == 4) {
                splitLine = line.split(" ");
                gameState.setStartVal(Integer.parseInt(splitLine[1].trim()));
            } else if (lineNumber > 4 && lineNumber < 13) {
                boardState.append(line).append("\n");
            } else {
                moveHistory.append(line).append("\n");
            }
            lineNumber++;
        }

        gameState.setBoardStateString(boardState.toString());
        gameState.setMoveHistoryString(moveHistory.toString());

        loadFile.close();
        return gameState;
    }

    // Check if save file exists and has content
    public boolean hasSavedGame() {
        try {
            BufferedReader checkFile = new BufferedReader(new FileReader(SAVE_FILE_PATH));
            boolean hasContent = checkFile.readLine() != null;
            checkFile.close();
            return hasContent;
        } catch (IOException e) {
            return false;
        }
    }
}
