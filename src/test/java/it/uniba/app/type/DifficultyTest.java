package it.uniba.app.type;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Difficulty class.
 */
public class DifficultyTest {
    private static final int TRIES = 50;
    private static final int SET_TRIES = 8;
    private static final int HARD_TRIES = 10;

    /**
     * Tests the getCurrentTries method to ensure it returns the correct number of
     * current tries for the current difficulty.
     */
    @Test
    void testGetCurrentTries() {
        Difficulty.setDifficulty(Difficulty.EASY);
        assertEquals(0, Difficulty.getCurrentTries());
    }

    /**
     * Tests the setCurrentTries method to ensure it sets the current number of
     * tries correctly.
     */
    @Test
    void testSetCurrentTries() {
        Difficulty.setCurrentTries(TRIES);
        assertEquals(TRIES, Difficulty.getCurrentTries());
    }
}