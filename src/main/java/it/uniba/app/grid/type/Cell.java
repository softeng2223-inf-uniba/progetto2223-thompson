package it.uniba.app.grid.type;

import it.uniba.app.ship.Ship;

/**
 * Class to manage cells of grid.
 */
public final class Cell {

    /**
     * The state describe if the cell contains a ship or not and if it has been hit.
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

    /**
     * Ship getter.
     * @return ship
     */
    public Ship getShip() {
        return ship;
    }

    /**
     * Ship setter.
     * @param valShip ship to set
     */
    public void setShip(final Ship valShip) {
        this.ship = valShip;
        setState(State.SHIP);
    }


    /**
     * this method return X if there is a ship in a specific cell, else nothing
     */
    /*@Override
    public String toString() {
        String cellContent=" ";
        if(this.state==State.SHIP){
            cellContent="X";
        }
        return cellContent;
    }*/
}
