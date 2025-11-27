package services;

import utils.Constants;

/**
 * Manages game state including turns, moves, and game flow
 */
public class GameStateManager {

    private int turn = 0;
    private boolean redMoved = false;
    private boolean blueMoved = false;
    private int startVal;

    // Getters
    public int getTurn() {
        return turn;
    }

    public boolean isRedMoved() {
        return redMoved;
    }

    public boolean isBlueMoved() {
        return blueMoved;
    }

    public int getStartVal() {
        return startVal;
    }

    // Setters
    public void setTurn(int turn) {
        this.turn = turn;
    }

    public void setRedMoved(boolean redMoved) {
        this.redMoved = redMoved;
    }

    public void setBlueMoved(boolean blueMoved) {
        this.blueMoved = blueMoved;
    }

    public void setStartVal(int startVal) {
        this.startVal = startVal;
    }

    // Game state operations
    public void incrementTurn() {
        turn++;
    }

    public void resetState() {
        turn = 0;
        redMoved = false;
        blueMoved = false;
    }

    public boolean bothPlayersHaveMoved() {
        return redMoved && blueMoved;
    }

    public boolean shouldTransformPieces() {
        return turn % 2 == 0 && !redMoved && !blueMoved;
    }

    public String getCurrentPlayer() {
        if (startVal == Constants.RED_STARTS) {
            return redMoved ? "Blue" : "Red";
        } else {
            return blueMoved ? "Red" : "Blue";
        }
    }
}
