package it.uniba.app.grid.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniba.app.grid.type.Column;
import it.uniba.app.grid.type.Coordinate;

/**
 * Unit tests for the GridController class.
 */
public class GridControllerTest {
    private static final int ROW = 10;

    /**
     * Set up a new grid before each test.
     */
    @BeforeEach
    public void setUp() {
        GridController.INSTANCE.newGrid();
    }

    /**
     * Test the hitCoordinate method by shooting at a coordinate on the grid.
     * The result can be "acqua", "colpito", or "colpito e affondato".
     */
    @Test
    public void testHitCoordinate() {
        Coordinate coord = new Coordinate(Column.A, ROW);
        String result = GridController.INSTANCE.hitCoordinate(coord);
        assertTrue(result.equals("acqua") || result.equals("colpito") || result.equals("colpito e affondato"),
                "The result is \"acqua\", \"colpito\", or \"colpito e affondato\"");
    }

    /**
     * Test hitting the same coordinate twice.
     * The result should be "Questa mossa Ã¨ stata giÃ  effettuata".
     */
    @Test
    public void testHitCoordinateSameCoordinate() {
        Coordinate coord = new Coordinate(Column.A, ROW);
        String result = GridController.INSTANCE.hitCoordinate(coord);
        result = GridController.INSTANCE.hitCoordinate(coord);
        assertEquals("Questa mossa Ã¨ stata giÃ  effettuata", result,
                "The result is \"Questa mossa Ã¨ stata giÃ  effettuata\"");
    }

}