package it.uniba.app.grid;

import it.uniba.app.grid.type.Cell;

public class Grid {
    private static final int MAXROW = 10;
    private static final int MAXCOLUMN = 10;
    private final Cell[][] grid;

    private int totalShips;

    public Grid() {
        this.grid = new Cell[MAXROW][MAXCOLUMN];
        for (int row = 0; row < MAXROW; row++) {
            for (int column = 0; column < MAXCOLUMN; column++) {
                this.grid[row][column] = new Cell();
            }
        }
        this.totalShips = 0;
    }
}
