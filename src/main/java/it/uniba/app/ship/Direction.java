package it.uniba.app.ship;

import java.util.Random;

/**
 * Enum class to manage directions.
 */
public enum Direction {
    RIGHT(0, 1),
    LEFT(0, -1),
    UP(-1, 0),
    DOWN(1, 0);

    private int orizontal;

    private int vertical;
    /**
     * This array contains direction values, is used as a buffer so as not to
     * recreate it
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

    Direction(final int valVertical, final int valOrizontal) {
        this.vertical = valVertical;
        this.orizontal = valOrizontal;
    }

    /**
     * Method to create random direction.
     *
     * @return direction
     */
    public static Direction randomDirection() {
        return VALUES[RAND.nextInt(SIZE)];
    }

    /**
     * Method to rotate the direction.
     *
     * @return opposite direction
     */
    public Direction rotate() {
        int valOrizontal = this.orizontal * -1;
        int valVertical = this.vertical * -1;
        for (Direction d : VALUES) {
            if (valOrizontal == d.orizontal && valVertical == d.vertical) {
                return d;
            }
        }
        this.orizontal *= -1;
        this.vertical *= -1;
        return this;
    }

    public int getOrizontal() {
        return orizontal;
    }

    public int getVertical() {
        return vertical;
    }
}
