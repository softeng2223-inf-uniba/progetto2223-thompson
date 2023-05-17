package it.uniba.app.grid;

import it.uniba.app.grid.type.Cell;
import it.uniba.app.grid.type.Coordinate;
import it.uniba.app.grid.type.Row;
import it.uniba.app.ship.Ship;
import it.uniba.app.ship.Direction;
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
            for (int i = 0; i < s.getnShips(); i++) {
                this.ships.get(s).put(i, new LinkedList<>());
            }
        }
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

    private void addShips(final Ship ship, final int nShip, final Direction direction, final Coordinate coord) {
        if (direction != null) {
            switch (direction) {
                case LEFT -> {
                    for (int i = 0; i < ship.getSize(); i++) {
                        int row = coord.getRow().ordinal();
                        int column = coord.getColumn() - i + 1;
                        this.ships.get(ship).get(nShip).add(new Coordinate(Row.fromInt(row), column));
                    }
                }
                case RIGHT -> {
                    for (int i = 0; i < ship.getSize(); i++) {
                        int row = coord.getRow().ordinal();
                        int column = coord.getColumn() + i + 1;
                        this.ships.get(ship).get(nShip).add(new Coordinate(Row.fromInt(row), column));
                    }
                }
                case UP -> {
                    for (int i = 0; i < ship.getSize(); i++) {
                        int row = coord.getRow().ordinal() - i;
                        int column = coord.getColumn() + 1;
                        this.ships.get(ship).get(nShip).add(new Coordinate(Row.fromInt(row), column));
                    }
                }
                case DOWN -> {
                    for (int i = 0; i < ship.getSize(); i++) {
                        int row = coord.getRow().ordinal() + i;
                        int column = coord.getColumn() + 1;
                        this.ships.get(ship).get(nShip).add(new Coordinate(Row.fromInt(row), column));
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
                    if (coord.getColumn() - dimension < 0) {
                        return false;
                    }
                    for (int i = 0; i < dimension; i++) {
                        if (!this.grid[coord.getRow().ordinal()][coord.getColumn() - i].isEmpty()) {
                            return false;
                        }
                    }
                    return true;
                }
                case RIGHT -> {
                    if (coord.getColumn() + dimension >= MAXCOLUMN) {
                        return false;
                    }
                    for (int i = 0; i < dimension; i++) {
                        if (!this.grid[coord.getRow().ordinal()][coord.getColumn() + i].isEmpty()) {
                            return false;
                        }
                    }
                    return true;
                }
                case UP -> {
                    if (coord.getRow().ordinal() - dimension < 0) {
                        return false;
                    }
                    for (int i = 0; i < dimension; i++) {
                        if (!this.grid[coord.getRow().ordinal() - i][coord.getColumn()].isEmpty()) {
                            return false;
                        }
                    }
                    return true;
                }
                case DOWN -> {
                    if (coord.getRow().ordinal() + dimension >= MAXROW) {
                        return false;
                    }
                    for (int i = 0; i < dimension; i++) {
                        if (!this.grid[coord.getRow().ordinal() + i][coord.getColumn()].isEmpty()) {
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
                        this.grid[coord.getRow().ordinal()][coord.getColumn() - i].setShip(ship);
                    }
                }
                case RIGHT -> {
                    for (int i = 0; i < ship.getSize(); i++) {
                        this.grid[coord.getRow().ordinal()][coord.getColumn() + i].setShip(ship);
                    }
                }
                case UP -> {
                    for (int i = 0; i < ship.getSize(); i++) {
                        this.grid[coord.getRow().ordinal() - i][coord.getColumn()].setShip(ship);
                    }
                }
                case DOWN -> {
                    for (int i = 0; i < ship.getSize(); i++) {
                        this.grid[coord.getRow().ordinal() + i][coord.getColumn()].setShip(ship);
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
                    this.addShips(s, i, direction, coord);
                    this.totalShips += 1;
                } else {
                    direction = direction.rotate();
                    if (this.canPlaceShip(direction, s.getSize(), coord)) {
                        this.placeShip(direction, s, coord);
                        this.addShips(s, i, direction, coord);
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
}
