package it.uniba.app.grid.controller;

import it.uniba.app.grid.Grid;
import it.uniba.app.grid.GridBoundary;
import it.uniba.app.grid.type.Column;
import it.uniba.app.grid.type.Coordinate;
import it.uniba.app.grid.type.ResultRemove;
import it.uniba.app.grid.type.SizeGrid;
import it.uniba.app.grid.type.State;
import it.uniba.app.ship.Direction;
import it.uniba.app.ship.Ship;
import it.uniba.app.type.Difficulty;

/**
 * <Control>
 *
 * The GridController class is responsible for managing the game grid.
 * It handles the creation of the grid, placement of ships, shooting at
 * coordinates,
 * and various operations related to the grid.
 */
public final class GridController extends GridBoundary {
    private static final GridController CONTROLLER = new GridController();

    /**
     * Grid of the current match.
     */
    private Grid grid;

    public static GridController getInstance() {
        return CONTROLLER;
    }

    private GridController() {
    }

    /**
     * Creates a new grid for the match and generates the ship placements.
     */
    public void newGrid() {
        this.grid = new Grid(SizeGrid.getSize());
        generateGrid();
    }

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
    public String hitCoordinate(final Coordinate coord) throws IndexOutOfBoundsException {
        int row = coord.getRow() - 1;
        int column = coord.getColumn().getColumnInt();
        Coordinate copyCoordinate = new Coordinate(Column.fromInt(column), row);
        if (grid.getState(copyCoordinate) == State.HIT || grid.getState(copyCoordinate) == State.MISS) {
            return "Questa mossa è stata già effettuata";
        }
        grid.setState(copyCoordinate, grid.getState(copyCoordinate).hit());
        ResultRemove result = grid.removeShip(coord);
        if (!result.getResult()) {
            int tries = Difficulty.getFailedTries() - 1;
            Difficulty.setFailedTries(tries);
        } else {
            int tries = Difficulty.getCurrentTries() + 1;
            Difficulty.setCurrentTries(tries);
        }
        return result.getMessage();
    }

    /**
     * Adds ships to the grid with their coordinates.
     *
     * @param ship      the ship to add
     * @param nShip     the index of the ship in the collection
     * @param direction the direction of the ship
     * @param coord     the initial coordinate
     */
    private void addShips(final Ship ship, final int nShip, final Direction direction, final Coordinate coord) {
        if (direction != null) {
            int row = coord.getRow() + 1;
            int column = coord.getColumn().getColumnInt();
            Coordinate copyCoordinate = new Coordinate(Column.fromInt(column), row);
            for (int i = 0; i < ship.getSize(); i++) {
                grid.setShip(ship, nShip, copyCoordinate);
                row += direction.getOrizontal();
                column += direction.getVertical();
                copyCoordinate.setRow(row);
                copyCoordinate.setColumn(Column.fromInt(column));
            }
        }
    }

    /**
     * Checks if a ship can be placed in a given coordinate.
     *
     * @param direction the direction of the ship
     * @param dimension the dimension of the ship
     * @param coord     the coordinate where to place the ship
     * @return true if the ship can be placed, false otherwise
     */
    private boolean canPlaceShip(final Direction direction, final Ship dimension, final Coordinate coord) {
        if (direction != null) {
            int size = this.grid.getSize();
            int row = coord.getRow();
            int column = coord.getColumn().getColumnInt();
            Coordinate copyCoordinate = new Coordinate(Column.fromInt(column), row);
            if (direction.getVertical() != 0) {
                int check = column + (dimension.getSize() * direction.getVertical());
                if (check < 0 || check >= size) {
                    return false;
                }
            }
            if (direction.getOrizontal() != 0) {
                int check = row + (dimension.getSize() * direction.getOrizontal());
                if (check < 0 || check >= size) {
                    return false;
                }
            }
            for (int i = 0; i < dimension.getSize(); i++) {
                if (!this.grid.isCellEmpty(copyCoordinate)) {
                    return false;
                }
                row += direction.getOrizontal();
                column += direction.getVertical();
                copyCoordinate.setRow(row);
                copyCoordinate.setColumn(Column.fromInt(column));
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Places ships in the grid.
     *
     * @param direction the direction of the ship
     * @param ship      the ship to place
     * @param coord     the initial coordinate
     */
    private void placeShip(final Direction direction, final Ship ship, final Coordinate coord) {
        if (direction != null) {
            int row = coord.getRow();
            int column = coord.getColumn().getColumnInt();
            Coordinate copyCoordinate = new Coordinate(Column.fromInt(column), row);
            for (int i = 0; i < ship.getSize(); i++) {
                grid.setCell(copyCoordinate, ship);
                row += direction.getOrizontal();
                column += direction.getVertical();
                copyCoordinate.setRow(row);
                copyCoordinate.setColumn(Column.fromInt(column));
            }
        }
    }

    /**
     * Generates the grid with random ship placements.
     */
    private void generateGrid() {
        int size = this.grid.getSize();
        Direction direction;

        for (Ship s : Ship.values()) {
            for (int i = 0; i < s.getnShips(); i++) {
                Coordinate coord = Coordinate.random(size, size);
                direction = Direction.randomDirection();
                if (this.canPlaceShip(direction, s, coord)) {
                    this.placeShip(direction, s, coord);
                    this.addShips(s, i + 1, direction, coord);
                } else {
                    direction = direction.rotate();
                    if (this.canPlaceShip(direction, s, coord)) {
                        this.placeShip(direction, s, coord);
                        this.addShips(s, i + 1, direction, coord);
                    } else {
                        i--;
                    }
                }
            }
        }
    }

    /**
     * Checks if all ships have been sunk.
     *
     * @return true if all ships have been sunk, false otherwise
     */
    public boolean isAllSunken() {
        return grid.isAllSunken();
    }

    /**
     * Prints the current state of the grid.
     */
    public void printCurrentGrid() {
        boundaryPrintCurrentGrid(grid, grid.getSize());
    }

    /**
     * Prints the grid.
     */
    public void printGrid() {
        boundaryPrintGrid(grid, grid.getSize());
    }

    /**
     * Displays the number of the ships on the grid.
     */
    public void showShips() {
        boundaryShowShips(grid.getShips());
    }
}
