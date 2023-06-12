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

}