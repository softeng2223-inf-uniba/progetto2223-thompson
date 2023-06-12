package it.uniba.app.ship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Ship enum.
 */
class ShipTest {
    private static final Ship SHIP = Ship.CACCIATORPEDINIERE;
    private static final int SIZE = 2;
    private static final int N_SHIPS = 4;

    /**
     * Tests the getSize method to ensure it returns the correct size of the ship.
     */
    @Test
    void testGetSize() {
        assertEquals(SIZE, SHIP.getSize(), "The size must be 2");
    }

    /**
     * Tests the getnShips method to ensure it returns the correct number of ships.
     */
    @Test
    void testGetnShips() {
        assertEquals(N_SHIPS, SHIP.getnShips(), "The number of ships must be 4");
    }

    /**
     * Tests the stringShip method to ensure it returns the correct string
     * representation of the ship.
     */
    @Test
    void testStringShip() {
        assertEquals("X", Ship.stringShip(), "The string representation must be X");
    }

    /**
     * Tests the colorShip method to ensure it returns the correct colored
     * representation of the ship.
     */
    @Test
    void testColorShip() {
        assertEquals("\u001B[0;32mX\u001B[0m", SHIP.colorShip(),
                "The colored representation must be \u001B[0;32mX\u001B[0m");
    }
}
