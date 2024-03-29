package it.uniba.app.ship;

/**
 * <Entity>
 *
 * The Ship class is an enumeration that represents different types of ships in
 * a game. Each ship type has a specific size, number of ships available, and
 * color associated with it.
 */
public enum Ship {
    CACCIATORPEDINIERE(2, 4, "\u001B[0;32m"),
    INCROCIATORE(3, 3, "\u001B[0;31m"),
    CORAZZATA(4, 2, "\u001B[0;33m"),
    PORTAEREI(5, 1, "\u001B[0;35m");

    /**
     * Ship constructor.
     *
     * @param dimension dimension of ship
     * @param valnShips number of placable ships
     * @param valColor  Color of ship
     */
    Ship(final int dimension, final int valnShips, final String valColor) {
        this.size = dimension;
        this.nShips = valnShips;
        this.color = valColor;
    }

    /**
     * terminator character for ship color.
     */
    private static final String ANSI_RESET = "\u001B[0m";
    /**
     * Size of the ship.
     */
    private final int size;
    /**
     * Number of the used ships.
     */
    private final int nShips;
    /**
     * Color of ship.
     */
    private final String color;

    /**
     * Size getter.
     *
     * @return size
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Number of ships getter.
     *
     * @return nShps
     */
    public int getnShips() {
        return this.nShips;
    }

    /**
     * This method returns X if there is a ship.
     */
    public static String stringShip() {
        return "X";
    }

    /**
     * This method returns a colored X.
     */
    public String colorShip() {
        return this.color + stringShip() + ANSI_RESET;
    }
}
