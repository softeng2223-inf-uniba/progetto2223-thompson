package it.uniba.app.grid;

import it.uniba.app.grid.type.Cell;
import it.uniba.app.grid.type.Row;
import java.util.Random;
/**
 * Class to generate and handle the grid.
 */
public class Grid {
    /**
     * Used for generate random elements.
     */
    private static final Random RAND = new Random();
    /**
     * Constant number of rows.
     */
    private static final int MAXROW = 10;

    /**
     * Constant number of columns.
     */
    private static final int MAXCOLUMN = 10;
    /**
     * It represents the game grid.
     */
    private final Cell[][] grid;

    /**
     * Number of total ships placed.
     */
    private int totalShips;

    /**
     * Grid constructor.
     */
    public Grid() {
        this.grid = new Cell[MAXROW][MAXCOLUMN];
        for (int row = 0; row < MAXROW; row++) {
            for (int column = 0; column < MAXCOLUMN; column++) {
                this.grid[row][column] = new Cell();
            }
        }
        this.totalShips = 0;
    }

    /**
     * Method to generate a random row.
     */
    private Row generateRandomRow() {
        return Row.fromInt(RAND.nextInt(MAXROW));
    }

    /**
     * Method to generate a random column.
     */
    private int generateRandomColumn() {
        return RAND.nextInt(MAXCOLUMN);
    }
}
