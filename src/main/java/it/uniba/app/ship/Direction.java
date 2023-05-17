package it.uniba.app.ship;

import java.util.Random;

/**
 * Enum class to manage directions.
 */
public enum Direction {
    RIGHT,
    LEFT,
    UP,
    DOWN;

    /**
     * This array contains direction values, is used as a buffer so as not to recreate it
     * each time.
     */
    private static final Direction[] VALUES = Direction.values();
    /**
     * Total number of direction.
     */
    private static final int SIZE = VALUES.length;
    /**
     * Used for generate random elements.
     */
    private static final Random RAND = new Random();

    /**
     * Method to create random direction.
     * @return direction
     */
    public static Direction randomDirection() {
        return VALUES[RAND.nextInt(SIZE)];
    }

    /**
     * Method to rotate the direction.
     * @return opposite direction
     */
    public Direction rotate() {
        return switch (this) {
            case LEFT ->
                Direction.RIGHT;
            case RIGHT ->
                Direction.LEFT;
            case UP ->
                Direction.DOWN;
            case DOWN ->
                Direction.UP;
            default ->
                null;
        };
    }
}
