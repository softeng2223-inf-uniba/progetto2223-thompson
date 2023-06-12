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
}