package it.uniba.app.ship;


/**
 * Enum class to manage the type and the quantity of ships.
 */
public enum Ship {
    CACCIATORPEDINIERE(2, 4),
    INCROCIATORE(3, 3),
    CORAZZATA(4, 2),
    PORTAEREI(5, 1);

    /**
     * Ship constructor.
     * @param dimension dimension of ship
     * @param valnShips number of placable ships
     */
    Ship(final int dimension, final int valnShips) {
        this.size = dimension;
        this.nShips = valnShips;
    }

    /**
     * Size of the ship.
     */
    private final int size;
    /**
     * Number of the used ships.
     */
    private final int nShips;


    /**
     * Size getter.
     * @return size
     */
    public int getSize() {
        return size;
    }

    /**
     * Number of ships getter.
     * @return nShps
     */
    public int getnShips() {
        return nShips;
    }
}
