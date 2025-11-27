package core;

import ui.View;
import pieces.Square;
import models.GameState;
import services.*;
import utils.Constants;
import java.util.*;
import java.io.*;

/**
 * Model class - Coordinates game logic using service classes
 */
public class Model {

    // Dependencies
    private View view;
    private Square[] square;
    private ArrayList<ModelObserver> observers;

    // Service classes
    private GameStateManager stateManager;
    private BoardManager boardManager;
    private PieceMoveCalculator moveCalculator;
    private PieceMovementHandler movementHandler;
    private PieceTransformationService transformationService;
    private GameFileService fileService;
    private BoardSerializationService serializationService;

    public Model(View view) {
        this.view = view;
        this.square = view.getBoard().getSquare();
        this.observers = new ArrayList<ModelObserver>();

        // Initialize services
        this.stateManager = new GameStateManager();
        this.boardManager = new BoardManager(square);
        this.moveCalculator = new PieceMoveCalculator(square, boardManager);
        this.movementHandler = new PieceMovementHandler(square, view.getBoard().getBoardFrame());
        this.transformationService = new PieceTransformationService(square, view.getBoard().getBoardFrame());
        this.fileService = GameFileService.getInstance();
        this.serializationService = new BoardSerializationService(square);
    }

    // ========== Observer Pattern ==========

    public void addObserver(ModelObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(boolean redMoved, boolean blueMoved) {
        for (ModelObserver observer : observers) {
            observer.onModelUpdate(redMoved, blueMoved);
        }
    }

    // ========== Game Initialization ==========

    public void game(int startFirstVal) {
        stateManager.setStartVal(startFirstVal);

        if (startFirstVal == Constants.RED_STARTS) {
            boardManager.disableBlue();
            boardManager.disableSquares();
            notifyTurn("Red");
        } else {
            boardManager.disableRed();
            boardManager.disableSquares();
            notifyTurn("Blue");
        }

        notifyCurrentTurnNumber(stateManager.getTurn());
    }

    // ========== Piece Click Handling ==========

    public void pieceClicked(int noSquare) {
        String pieceType = square[noSquare].getType();

        // Disable appropriate pieces based on clicked piece
        if (pieceType.equals(Constants.RED_PIECE)) {
            boardManager.disableRed();
        } else if (pieceType.equals(Constants.BLUE_PIECE)) {
            boardManager.disableBlue();
        }

        // Calculate and highlight valid moves
        moveCalculator.calculateAndHighlightMoves(noSquare, new PieceMoveCalculator.MoveHandler() {
            @Override
            public void handleMove(int from, int to, boolean isCapture) {
                if (isCapture) {
                    handleCapture(from, to);
                } else {
                    handleNormalMove(from, to);
                }
            }
        });
    }

    public void pieceClicked2(int noSquare) {
        // Reset board state when piece is clicked second time (deselect)
        boardManager.resetAllPieceClicks();
        boardManager.addAllListeners();

        String pieceType = square[noSquare].getType();

        if (pieceType.equals(Constants.RED_PIECE)) {
            boardManager.enableRed();
            boardManager.disableBlue();
            boardManager.disableSquares();
        } else if (pieceType.equals(Constants.BLUE_PIECE)) {
            boardManager.enableBlue();
            boardManager.disableRed();
            boardManager.disableSquares();
        }
    }

    // ========== Move Execution ==========

    private void handleNormalMove(int fromSquare, int toSquare) {
        notifyMoveHistoryMove(fromSquare, toSquare);
        Controller.getController().enableSaveGame();
        Controller.getController().enableLoadGame();
        Controller.getController().playSound(Constants.SOUND_MOVED);

        // Get piece name BEFORE moving
        String pieceName = square[fromSquare].getName();

        // Execute the move
        movementHandler.executeMove(fromSquare, toSquare, false);

        // Update game state and switch turns
        updateGameStateAfterMove(pieceName);
    }

    private void handleCapture(int fromSquare, int toSquare) {
        notifyMoveHistoryEat(fromSquare, toSquare);

        // Check if Sau is captured (game ending condition)
        String capturedPieceName = movementHandler.getCapturedPieceName(toSquare);
        boolean sauCaptured = movementHandler.isSauCaptured(toSquare);

        Controller.getController().enableSaveGame();
        Controller.getController().enableLoadGame();
        Controller.getController().playSound(Constants.SOUND_MOVED);

        // Get piece name BEFORE moving
        String pieceName = square[fromSquare].getName();

        // Execute the move
        movementHandler.executeMove(fromSquare, toSquare, true);

        // Update game state
        updateGameStateAfterMove(pieceName);

        // Check for game over
        if (sauCaptured) {
            gameOver(capturedPieceName);
        }
    }

    private void updateGameStateAfterMove(String pieceName) {

        // Update move flags
        if (pieceName.startsWith("Red")) {
            stateManager.setRedMoved(true);
            boardManager.enableBlue();
            boardManager.disableRed();
            boardManager.disableSquares();
            notifyTurn("Blue");
        } else if (pieceName.startsWith("Blue")) {
            stateManager.setBlueMoved(true);
            boardManager.enableRed();
            boardManager.disableBlue();
            boardManager.disableSquares();
            notifyTurn("Red");
        }

        // Check if both players have moved
        if (stateManager.bothPlayersHaveMoved()) {
            stateManager.incrementTurn();
            stateManager.setRedMoved(false);
            stateManager.setBlueMoved(false);
        }

        // Transform pieces every 2 turns
        if (stateManager.shouldTransformPieces()) {
            transformationService.transformTorXorPieces();

            // Reset board based on starting player
            if (stateManager.getStartVal() == Constants.RED_STARTS) {
                boardManager.enableRed();
                boardManager.disableBlue();
                boardManager.disableSquares();
            } else {
                boardManager.enableBlue();
                boardManager.disableRed();
                boardManager.disableSquares();
            }
        }

        notifyCurrentTurnNumber(stateManager.getTurn());
        boardManager.resetAllPieceClicks();
        boardManager.addAllListeners();
        notifyObservers(stateManager.isRedMoved(), stateManager.isBlueMoved());
    }

    // ========== Game State Management ==========

    public void resetAll() {
        boardManager.resetAllPieceClicks();
        stateManager.resetState();
        view.getMenu().resetTurnDisplay();
        view.getMenu().resetNoTurnDisplay();
        view.getMenu().resetMoveHistory();
    }

    public void gameOver(String loser) {
        if (loser.equals(Constants.RED_SAU)) {
            view.gameOverDialog(Constants.BLUE_SAU);
        } else if (loser.equals(Constants.BLUE_SAU)) {
            view.gameOverDialog(Constants.RED_SAU);
        }

        resetAll();
        System.out.println("Game Over");
        notifyObservers(stateManager.isRedMoved(), stateManager.isBlueMoved());
    }

    // ========== Notification Methods ==========

    public void notifyTurn(String currentPlayer) {
        view.getMenu().updateTurnDisplay(currentPlayer);
    }

    public void notifyCurrentTurnNumber(int currentTurn) {
        view.getMenu().updateNoTurnDisplay(currentTurn);
    }

    public void notifyMoveHistoryMove(int from, int to) {
        view.getMenu().updateMoveHistoryMove(from, to);
    }

    public void notifyMoveHistoryEat(int from, int to) {
        view.getMenu().updateMoveHistoryEat(from, to);
    }

    // ========== Save/Load Game ==========

    public void saveCurrentGame() {
        try {
            GameState gameState = new GameState();
            gameState.setTurn(stateManager.getTurn());
            gameState.setRedMoved(stateManager.isRedMoved());
            gameState.setBlueMoved(stateManager.isBlueMoved());
            gameState.setStartVal(stateManager.getStartVal());

            // Use serialization service to build board state
            String boardState = serializationService.buildBoardState();
            gameState.setBoardStateString(boardState);
            gameState.setMoveHistoryString(view.getMenu().getMoveHistory().getText());

            fileService.saveGame(gameState);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadGame() {
        try {
            GameState gameState = fileService.loadGame();

            stateManager.setTurn(gameState.getTurn());
            stateManager.setRedMoved(gameState.isRedMoved());
            stateManager.setBlueMoved(gameState.isBlueMoved());
            stateManager.setStartVal(gameState.getStartVal());

            // Use serialization service to restore board state
            serializationService.restoreBoardState(gameState.getBoardStateString(), view.getBoard().getBoardFrame());

            // Restore move history
            view.getMenu().getMoveHistory().append(gameState.getMoveHistoryString());

            view.getBoard().setStartVal(stateManager.getStartVal());
            view.getMenu().setStartFirstVal(stateManager.getStartVal());
            notifyCurrentTurnNumber(stateManager.getTurn());

            // Update turn display based on current state
            String currentPlayer = stateManager.getCurrentPlayer();
            notifyTurn(currentPlayer);

            notifyObservers(stateManager.isRedMoved(), stateManager.isBlueMoved());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
