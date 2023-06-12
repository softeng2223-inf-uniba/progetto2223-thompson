package it.uniba.app.grid.type;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the State class.
 */
class StateTest {
    private static final String VOID = "\u001B[0m";
    private static final String SHIP = "\u001B[0m";
    private static final String MISS = "\u001B[0m";
    private static final String HIT = "\u001B[31m";

    /**
     * Tests the getColor method of the VOID state to ensure it returns the correct
     * color.
     */
    @Test
    void testGetColorVoidState() {
        assertEquals(VOID, State.VOID.getColor(), "The color must be \u001B[0m");
    }

    /**
     * Tests the getColor method of the SHIP state to ensure it returns the correct
     * color.
     */
    @Test
    void testGetColorShipState() {
        assertEquals(SHIP, State.SHIP.getColor(), "The color must be \u001B[0m");
    }

    /**
     * Tests the getColor method of the MISS state to ensure it returns the correct
     * color.
     */
    @Test
    void testGetColorMissState() {
        assertEquals(MISS, State.MISS.getColor(), "The color must be \u001B[0m");
    }

    /**
     * Tests the getColor method of the HIT state to ensure it returns the correct
     * color.
     */
    @Test
    void testGetColorHitState() {
        assertEquals(HIT, State.HIT.getColor(), "The color must be \u001B[31m");
    }

    /**
     * Tests the hit method of the SHIP state to ensure it transitions to the
     * correct state.
     */
    @Test
    void testHitShipState() {
        State state = State.SHIP;
        assertEquals(State.HIT, state.hit(), "The state must be HIT");
    }

    /**
     * Tests the hit method of the VOID state to ensure it transitions to the
     * correct state.
     */
    @Test
    void testHitVoidState() {
        State state = State.VOID;
        assertEquals(State.MISS, state.hit(), "The state must be MISS");
    }

    /**
     * Tests the hit method of the MISS state to ensure it transitions to the
     * correct state.
     */
    @Test
    void testHitMissState() {
        State state = State.MISS;
        assertEquals(State.MISS, state.hit(), "The state must be MISS");
    }

    /**
     * Tests the hit method of the HIT state to ensure it transitions to the correct
     * state.
     */
    @Test
    void testHitHitState() {
        State state = State.HIT;
        assertEquals(State.HIT, state.hit(), "The state must be HIT");
    }
}
