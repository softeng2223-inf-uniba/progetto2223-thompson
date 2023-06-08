package it.uniba.app.grid.type;

/**
 * Enum class to describe cell states.
 * Each state can be associated with a color for display purposes.
 */
public enum State {
    VOID("\u001B[0m"),
    SHIP("\u001B[0m"),
    MISS("\u001B[0m"),
    HIT("\u001B[31m");

    private final String color;

    /**
     * ANSI escape code to reset the text color to the default value.
     */
    public static final String ANSI_RESET = "\u001B[0m";

    /**
     * Constructs a state without a color.
     */
    State() {
        this.color = null;
    }

    /**
     * Constructs a state with the specified color.
     */
    State(final String stateColor) {
        this.color = stateColor;
    }

    /**
     * Returns the color associated with the state.
     */
    public String getColor() {
        return color;
    }

    /**
     * Returns the resulting state after a hit.
     * If the state is SHIP, returns HIT; otherwise, returns MISS.
     *
     * @return the resulting state after a hit
     */
    public State hit() {
        if (this == SHIP) {
            return State.HIT;
        } else {
            return State.MISS;
        }
    }
}
