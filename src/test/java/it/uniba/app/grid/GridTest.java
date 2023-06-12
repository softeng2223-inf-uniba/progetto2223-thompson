package it.uniba.app.grid;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniba.app.grid.type.Column;
import it.uniba.app.grid.type.Coordinate;
import it.uniba.app.grid.type.ResultRemove;
import it.uniba.app.grid.type.State;
import it.uniba.app.ship.Ship;

/**
 * Unit tests for the Grid class.
 */
public class GridTest {
    private Grid grid;
    private static final int SIZE = 50;
    private static final int ROW = 20;
    private static final int ROW2 = 30;

    /**
     * Set up the grid before each test.
     */
    @BeforeEach
    public void setUp() {
        grid = new Grid(SIZE);
    }

    /**
     * Test getting ships when there are no ships on the grid.
     * The number of ships should be 0.
     */
    @Test
    public void testGetShipsNoShip() {
        int count = 0;
        Map<Ship, Map<Integer, List<Coordinate>>> ships = grid.getShips();
        for (Ship s : ships.keySet()) {
            count += ships.get(s).keySet().size();
        }
        assertEquals(0, count, "The number of ships is 0");
    }
}