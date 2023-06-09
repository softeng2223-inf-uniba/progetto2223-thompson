package it.uniba.app.grid;

import it.uniba.app.grid.type.Column;
import it.uniba.app.grid.type.State;
import it.uniba.app.ship.Ship;

public class GridPrinter {

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
     * Method to display the grid with the ships.
     * Format the grid display according to its size
     * 
     * @param grid The grid to be printed.
     */
    public static void printGrid(Grid grid) {
        int size = grid.getSize();
        GridPrinter.setFormatters(size);
        for (Ship ship : Ship.values()) {
            System.out.print(ship.toString() + " = " + ship.colorShip() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.print("    |");
        for (int i = 0; i < size; i++) {
            System.out.print(String.format(formatterCol, Column.fromInt(i)));
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print("----+");
            for (int j = 0; j < size; j++) {
                System.out.print(formatterRow);
            }
            System.out.println();
            System.out.print(String.format(" %2d |", (i + 1)));
            for (int j = 0; j < size; j++) {
                System.out.print(
                        grid.getGrid()[i][j].getShip() != null
                                ? formatterSpace + grid.getGrid()[i][j].getShip().colorShip()
                                : formatterVoid);
                System.out.print(formatterSpace);
                System.out.print("|");
            }
            System.out.println();
        }
    }

    /*
     * Prints the current grid with the state of each cell.
     * Display HIT cells in red and MISS cells in white.
     * Format the grid display according to its size
     * 
     * @param grid The grid to be printed.
     */
    public static final void printCurrentGrid(Grid grid) {
        int size = grid.getSize();
        GridPrinter.setFormatters(size);
        System.out.println();
        System.out.print("  |");
        for (int i = 0; i < size; i++) {
            System.out.print(String.format(formatterCol, Column.fromInt(i)));
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print("--+");
            for (int j = 0; j < size; j++) {
                System.out.print(formatterRow);
            }
            System.out.println();
            System.out.print(String.format("%2d|", (i + 1)));
            for (int j = 0; j < size; j++) {
                if (grid.getGrid()[i][j].getState() == State.VOID || grid.getGrid()[i][j].getState() == State.SHIP) {
                    System.out.print(formatterVoid);
                } else if (grid.getGrid()[i][j].getState() == State.HIT) {
                    String color = State.HIT.getColor();
                    System.out.print(color + formatterSpace + Ship.stringShip() + State.ANSI_RESET);
                } else {
                    String color = State.MISS.getColor();
                    System.out.print(color + formatterSpace + Ship.stringShip() + State.ANSI_RESET);
                }
                System.out.print(formatterSpace + "|");
            }
            System.out.println();
        }
    }

    /**
     * Sets the formatters based on the given grid size.
     *
     * @param size The size of the grid.
     */
    public static void setFormatters(int size) {
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
}
