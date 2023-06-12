package it.uniba.app.ship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Direction enum.
 */
public class DirectionTest {
    /**
     * Tests the randomDirection method to ensure the direction is not null.
     */
    @Test
    void testRandomDirection() {
        Direction direction = Direction.randomDirection();
        assertNotNull(direction, "The direction must be not null");
    }

    /**
     * Tests the rotate method for the RIGHT direction.
     */
    @Test
    void testRotateRightDirection() {
        Direction direction = Direction.RIGHT;
        assertEquals(Direction.LEFT, direction.rotate(), "The direction must be LEFT");
    }

    /**
     * Tests the rotate method for the LEFT direction.
     */
    @Test
    void testRotateLeftDirection() {
        Direction direction = Direction.LEFT;
        assertEquals(Direction.RIGHT, direction.rotate(), "The direction must be RIGHT");
    }

    /**
     * Tests the rotate method for the UP direction.
     */
    @Test
    void testRotateUpDirection() {
        Direction direction = Direction.UP;
        assertEquals(Direction.DOWN, direction.rotate(), "The direction must be DOWN");
    }

    /**
     * Tests the rotate method for the DOWN direction.
     */
    @Test
    void testRotateDownDirection() {
        Direction direction = Direction.DOWN;
        assertEquals(Direction.UP, direction.rotate(), "The direction must be UP");
    }

     /**
     * Tests the getOrizontal method for the RIGHT direction.
     */
    @Test
    void testGetOrizontal() {
        Direction direction = Direction.RIGHT;
        assertEquals(1, direction.getOrizontal(), "The orizontal must be 1");
    }

     /**
     * Tests the getVertical method for the RIGHT direction.
     */
    @Test
    void testGetVertical() {
        Direction direction = Direction.RIGHT;
        assertEquals(0, direction.getVertical(), "The vertical must be 0");
    }

}
