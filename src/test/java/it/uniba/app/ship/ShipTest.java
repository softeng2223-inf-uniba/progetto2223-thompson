package it.uniba.app.ship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Ship enum.
 */
public class ShipTest {
    private static final Ship SHIP = Ship.CACCIATORPEDINIERE;
    private static final int SIZE = 2;
    private static final int N_SHIPS = 4;

    /**
     * Tests the getSize method to ensure it returns the correct size of the ship.
     */
    @Test
    void testGetSize() {
        assertEquals(SIZE, SHIP.getSize());
    }
}