package it.uniba.app.grid;

import it.uniba.app.grid.type.Cell;
import it.uniba.app.grid.type.Coordinate;
import it.uniba.app.grid.type.State;
import it.uniba.app.grid.type.Column;
import it.uniba.app.ship.Ship;
import it.uniba.app.ship.Direction;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
     * Dictionary that contains the ships with their coordinates.
     */
    private final Map<Ship, Map<Integer, List<Coordinate>>> ships;

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
        this.ships = new HashMap<>();
        for (Ship s : Ship.values()) {
            this.ships.put(s, new HashMap<>());
        }
    }

    /**
     * Method to generate a random column.
     */
    private Column generateRandomColumn() {
        return Column.fromInt(RAND.nextInt(MAXCOLUMN));
    }

    /**
     * Method to generate a random row.
     */
    private int generateRandomRow() {
        return RAND.nextInt(MAXROW);
    }

    /**
     * Method that adds ships to the dictionary with their coordinates.
     *
     * @param ship
     * @param nShip
     * @param direction
     * @param coord
     */
    private void addShips(final Ship ship, final int nShip, final Direction direction, final Coordinate coord) {
        if (direction != null) {
            switch (direction) {
                case LEFT -> {
                    for (int i = 0; i < ship.getSize(); i++) {
                        int row = coord.getRow() + 1;
                        int column = coord.getColumn().ordinal() - i;
                        if (this.ships.get(ship).get(nShip) == null) {
                            this.ships.get(ship).put(nShip, new LinkedList<>());
                            this.ships.get(ship).get(nShip).add(new Coordinate(Column.fromInt(column), row));
                        } else {
                            this.ships.get(ship).get(nShip).add(new Coordinate(Column.fromInt(column), row));
                        }
                    }
                }
                case RIGHT -> {
                    for (int i = 0; i < ship.getSize(); i++) {
                        int row = coord.getRow() + 1;
                        int column = coord.getColumn().ordinal() + i;
                        if (this.ships.get(ship).get(nShip) == null) {
                            this.ships.get(ship).put(nShip, new LinkedList<>());
                            this.ships.get(ship).get(nShip).add(new Coordinate(Column.fromInt(column), row));
                        } else {
                            this.ships.get(ship).get(nShip).add(new Coordinate(Column.fromInt(column), row));
                        }
                    }
                }
                case UP -> {
                    for (int i = 0; i < ship.getSize(); i++) {
                        int row = coord.getRow() - i + 1;
                        int column = coord.getColumn().ordinal();
                        if (this.ships.get(ship).get(nShip) == null) {
                            this.ships.get(ship).put(nShip, new LinkedList<>());
                            this.ships.get(ship).get(nShip).add(new Coordinate(Column.fromInt(column), row));
                        } else {
                            this.ships.get(ship).get(nShip).add(new Coordinate(Column.fromInt(column), row));
                        }
                    }
                }
                case DOWN -> {
                    for (int i = 0; i < ship.getSize(); i++) {
                        int row = coord.getRow() + i + 1;
                        int column = coord.getColumn().ordinal();
                        if (this.ships.get(ship).get(nShip) == null) {
                            this.ships.get(ship).put(nShip, new LinkedList<>());
                            this.ships.get(ship).get(nShip).add(new Coordinate(Column.fromInt(column), row));
                        } else {
                            this.ships.get(ship).get(nShip).add(new Coordinate(Column.fromInt(column), row));
                        }
                    }
                }
                default -> {
                }
            }
        }
    }

    /**
     * Method to check if we can place a ship in a given coordinate.
     *
     * @param direction direction of ship
     * @param dimension dimension of ship
     * @param coord     coordinate where to place the ship
     * @return true if can be placed else false
     */
    private boolean canPlaceShip(final Direction direction, final int dimension, final Coordinate coord) {
        if (direction != null) {
            switch (direction) {
                case LEFT -> {
                    if (coord.getColumn().ordinal() - dimension < 0) {
                        return false;
                    }
                    for (int i = 0; i < dimension; i++) {
                        if (!this.grid[coord.getRow()][coord.getColumn().ordinal() - i].isEmpty()) {
                            return false;
                        }
                    }
                    return true;
                }
                case RIGHT -> {
                    if (coord.getColumn().ordinal() + dimension >= MAXCOLUMN) {
                        return false;
                    }
                    for (int i = 0; i < dimension; i++) {
                        if (!this.grid[coord.getRow()][coord.getColumn().ordinal() + i].isEmpty()) {
                            return false;
                        }
                    }
                    return true;
                }
                case UP -> {
                    if (coord.getRow() - dimension < 0) {
                        return false;
                    }
                    for (int i = 0; i < dimension; i++) {
                        if (!this.grid[coord.getRow() - i][coord.getColumn().ordinal()].isEmpty()) {
                            return false;
                        }
                    }
                    return true;
                }
                case DOWN -> {
                    if (coord.getRow() + dimension >= MAXROW) {
                        return false;
                    }
                    for (int i = 0; i < dimension; i++) {
                        if (!this.grid[coord.getRow() + i][coord.getColumn().ordinal()].isEmpty()) {
                            return false;
                        }
                    }
                    return true;
                }
                default -> {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    /**
     * Method to place ships in the grid.
     *
     * @param direction direction of the ship
     * @param ship      ship to place
     * @param coord     initial coordinate
     */
    private void placeShip(final Direction direction, final Ship ship, final Coordinate coord) {
        if (direction != null) {
            switch (direction) {
                case LEFT -> {
                    for (int i = 0; i < ship.getSize(); i++) {
                        this.grid[coord.getRow()][coord.getColumn().ordinal() - i].setShip(ship);
                    }
                }
                case RIGHT -> {
                    for (int i = 0; i < ship.getSize(); i++) {
                        this.grid[coord.getRow()][coord.getColumn().ordinal() + i].setShip(ship);
                    }
                }
                case UP -> {
                    for (int i = 0; i < ship.getSize(); i++) {
                        this.grid[coord.getRow() - i][coord.getColumn().ordinal()].setShip(ship);
                    }
                }
                case DOWN -> {
                    for (int i = 0; i < ship.getSize(); i++) {
                        this.grid[coord.getRow() + i][coord.getColumn().ordinal()].setShip(ship);
                    }
                }
                default -> {
                }
            }
        }
    }

    /**
     * Method to create a grid whit random ships.
     */
    public final void generateGrid() {
        Coordinate coord = new Coordinate();
        Direction direction;

        for (Ship s : Ship.values()) {
            for (int i = 0; i < s.getnShips(); i++) {
                coord.setRow(this.generateRandomRow());
                coord.setColumn(this.generateRandomColumn());
                direction = Direction.randomDirection();
                if (this.canPlaceShip(direction, s.getSize(), coord)) {
                    this.placeShip(direction, s, coord);
                    this.addShips(s, i + 1, direction, coord);
                    this.totalShips += 1;
                } else {
                    direction = direction.rotate();
                    if (this.canPlaceShip(direction, s.getSize(), coord)) {
                        this.placeShip(direction, s, coord);
                        this.addShips(s, i + 1, direction, coord);
                        this.totalShips += 1;
                    } else {
                        i--;
                    }
                }
            }
        }
    }

    /**
     * TotalShips getter.
     *
     * @return totalships
     */
    public final int getTotalShips() {
        return this.totalShips;
    }

    /**
     * Method to display the grid with the ships.
     */
    public final void printGrid() {
        for (Ship ship : Ship.values()) {
            System.out.print(ship.toString() + " = " + ship.colorShip() + " ");
        }
        System.out.println();
        System.out.println();
        System.out.print("    |");
        for (int i = 0; i < MAXROW; i++) {
            System.out.print(String.format("   %s   |", Column.fromInt(i)));
        }
        System.out.println();
        for (int i = 0; i < MAXROW; i++) {
            System.out.print("----+");
            for (int j = 0; j < MAXCOLUMN; j++) {
                System.out.print("-------+");
            }
            System.out.println();
            System.out.print(String.format(" %2d |", (i + 1)));
            for (int j = 0; j < MAXCOLUMN; j++) {
                System.out.print("   ");
                System.out.print(this.grid[i][j].getShip() != null ? this.grid[i][j].getShip().colorShip() : " ");
                System.out.print("   ");
                System.out.print("|");
            }
            System.out.println();
        }
    }

    /**
     * Method to display the current grid with the user's tries.
     */
    public final void printCurrentGrid() {
        System.out.println();
        System.out.print("    |");
        for (int i = 0; i < MAXROW; i++) {
            System.out.print(String.format("   %s   |", Column.fromInt(i)));
        }
        System.out.println();
        for (int i = 0; i < MAXROW; i++) {
            System.out.print("----+");
            for (int j = 0; j < MAXCOLUMN; j++) {
                System.out.print("-------+");
            }
            System.out.println();
            System.out.print(String.format(" %2d |", (i + 1)));
            for (int j = 0; j < MAXCOLUMN; j++) {
                System.out.print("   ");
                if (this.grid[i][j].getState() == State.VOID || this.grid[i][j].getState() == State.SHIP) {
                    System.out.print(" ");

                } else {
                    System.out.print(Ship.stringShip());
                }
                System.out.print("   ");
                System.out.print("|");
            }
            System.out.println();
        }
    }

    /**
     * Method to display remaing ships.
     */
    public final void showShips() {
        for (Ship ship : EnumSet.copyOf(this.ships.keySet())) {
            System.out.print(ship.toString() + " ");
            for (int i = 0; i < ship.getSize(); i++) {
                System.out.print(ship.colorShip());
            }
            System.out.print(" " + this.ships.get(ship).size());
            System.out.print(" da affondare su " + ship.getnShips() + " totali ");
            System.out.println();
        }
    }
}
