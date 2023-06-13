package it.uniba.app.grid.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniba.app.grid.type.Column;
import it.uniba.app.grid.type.Coordinate;
import it.uniba.app.grid.type.SizeGrid;

/**
 * Unit tests for the GridController class.
 */
class GridControllerTest {
    private static final int ROW = 10;

    /**
     * Set up a new grid before each test.
     */
    @BeforeEach
    public void setUp() {
        SizeGrid.setSize(SizeGrid.LARGE);
        GridController.INSTANCE.newGrid();
    }

    /**
     * Test the hitCoordinate method by shooting at a coordinate on the grid.
     * The result can be "acqua", "colpito", or "colpito e affondato".
     */
    @Test
    void testHitCoordinate() {
        Coordinate coord = new Coordinate(Column.A, ROW);
        String result = GridController.INSTANCE.hitCoordinate(coord);
        assertTrue(result.equals("acqua") || result.equals("colpito") || result.equals("colpito e affondato"),
                "The result is \"acqua\", \"colpito\", or \"colpito e affondato\"");
    }

    /**
     * Test hitting the same coordinate twice.
     * The result should be "Questa mossa è stata già  effettuata".
     */
    @Test
    void testHitCoordinateSameCoordinate() {
        Coordinate coord = new Coordinate(Column.A, ROW);
        String result = GridController.INSTANCE.hitCoordinate(coord);
        result = GridController.INSTANCE.hitCoordinate(coord);
        assertEquals("Questa mossa \u00E8 stata gi\u00E0 effettuata", result,
                "The result is \"Questa mossa \u00E8 stata gi\u00E0 effettuata\"");
    }

    /**
     * Test the hitCoordinate method by shooting at a coordinate out of the grid.
     * The result should be "Questa mossa è stata giÃ  effettuata".
     */
    @Test
    void testHitCoordinateOutGridCoordinate() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> {
                    Coordinate coord = new Coordinate(Column.A, SizeGrid.getSize() + 1);
                    GridController.INSTANCE.hitCoordinate(coord);
                }, "The coordinate is out of the grid");
    }

    /**
     * Test the isAllSunken method when not all ships are sunk.
     * The result should be false.
     */
    @Test
    void testIsAllSunken() {
        assertFalse(GridController.INSTANCE.isAllSunken(), "Not all ships are sunk");
    }
}
