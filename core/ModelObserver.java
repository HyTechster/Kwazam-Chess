package core;

/**
 * Observer interface for model updates
 * Method to be called when the model is updated
 * redMoved indicates if the red player has moved
 * blueMoved indicates if the blue player has moved
 */
public interface ModelObserver {
    void onModelUpdate(boolean redMoved, boolean blueMoved);
}
