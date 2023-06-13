package it.uniba.app.grid.type;

/**
 * <noECB>
 *
 * The Column enum class is responsible for representing the columns in a
 * coordinate system.
 * It provides a set of predefined column values, from A to Z, to describe the
 * columns in a grid.
 */
public enum Column {
    A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z;

    /**
     * This array contains column values, is used as a buffer so as not to recreate
     * it
     * each time.
     */
    private static final Column[] VALUES = Column.values();

    /**
     * Method to convert integer to column type.
     *
     * @param value integer
     * @return value or column
     */
    public static Column fromInt(final int value) {
        if (value < 0 || value >= VALUES.length) {
            return null;
        }
        return VALUES[value];
    }

    /**
     * Column to integer getter.
     *
     * @return integer of column
     */
    public int getColumnInt() {
        return this.ordinal();
    }
}
