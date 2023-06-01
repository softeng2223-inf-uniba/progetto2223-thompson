package it.uniba.app.grid.type;

/**
 * Enum class to describe coordinate rows.
 */
public enum Row {
    A, B, C, D, E, F, G, H, I, J;

    /**
     * This array contains row values, is used as a buffer so as not to recreate it
     * each time.
     */
    private static final Row[] VALUES = Row.values();

    /**
     * Method to convert integer to row type.
     * @param value integer
     * @return value or row
     */
    public static Row fromInt(final int value) {
        if (value < 0 || value > VALUES.length) {
            return null;
        }
        return VALUES[value];
    }
}
