package it.uniba.app.grid;

import it.uniba.app.grid.type.Cell;
import it.uniba.app.grid.type.Coordinate;
import it.uniba.app.grid.type.SizeGrid;
import it.uniba.app.grid.type.State;
import it.uniba.app.grid.type.Column;
import it.uniba.app.ship.Ship;
import it.uniba.app.type.Difficulty;
import it.uniba.app.ship.Direction;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Class to generate and handle the grid.
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
        this.size = SizeGrid.getSize();
        this.grid = new Cell[size][size];
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
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
     * Shoots at the specified coordinate on the grid and returns the result of the
     * shot.
     *
     * @param coord the coordinate to shoot at
     * @return the result of the shot, which can be "acqua" (water), "colpito"
     *         (hit), "colpito e affondato" (hit and sunk),
     *         or "Questa mossa è stata già effettuata" (This move has already been
     *         made)
     */
    public String hitCoordinate(final Coordinate coord) {
        String result = "acqua";
        boolean hit = false;
        Ship shipToRemove = null;
        int nShipToRemove = -1;
        int row = coord.getRow() - 1;
        int column = coord.getColumn().ordinal();
        if (grid[row][column].getState() == State.HIT || grid[row][column].getState() == State.MISS) {
            return "Questa mossa è stata già effettuata";
        }
        grid[row][column].setState(grid[row][column].getState().hit());
        for (Ship s : Ship.values()) {
            for (var entry : ships.get(s).entrySet()) {
                if (entry.getValue().contains(coord)) {
                    entry.getValue().remove(coord);
                    result = "colpito";
                    hit = true;
                }
                if (entry.getValue().isEmpty()) {
                    result = "colpito e affondato";
                    nShipToRemove = entry.getKey();
                    shipToRemove = s;
                    hit = true;
                }
            }
        }
        if (shipToRemove != null) {
            ships.get(shipToRemove).remove(nShipToRemove);
        }
        if (!hit) {
            int tries = Difficulty.getCurrentTries() - 1;
            Difficulty.setCurrentTries(tries);
        }
        return result;
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
            int row = coord.getRow() + 1;
            int column = coord.getColumn().ordinal();
            for (int i = 0; i < ship.getSize(); i++) {
                if (this.ships.get(ship).get(nShip) == null) {
                    this.ships.get(ship).put(nShip, new LinkedList<>());
                    this.ships.get(ship).get(nShip).add(new Coordinate(Column.fromInt(column), row));
                } else {
                    this.ships.get(ship).get(nShip).add(new Coordinate(Column.fromInt(column), row));
                }
                row += direction.getOrizontal();
                column += direction.getVertical();
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
    private boolean canPlaceShip(final Direction direction, final Ship dimension, final Coordinate coord) {
        if (direction != null) {
            int row = coord.getRow();
            int column = coord.getColumn().ordinal();
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
                if (!this.grid[row][column].isEmpty()) {
                    return false;
                }
                row += direction.getOrizontal();
                column += direction.getVertical();
            }
            return true;
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
            int row = coord.getRow();
            int column = coord.getColumn().ordinal();
            for (int i = 0; i < ship.getSize(); i++) {
                this.grid[row][column].setShip(ship);
                row += direction.getOrizontal();
                column += direction.getVertical();
            }
        }
    }

    /**
     * Method to create a grid whit random ships.
     */
    public final void generateGrid() {
        Coordinate coord = Coordinate.random(size, size);
        Direction direction;

        for (Ship s : Ship.values()) {
            for (int i = 0; i < s.getnShips(); i++) {
                direction = Direction.randomDirection();
                if (this.canPlaceShip(direction, s, coord)) {
                    this.placeShip(direction, s, coord);
                    this.addShips(s, i + 1, direction, coord);
                    this.totalShips += 1;
                } else {
                    direction = direction.rotate();
                    if (this.canPlaceShip(direction, s, coord)) {
                        this.placeShip(direction, s, coord);
                        this.addShips(s, i + 1, direction, coord);
                        this.totalShips += 1;
                    } else {
                        i--;
                    }
                }
                coord.setRow(Coordinate.generateRandomRow(size));
                coord.setColumn(Coordinate.generateRandomColumn(size));
            }
        }
        System.out.println("fine generazione");
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
        for (int i = 0; i < size; i++) {
            System.out.print(String.format("   %s   |", Column.fromInt(i)));
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print("----+");
            for (int j = 0; j < size; j++) {
                System.out.print("-------+");
            }
            System.out.println();
            System.out.print(String.format(" %2d |", (i + 1)));
            for (int j = 0; j < size; j++) {
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
        for (int i = 0; i < size; i++) {
            System.out.print(String.format("   %s   |", Column.fromInt(i)));
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print("----+");
            for (int j = 0; j < size; j++) {
                System.out.print("-------+");
            }
            System.out.println();
            System.out.print(String.format(" %2d |", (i + 1)));
            for (int j = 0; j < size; j++) {
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
     * Method for displaying ships not sunk yet.
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
