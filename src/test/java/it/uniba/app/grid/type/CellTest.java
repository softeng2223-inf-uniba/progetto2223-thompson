package it.uniba.app.grid.type;

import it.uniba.app.ship.Ship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Cell class.
 */
class CellTest {
    private Cell cell;
    private Ship ship;

    @BeforeEach
    void setUp() {
        cell = new Cell();
        ship = Ship.CACCIATORPEDINIERE;
    }

    /**
     * Test the initial state of the cell.
     */
    @Test
    void testInitialState() {
        assertEquals(State.VOID, cell.getState(), "The initial state must be VOID");
    }

    /**
     * Test the initial ship of the cell.
     */
    @Test
    void testInitialShip() {
        assertNull(cell.getShip(), "The initial ship must be null");
    }

    /**
     * Test if the initial cell is empty.
     */
    @Test
    void testInitialIsEmpty() {
        assertTrue(cell.isEmpty(), "The initial cell must be empty");
    }

    /**
     * Test setting the state of the cell.
     */
    @Test
    void testSetState() {
        cell.setShip(ship);
        cell.setState(State.HIT);
        assertEquals(State.HIT, cell.getState(), "The state must be HIT");
    }

    /**
     * Test setting the ship of the cell.
     */
    @Test
    void testSetShip() {
        cell.setShip(ship);
        assertEquals(ship, cell.getShip(), "The ship must be CACCIATORPEDINIERE");
    }

    /**
     * Test if the cell is empty.
     */
    @Test
    void testIsEmpty() {
        cell.setShip(ship);
        assertFalse(cell.isEmpty(), "The cell must not be empty");
    }
}
