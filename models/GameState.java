package models;

/**
 * Data class to hold game state information
 */
public class GameState {
    private int turn;
    private boolean redMoved;
    private boolean blueMoved;
    private int startVal;
    private String boardStateString;
    private String moveHistoryString;

    public GameState() {
    }

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

    public String getBoardStateString() {
        return boardStateString;
    }

    public String getMoveHistoryString() {
        return moveHistoryString;
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

    public void setBoardStateString(String boardStateString) {
        this.boardStateString = boardStateString;
    }

    public void setMoveHistoryString(String moveHistoryString) {
        this.moveHistoryString = moveHistoryString;
    }

    // Helper methods
    public String getBoardState() {
        return boardStateString != null ? boardStateString : "";
    }

    public String getMoveHistory() {
        return moveHistoryString != null ? moveHistoryString : "";
    }
}
