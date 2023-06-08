package it.uniba.app.grid.type;

/**
 * Enum class to describe cell states.
 */
public enum State {
    VOID,
    SHIP,
    MISS,
    HIT;

    public State hit() {
        if (this == SHIP) {
            return State.HIT;
        } else {
            return State.MISS;
        }
    }
}
