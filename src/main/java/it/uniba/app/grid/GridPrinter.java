package it.uniba.app.grid;

import it.uniba.app.grid.type.Column;
import it.uniba.app.grid.type.State;
import it.uniba.app.ship.Ship;

public class GridPrinter {
    /*
     * Prints the current grid with the state of each cell.
     * Display HIT cells in red and MISS cells in white.
     * Format the grid display according to its size
     */
    public static final void printCurrentGrid(Grid grid) {
        int size=grid.getSize();
        String formatterCol = "";
        String formatterRow = "";
        String formatterNull = "";
        String formatterSpace = "";
        switch (size) {
            case 10:
                formatterCol = "   %s   |";
                formatterRow = "-------+";
                formatterNull = "    ";
                formatterSpace = "   ";
                break;
            case 18:
                formatterCol = "  %s  |";
                formatterRow = "-----+";
                formatterNull = "   ";
                formatterSpace = "  ";
                break;
            case 26:
                formatterCol = " %s |";
                formatterRow = "---+";
                formatterNull = "  ";
                formatterSpace = " ";
                break;
        }

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
                    System.out.print(formatterNull);
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
}
