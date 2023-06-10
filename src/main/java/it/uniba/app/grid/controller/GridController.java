package it.uniba.app.grid.controller;

import java.util.List;
import java.util.Map;

import it.uniba.app.grid.GridBoundary;
import it.uniba.app.grid.type.Coordinate;
import it.uniba.app.ship.Direction;
import it.uniba.app.ship.Ship;

public final class GridController extends GridBoundary {
    public static final GridController INSTANCE = new GridController();

    private GridController() {
    }

    public boolean isAllSunken(Map<Ship, Map<Integer, List<Coordinate>>> ships) {
        boolean check = true;
        for (Ship s : Ship.values()) {
            if (!ships.get(s).isEmpty()) {
                check = false;
            }
        }
        return check;
    }

    @Override
    public String hitCoordinate(Coordinate coord) {
        
    }

    @Override
    public void addShips(Ship ship, int nShip, Direction direction, Coordinate coord) {
        
    }

    @Override
    public boolean canPlaceShip(Direction direction, Ship dimension, Coordinate coord) {
       
    }

    @Override
    public void placeShip(Direction direction, Ship ship, Coordinate coord) {
        
    }

    @Override
    public void generateGrid() {
        
    }

}