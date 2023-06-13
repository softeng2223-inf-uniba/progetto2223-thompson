package it.uniba.app.grid;

import it.uniba.app.grid.type.Cell;
import it.uniba.app.grid.type.Coordinate;
import it.uniba.app.grid.type.ResultRemove;
import it.uniba.app.grid.type.State;
import it.uniba.app.ship.Ship;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * <Entity>
 *
 * The Grid class is responsible for representing the grid concept,
 * and mainly offers get and set methods to access and modify its internal
 * attributes.
 */
public class Grid {
    /**
     * Constant size of the grid.
     */
    private int size;
    /**
     * It represents the game grid.
     */
    private final Cell[][] grid;

    /**
     * Dictionary that contains the ships with their coordinates.
     */
    private final Map<Ship, Map<Integer, List<Coordinate>>> ships;

    /**
     * Grid constructor.
     */
    public Grid(final int valSize) {
        this.size = valSize;
        this.grid = new Cell[size][size];
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                this.grid[row][column] = new Cell();
            }
        }
        this.ships = new HashMap<>();
        for (Ship s : Ship.values()) {
            this.ships.put(s, new HashMap<>());
        }
    }

    /**
     * Returns a copy of the ships map.
     *
     * @return a copy of the ships map, where each ship is mapped to a map of ship
     *         instances
     *         and their corresponding coordinates
     */
    public final Map<Ship, Map<Integer, List<Coordinate>>> getShips() {
        Map<Ship, Map<Integer, List<Coordinate>>> copiedMap = new HashMap<>();
        for (Map.Entry<Ship, Map<Integer, List<Coordinate>>> entry : ships.entrySet()) {
            copiedMap.put(entry.getKey(), entry.getValue());
        }
        return copiedMap;
    }

    /**
     * Check if all the ships have been sunken.
     *
     * @return true if all the ships have been sunken, false otherwise
     */
    public boolean isAllSunken() {
        boolean check = true;
        for (Ship s : Ship.values()) {
            if (!ships.get(s).isEmpty()) {
                check = false;
            }
        }
        return check;
    }

    /**
     * Gets the size of the grid.
     *
     * @return The size of the grid.
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets the ship in the cell at the specified coordinate.
     *
     * @param coord The coordinate of the cell where the ship will be set.
     * @param ship  The ship object to be set in the cell.
     */
    public final void setCell(final Coordinate coord, final Ship ship) throws IndexOutOfBoundsException {
        if (coord == null || ship == null) {
            throw new IllegalArgumentException("Coordinate and ship cannot be null");
        }
        int row = coord.getRow();
        int column = coord.getColumn().getColumnInt();
        grid[row][column].setShip(ship);
    }

    /**
     * Checks if the cell at the specified coordinate is empty.
     *
     * @param coord the coordinate of the cell to check
     * @return true if the cell is empty, false otherwise
     */
    public final boolean isCellEmpty(final Coordinate coord) {
        return this.grid[coord.getRow()][coord.getColumn().getColumnInt()].isEmpty();
    }

    /**
     * Sets the specified ship at the given coordinate.
     *
     * @param ship  the ship to set
     * @param nShip the index of the ship in the collection
     * @param coord the coordinate where the ship will be placed
     */
    public final void setShip(final Ship ship, final int nShip, final Coordinate coord) {
        if (ship == null || coord == null) {
            throw new IllegalArgumentException("Coordinate and ship cannot be null");
        }
        int newNShip = nShip;
        if (this.ships.get(ship).get(newNShip) == null) {
            if (nShip <= 0) {
                newNShip = 1;
            }
            this.ships.get(ship).put(newNShip, new LinkedList<>());

            this.ships.get(ship).get(newNShip).add(coord.copy());
        } else {
            if (nShip <= 0) {
                newNShip = Collections.max(ships.get(ship).entrySet(), Map.Entry.comparingByKey()).getKey() + 1;
            }
            this.ships.get(ship).get(newNShip).add(coord.copy());
        }
    }

    /**
     * Removes a ship at the specified coordinate and returns the result of the
     * removal operation.
     *
     * @param coord the coordinate of the ship to remove
     * @return the result of the removal operation
     */
    public final ResultRemove removeShip(final Coordinate coord) {
        String message = "acqua";
        boolean hit = false;
        Ship shipToRemove = null;
        int nShipToRemove = -1;
        for (Ship s : Ship.values()) {
            for (var entry : ships.get(s).entrySet()) {
                if (entry.getValue().contains(coord)) {
                    entry.getValue().remove(coord);
                    message = "colpito";
                    hit = true;
                }
                if (entry.getValue().isEmpty()) {
                    message = "colpito e affondato";
                    nShipToRemove = entry.getKey();
                    shipToRemove = s;
                    hit = true;
                }
            }
        }
        if (shipToRemove != null) {
            this.ships.get(shipToRemove).remove(nShipToRemove);
        }
        return new ResultRemove(hit, message);
    }

    /**
     * Returns the state of the cell at the specified coordinate.
     *
     * @param coord the coordinate of the cell
     * @return the state of the cell
     */
    public final State getState(final Coordinate coord) {
        return this.grid[coord.getRow()][coord.getColumn().getColumnInt()].getState();
    }

    /**
     * Sets the state of the cell at the specified coordinate.
     *
     * @param coord the coordinate of the cell
     * @param state the state to set for the cell
     */
    public final void setState(final Coordinate coord, final State state) throws IndexOutOfBoundsException {
        if (state == null || coord == null) {
            throw new IllegalArgumentException("Coordinate and state cannot be null");
        }
        this.grid[coord.getRow()][coord.getColumn().getColumnInt()].setState(state);
    }

    /**
     * Checks if a ship is placed at the specified coordinate.
     *
     * @param coord the coordinate to check
     * @return true if a ship is placed at the coordinate, false
     *         otherwise
     */
    public final boolean isShipPlaced(final Coordinate coord) throws IndexOutOfBoundsException {
        if (grid[coord.getRow()][coord.getColumn().getColumnInt()].getShip() != null) {
            return true;
        }
        return false;
    }

    /**
     * Returns the color of the ship placed at the specified coordinate.
     * If no ship is placed at the coordinate, it returns the default ship color.
     *
     * @param coord the coordinate to check
     * @return the color of the ship or the default ship color
     */
    public final String getShipColor(final Coordinate coord) {
        if (this.isShipPlaced(coord)) {
            return grid[coord.getRow()][coord.getColumn().getColumnInt()].getShip().colorShip();
        }
        return Ship.stringShip();
    }
}
