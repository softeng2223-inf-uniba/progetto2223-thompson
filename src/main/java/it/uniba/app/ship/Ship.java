package it.uniba.app.ship;

/**
 * Enum class to manage the type and the quantity of ships.
 */
public enum Ship {
    CACCIATORPEDINIERE(2, 4, "\u001B[0;31m"),
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
        return size;
    }

    /**
     * Number of ships getter.
     * 
     * @return nShps
     */
    public int getnShips() {
        return nShips;
    }

    /**
     * return X if there is a ship
     */
    @Override
    public String toString() {
        String ship = " ";
        if (this != null) {
            ship = "X";
        }
        return ship;
    }

    /**
     * this method returns a colored X
     */
    public String colorShip() {
        String colorShip = toString();
        if (this != null) {
            colorShip = this.color + toString() + ANSI_RESET;
        }
        return colorShip;
    }
}
