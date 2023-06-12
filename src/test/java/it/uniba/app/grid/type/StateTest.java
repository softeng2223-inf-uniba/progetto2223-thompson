package it.uniba.app.grid.type;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StateTest {
    private static final String VOID = "\u001B[0m";
    private static final String SHIP = "\u001B[0m";
    private static final String MISS = "\u001B[0m";
    private static final String HIT = "\u001B[31m";

    @Test
    void testGetColorVoidState() {
        assertEquals(VOID, State.VOID.getColor(), "The color must be \u001B[0m");
    }

    @Test
    void testGetColorShipState() {
        assertEquals(SHIP, State.SHIP.getColor(), "The color must be \u001B[0m");
    }

    @Test
    void testGetColorMissState() {
        assertEquals(MISS, State.MISS.getColor(), "The color must be \u001B[0m");
    }

    @Test
    void testGetColorHitState() {
        assertEquals(HIT, State.HIT.getColor(), "The color must be \u001B[31m");
    }

    @Test
    void testHitShipState() {
        State state = State.SHIP;
        assertEquals(State.HIT, state.hit(), "The state must be HIT");
    }

    @Test
    void testHitVoidState() {
        State state = State.VOID;
        assertEquals(State.MISS, state.hit(), "The state must be MISS");
    }

    @Test
    void testHitMissState() {
        State state = State.MISS;
        assertEquals(State.MISS, state.hit(), "The state must be MISS");
    }

    @Test
    void testHitHitState() {
        State state = State.HIT;
        assertEquals(State.HIT, state.hit(), "The state must be HIT");
    }
}