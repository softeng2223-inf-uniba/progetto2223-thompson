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

    /**
     * Test getting ships when there is a ship on the grid.
     * The number of ships should be 1.
     */
    @Test
    public void testGetShipsShips() {
        int count = 0;
        Coordinate coord = new Coordinate(Column.A, 1);
        Ship ship = Ship.CACCIATORPEDINIERE;
        grid.setCell(coord, ship);
        grid.setShip(ship, 1, coord);
        Map<Ship, Map<Integer, List<Coordinate>>> ships = grid.getShips();
        System.out.println(ships);
        for (Ship s : ships.keySet()) {
            count += ships.get(s).keySet().size();
        }
        assertEquals(1, count, "The number of ships is 1");
    }

    /**
     * Test the isAllSunken method when all ships are sunk.
     * The result should be true.
     */
    @Test
    public void testIsAllSunkenTrue() {
        assertTrue(grid.isAllSunken(), "All ships are sunken");
    }

    /**
     * Test the isAllSunken method when not all ships are sunk.
     * The result should be false.
     */
    @Test
    public void testIsAllSunkenFalse() {
        Coordinate coord = new Coordinate(Column.A, 1);
        Ship ship = Ship.CACCIATORPEDINIERE;
        grid.setCell(coord, ship);
        grid.setShip(ship, 1, coord);
        assertFalse(grid.isAllSunken(), "Not all ships are sunken");
    }

    /**
     * Test the getSize method.
     * The result should be the size of the grid, which is 50.
     */
    @Test
    public void testGetSize() {
        assertEquals(SIZE, grid.getSize(), "The size of the grid is 50");
    }

    /**
     * Test setting a cell on the grid with a ship.
     * The cell should not be empty.
     */
    @Test
    public void testSetCell() {
        Coordinate coord = new Coordinate(Column.B, ROW);
        Ship ship = Ship.INCROCIATORE;
        grid.setCell(coord, ship);
        assertFalse(grid.isCellEmpty(coord), "The cell is not empty");
    }


}