package it.uniba.app.grid.type;

import it.uniba.app.ship.Ship;

/**
 * <Entity>
 *
 * Class to manage cells of grid.
 */
public final class Cell {

    /**
     * The Cell class is responsible for managing the individual cells of a grid.
     * It represents a single cell and contains information about its state and the
     * ship occupying the cell.
     */
    private State state;

    /**
     * The ship of the current cell.
     */
    private Ship ship;

    /**
     * Cell constructor. Set the state to void.
     */
    public Cell() {
        this.state = State.VOID;
        this.ship = null;
    }

    /**
     * Get the state of a cell.
     *
     * @return state
     */
    public State getState() {
        return state;
    }

    /**
     * Set the state of a cell.
     *
     * @param valState state to set
     */
    public void setState(final State valState) {
        if (valState == null) {
            this.state = State.VOID;
        } else {
            this.state = valState;
        }
    }

    /**
     * Method to check if there is empty.
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return ship == null;
    }

    /**
     * Ship getter.
     *
     * @return ship
     */
    public Ship getShip() {
        return ship;
    }

    /**
     * Ship setter.
     *
     * @param valShip ship to set
     */
    public void setShip(final Ship valShip) {
        this.ship = valShip;
        if (valShip != null) {
            this.setState(State.SHIP);
        } else {
            this.setState(State.VOID);
        }
    }
}
