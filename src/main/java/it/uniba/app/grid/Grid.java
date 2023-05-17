package it.uniba.app.grid;

import it.uniba.app.grid.type.Cell;
import it.uniba.app.grid.type.Coordinate;
import it.uniba.app.grid.type.Row;
import it.uniba.app.ship.Ship;
import it.uniba.app.ship.Direction;
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

    /**
     * Method to check if we can place a ship in a given coordinate.
     * @param direction direction of ship
     * @param dimension dimension of ship
     * @param coord coordinate where to place the ship
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
                        if (!grid[coord.getRow().ordinal()][coord.getColumn() - i].isEmpty()) {
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
                        if (!grid[coord.getRow().ordinal()][coord.getColumn() + i].isEmpty()) {
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
                        if (!grid[coord.getRow().ordinal() - i][coord.getColumn()].isEmpty()) {
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
                        if (!grid[coord.getRow().ordinal() + i][coord.getColumn()].isEmpty()) {
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
     * @param direction direction of the ship
     * @param ship ship to place
     * @param coord initial coordinate
     */
    private void placeShip(final Direction direction, final Ship ship, final Coordinate coord) {
        if (direction != null) {
            switch (direction) {
                case LEFT -> {
                    for (int i = 0; i < ship.getSize(); i++) {
                        grid[coord.getRow().ordinal()][coord.getColumn() - i].setShip(ship);
                    }
                }
                case RIGHT -> {
                    for (int i = 0; i < ship.getSize(); i++) {
                        grid[coord.getRow().ordinal()][coord.getColumn() + i].setShip(ship);
                    }
                }
                case UP -> {
                    for (int i = 0; i < ship.getSize(); i++) {
                        grid[coord.getRow().ordinal() - i][coord.getColumn()].setShip(ship);
                    }
                }
                case DOWN -> {
                    for (int i = 0; i < ship.getSize(); i++) {
                        grid[coord.getRow().ordinal() + i][coord.getColumn()].setShip(ship);
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
                coord.setRow(generateRandomRow());
                coord.setColumn(generateRandomColumn());
                direction = Direction.randomDirection();
                if (canPlaceShip(direction, s.getSize(), coord)) {
                    placeShip(direction, s, coord);
                    totalShips += 1;
                } else {
                    direction = direction.rotate();
                    if (canPlaceShip(direction, s.getSize(), coord)) {
                        placeShip(direction, s, coord);
                        totalShips += 1;
                    } else {
                        i--;
                    }
                }
            }
        }
    }

    /**
     * TotalShips getter.
     * @return totalships
     */
    public final int getTotalShips() {
        return totalShips;
    }
}
