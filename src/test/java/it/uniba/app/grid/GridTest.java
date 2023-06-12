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

    /**
     * Test checking if a cell on the grid is empty.
     * The cell should be empty.
     */
    @Test
    public void testIsCellEmpty() {
        Coordinate coord = new Coordinate(Column.A, ROW);
        assertTrue(grid.isCellEmpty(coord), "The cell is empty");
    }

    /**
     * Test setting a ship on the grid.
     * All ships should not be sunk.
     */
    @Test
    public void testSetShip() {
        Coordinate coord = new Coordinate(Column.A, ROW);
        Ship ship = Ship.CACCIATORPEDINIERE;
        grid.setShip(ship, 1, coord);
        assertFalse(grid.isAllSunken(), "All ships are not sunken");
    }

    /**
     * Test removing a ship from the grid when a ship exists at the given
     * coordinate.
     * The result should be successful.
     */
    @Test
    public void testRemoveShipShipBoolean() {
        Coordinate coord = new Coordinate(Column.A, ROW2);
        Ship ship = Ship.PORTAEREI;
        grid.setShip(ship, 1, coord);
        ResultRemove result = grid.removeShip(coord);
        assertTrue(result.getResult(), "The result is successful");
    }

    /**
     * Test removing a ship from the grid when a ship exists at the given
     * coordinate.
     * The message should be "colpito e affondato".
     */
    @Test
    public void testRemoveShipShipMessage() {
        Coordinate coord = new Coordinate(Column.A, ROW2);
        Ship ship = Ship.PORTAEREI;
        grid.setShip(ship, 1, coord);
        ResultRemove result = grid.removeShip(coord);
        assertEquals("colpito e affondato", result.getMessage(), "The message is \"colpito e affondato\"");
    }
    
    /**
     * Test removing a ship from the grid when a ship exists at the given
     * coordinate.
     * All ships should be sunk after the removal.
     */
    @Test
    public void testRemoveShipShipAllSunken() {
        Coordinate coord = new Coordinate(Column.A, ROW2);
        Ship ship = Ship.PORTAEREI;
        grid.setShip(ship, 1, coord);
        grid.removeShip(coord);
        assertTrue(grid.isAllSunken(), "All ships are sunken");
    }

    /**
     * Test removing a ship from the grid when no ship exists at the given
     * coordinate.
     * The result should be unsuccessful.
     */
    @Test
    public void testRemoveShipNoShipBoolean() {
        Coordinate coord = new Coordinate(Column.A, ROW2);
        Ship ship = Ship.PORTAEREI;
        grid.setShip(ship, 1, coord);
        coord.setRow(ROW);
        ResultRemove result = grid.removeShip(coord);
        assertFalse(result.getResult(), "The result is unsuccessful");
    }

    /**
     * Test removing a ship from the grid when no ship exists at the given
     * coordinate.
     * The message should be "acqua".
     */
    @Test
    public void testRemoveShipNoShipMessage() {
        Coordinate coord = new Coordinate(Column.A, ROW2);
        Ship ship = Ship.PORTAEREI;
        grid.setShip(ship, 1, coord);
        coord.setRow(ROW);
        ResultRemove result = grid.removeShip(coord);
        assertEquals("acqua", result.getMessage(), "The message is \"acqua\"");
    }

    /**
     * Test removing a ship from the grid when no ship exists at the given
     * coordinate.
     * Not all ships should be sunk after the removal.
     */
    @Test
    public void testRemoveShipNoShipAllSunken() {
        Coordinate coord = new Coordinate(Column.A, ROW2);
        Ship ship = Ship.PORTAEREI;
        grid.setShip(ship, 1, coord);
        coord.setRow(ROW);
        grid.removeShip(coord);
        assertFalse(grid.isAllSunken(), "Not all ships are sunken");
    }
    
    /**
     * Test getting the state of a cell on the grid when the cell is empty.
     * The state should be VOID.
     */
    @Test
    public void testGetStateVoid() {
        Coordinate coord = new Coordinate(Column.A, ROW2);
        assertEquals(State.VOID, grid.getState(coord), "The state is VOID");
    }
    
    /**
     * Test getting the state of a cell on the grid when a ship exists at the cell.
     * The state should be SHIP.
     */
    @Test
    public void testGetStateShip() {
        Coordinate coord = new Coordinate(Column.A, ROW2);
        grid.setCell(coord, Ship.PORTAEREI);
        assertEquals(State.SHIP, grid.getState(coord), "The state is SHIP");
    }

    /**
     * Test setting the state of a cell on the grid.
     * The state should be HIT.
     */
    @Test
    public void testSetState() {
        Coordinate coord = new Coordinate(Column.A, ROW2);
        grid.setCell(coord, Ship.PORTAEREI);
        grid.setState(coord, State.HIT);
        assertEquals(State.HIT, grid.getState(coord), "The state is HIT");
    }

    /**
     * Test checking if a ship is placed at the given coordinate when a ship exists
     * at the coordinate.
     * The result should be true.
     */
    @Test
    public void testIsShipPlacedTrue() {
        Coordinate coord = new Coordinate(Column.A, ROW2);
        grid.setCell(coord, Ship.PORTAEREI);
        assertTrue(grid.isShipPlaced(coord), "The ship is placed");
    }

    /**
     * Test checking if a ship is placed at the given coordinate when no ship exists
     * at the coordinate.
     * The result should be false.
     */
    @Test
    public void testIsShipPlacedFalse() {
        Coordinate coord = new Coordinate(Column.A, ROW2);
        assertFalse(grid.isShipPlaced(coord), "The ship is not placed");
    }

    /**
     * Test getting the color of a ship at the given coordinate.
     * The color should match the ship's color.
     */
    @Test
    public void testGetShipColor() {
        Coordinate coord = new Coordinate(Column.A, ROW2);
        grid.setCell(coord, Ship.PORTAEREI);
        assertEquals(Ship.PORTAEREI.colorShip(), grid.getShipColor(coord), "The color matches the ship's color");
    }
}