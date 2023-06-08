package it.uniba.app.ship;

import java.util.Random;

/**
 * Enumeration representing the directions of movement.
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

    /**
     * Constructs a Direction with the specified horizontal and vertical values.
     *
     * @param valVertical   the vertical value
     * @param valHorizontal the horizontal value
     */
    Direction(final int valVertical, final int valOrizontal) {
        this.vertical = valVertical;
        this.orizontal = valOrizontal;
    }

    /**
     * Returns a random direction.
     *
     * @return a random direction
     */
    public static Direction randomDirection() {
        return VALUES[RAND.nextInt(SIZE)];
    }

    /**
     * Rotates the direction 90 degrees counterclockwise.
     *
     * @return the rotated direction
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

    /**
     * Returns the horizontal value of the direction.
     *
     * @return the horizontal value
     */
    public int getOrizontal() {
        return orizontal;
    }

    /**
     * Returns the vertical value of the direction.
     *
     * @return the vertical value
     */
    public int getVertical() {
        return vertical;
    }
}
