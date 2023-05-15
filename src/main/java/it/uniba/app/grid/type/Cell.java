package it.uniba.app.grid.type;


/**
 * Class to manage cells of grid.
 */
public final class Cell {

    /**
     * The state describe if the cell contains a ship or not and if it has been hit.
     */
    private State state;

    /**
     * Cell constructor. Set the state to void.
     */
    public Cell() {
        this.state = State.VOID;
    }

    /**
     * Get the state of a cell.
     * @return state
     */
    public State getState() {
        return state;
    }

    /**
     * Set the state of a cell.
     * @param valState state to set
     */
    private void setState(final State valState) {
        this.state = valState;
    }

    /**
     * Method to check if there is empty.
     * @return boolean
     */
    public boolean isEmpty() {
        return getState() == State.VOID;
    }
}
