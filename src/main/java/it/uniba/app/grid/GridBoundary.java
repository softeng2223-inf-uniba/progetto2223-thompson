package it.uniba.app.grid;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import it.uniba.app.grid.type.Cell;
import it.uniba.app.grid.type.Column;
import it.uniba.app.grid.type.Coordinate;
import it.uniba.app.grid.type.State;
import it.uniba.app.ship.Direction;
import it.uniba.app.ship.Ship;

/**
 * Utility class for printing grids with ships and their states.
 */
public abstract class GridBoundary {

    /**
     * The formatter for column values.
     */
    private static String formatterCol = "";

    /**
     * The formatter for row separators.
     */
    private static String formatterRow = "";

    /**
     * The formatter for empty cells.
     */
    private static String formatterVoid = "";

    /**
     * The formatter for space characters.
     */
    private static String formatterSpace = "";

    /**
     * The maximum size for a small grid.
     */
    private static final int MAX_SIZE_SMALL = 10;

    /**
     * The maximum size for a medium grid.
     */
    private static final int MAX_SIZE_MEDIUM = 18;

    /**
     * The maximum size for a large grid.
     */
    private static final int MAX_SIZE_LARGE = 26;

    /**
     * Shoots at the specified coordinate on the grid and returns the result of the
     * shot.
     *
     * @param coord the coordinate to shoot at
     * @return the result of the shot, which can be "acqua" (water), "colpito"
     *         (hit), "colpito e affondato" (hit and sunk),
     *         or "Questa mossa è stata già effettuata" (This move has already been
     *         made)
     */
    public abstract String hitCoordinate(final Coordinate coord);

    /**
     * Method that adds ships to the dictionary with their coordinates.
     *
     * @param ship
     * @param nShip
     * @param direction
     * @param coord
     */
    public abstract void addShips(final Ship ship, final int nShip, final Direction direction, final Coordinate coord);

    /**
     * Method to check if we can place a ship in a given coordinate.
     *
     * @param direction direction of ship
     * @param dimension dimension of ship
     * @param coord     coordinate where to place the ship
     * @return true if can be placed else false
     */
    public abstract boolean canPlaceShip(final Direction direction, final Ship dimension, final Coordinate coord);

    /**
     * Method to place ships in the grid.
     *
     * @param direction direction of the ship
     * @param ship      ship to place
     * @param coord     initial coordinate
     */
    public abstract void placeShip(final Direction direction, final Ship ship, final Coordinate coord);

    /**
     * Method to create a grid whit random ships.
     */
    public abstract void generateGrid();

    /**
     * Method to display the grid with the ships.
     * Format the grid display according to its size
     *
     * @param grid The grid to be printed.
     * @param size The size of the grid.
     */
    public void printGrid(final Cell[][] grid, final int size) {
        GridBoundary.setFormatters(size);
        StringBuilder legend = new StringBuilder();
        for (Ship ship : Ship.values()) {
            legend.append(ship.toString() + " = " + ship.colorShip() + " ");
        }
        printLegend(legend.toString());
        printHeader(size);
        for (int i = 0; i < size; i++) {
            printSeparator(size);
            printIndexColumn(i);
            for (int j = 0; j < size; j++) {
                printCell(grid[i][j].getShip() != null
                        ? formatterSpace + grid[i][j].getShip().colorShip()
                        : formatterVoid);
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Prints the legend on a new line.
     *
     * @param legend The legend to be printed.
     */
    private static void printLegend(final String legend) {
        System.out.println();
        System.out.print(legend);
        System.out.println();
    }

    /**
     * Prints a cell followed by the formatter space and a vertical separator.
     *
     * @param cell The cell value to be printed.
     */
    private static void printCell(final String cell) {
        System.out.print(cell);
        System.out.print(formatterSpace);
        System.out.print("|");
    }

    /**
     * Prints the index column value for the given row index.
     *
     * @param i The row index.
     */
    private static void printIndexColumn(final int i) {
        System.out.print(String.format(" %2d |", (i + 1)));
    }

    /**
     * Prints the row separator line.
     *
     * @param size The size of the grid.
     */
    private static void printSeparator(final int size) {
        System.out.print("----+");
        for (int j = 0; j < size; j++) {
            System.out.print(formatterRow);
        }
        System.out.println();
    }

    /**
     * Prints the header row with column labels.
     *
     * @param size The size of the grid.
     */
    private static void printHeader(final int size) {
        System.out.println();
        System.out.print("    |");
        for (int i = 0; i < size; i++) {
            System.out.print(String.format(formatterCol, Column.fromInt(i)));
        }
        System.out.println();
    }

    /**
     * Prints the current grid with the state of each cell.
     * Display HIT cells in red and MISS cells in white.
     * Format the grid display according to its size
     *
     * @param grid The grid to be printed.
     * @param size The size of the grid.
     */
    public void printCurrentGrid(final Cell[][] grid, final int size) {
        GridBoundary.setFormatters(size);
        System.out.println();
        StringBuilder legend = new StringBuilder();
        String color = State.HIT.getColor();
        legend.append("COLPITO = " + color + " " + Ship.stringShip() + State.ANSI_RESET + " ");
        color = State.MISS.getColor();
        legend.append("MANCATO = " + color + " " + Ship.stringShip() + State.ANSI_RESET + " ");
        printLegend(legend.toString());
        printHeader(size);
        for (int i = 0; i < size; i++) {
            printSeparator(size);
            printIndexColumn(i);
            for (int j = 0; j < size; j++) {
                if (grid[i][j].getState() == State.VOID || grid[i][j].getState() == State.SHIP) {
                    printCell(formatterVoid);
                } else if (grid[i][j].getState() == State.HIT) {
                    color = State.HIT.getColor();
                    printCell(color + formatterSpace + Ship.stringShip() + State.ANSI_RESET);
                } else {
                    color = State.MISS.getColor();
                    printCell(color + formatterSpace + Ship.stringShip() + State.ANSI_RESET);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Sets the formatters based on the given grid size.
     *
     * @param size The size of the grid.
     */
    public static void setFormatters(final int size) {
        if (size > 0 && size <= MAX_SIZE_SMALL) {
            formatterCol = "   %s   |";
            formatterRow = "-------+";
            formatterVoid = "    ";
            formatterSpace = "   ";
        } else if (size > MAX_SIZE_SMALL && size <= MAX_SIZE_MEDIUM) {
            formatterCol = "  %s  |";
            formatterRow = "-----+";
            formatterVoid = "   ";
            formatterSpace = "  ";
        } else if (size > MAX_SIZE_MEDIUM && size <= MAX_SIZE_LARGE) {
            formatterCol = " %s |";
            formatterRow = "---+";
            formatterVoid = "  ";
            formatterSpace = " ";
        }
    }

    /**
     * Method for displaying ships not sunk yet.
     */
    public final void showShips(final Map<Ship, Map<Integer, List<Coordinate>>> ships) {
        for (Ship ship : EnumSet.copyOf(ships.keySet())) {
            System.out.print(ship.toString() + " ");
            for (int i = 0; i < ship.getSize(); i++) {
                System.out.print(ship.colorShip());
            }
            System.out.print(" " + ships.get(ship).size());
            System.out.print(" da affondare su " + ship.getnShips() + " totali ");
            System.out.println();
        }
    }
}
