package it.uniba.app.grid.type;

/**
 * Enumeration representing the possible states of a grid cell.
 */
public enum State {
    VOID,
    SHIP,
    MISS,
    HIT;

    /**
     * Performs a hit action on the state and returns the resulting state.
     *
     * @return the resulting state after the hit action, which can be
     *         {@link State#HIT} if the state is a ship,
     *         or {@link State#MISS} if the state is not a ship
     */
    public State hit() {
        if (this == SHIP) {
            return State.HIT;
        } else {
            return State.MISS;
        }
    }
}
