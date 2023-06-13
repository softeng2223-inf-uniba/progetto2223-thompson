package it.uniba.app.type;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Difficulty class.
 */
class DifficultyTest {
    private static final int TRIES = 50;
    private static final int SET_TRIES = 8;
    private static final int HARD_TRIES = 10;

    /**
     * Tests the getCurrentTries method to ensure it returns the correct number of
     * current tries for the current difficulty.
     */
    @Test
    void testGetCurrentTries() {
        Difficulty.setCurrentTries(TRIES);
        assertEquals(TRIES, Difficulty.getCurrentTries(), "The current tries must be 50");
    }

    /**
     * Tests the setCurrentTries method to ensure it sets the current number of
     * tries correctly.
     */
    @Test
    void testSetCurrentTries() {
        Difficulty.setCurrentTries(TRIES + SET_TRIES);
        assertEquals(TRIES + SET_TRIES, Difficulty.getCurrentTries(), "The current tries must be 58");
    }

    /**
     * Tests the setCurrentTries method to ensure it sets the current number of
     * tries correctly.
     */
    @Test
    void testSetCurrentTriesNegativeNumber() {
        Difficulty.setCurrentTries((TRIES + SET_TRIES) * -1);
        assertEquals(0, Difficulty.getCurrentTries(), "The current tries must be 58");
    }

    /**
     * Tests the getMaxTries method to ensure it returns the correct maximum number
     * of tries for the current difficulty.
     */
    @Test
    void testGetMaxTries() {
        Difficulty.setDifficulty(Difficulty.MEDIUM);
        assertEquals(Difficulty.MEDIUM.getTries(), Difficulty.getMaxTries(), "The max tries must be 20");
    }

    /**
     * Tests the setMaxTries method to ensure it sets the maximum number of tries
     * correctly.
     */
    @Test
    void testSetMaxTries() {
        Difficulty.setMaxTries(TRIES);
        assertEquals(TRIES, Difficulty.getMaxTries(), "The max tries must be 50");
    }

    /**
     * Tests the setMaxTries method with a negative value to ensure it throws an
     * IllegalArgumentException.
     */
    @Test
    void testSetMaxTriesIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Difficulty.setMaxTries(TRIES * -1),
                "The max tries must be positive");
    }

    /**
     * Tests the setDifficulty method with a Difficulty enum to ensure it sets the
     * difficulty correctly.
     */
    @Test
    void testSetDifficultyNull() {
        assertThrows(IllegalArgumentException.class, () -> Difficulty.setDifficulty(null),
                "The difficulty must not be null");
    }

    /**
     * Tests the setDifficulty method with a Difficulty enum to ensure it sets the
     * difficulty correctly.
     */
    @Test
    void testSetDifficultyDifficulty() {
        Difficulty.setDifficulty(Difficulty.EASY);
        assertEquals(Difficulty.EASY, Difficulty.getDifficulty(), "The difficulty must be EASY");
    }

    /**
     * Tests the setDifficulty method with a Difficulty enum to ensure it also sets
     * the maximum number of tries correctly.
     */
    @Test
    void testSetDifficultyGetMaxTries() {
        Difficulty.setDifficulty(Difficulty.MEDIUM);
        assertEquals(Difficulty.MEDIUM.getTries(), Difficulty.getMaxTries(), "The max tries must be 20");
    }

    /**
     * Tests the getFailedTries method to ensure it returns the correct number of
     * failed tries.
     */
    @Test
    void testGetFailedTries() {
        Difficulty.setFailedTries(SET_TRIES);
        assertEquals(SET_TRIES, Difficulty.getFailedTries(), "The failed tries must be 8");
    }

    /**
     * Tests the setFailedTries method to ensure it sets the number of failed tries
     * correctly.
     */
    @Test
    void testSetFailedTries() {
        Difficulty.setMaxTries(TRIES);
        Difficulty.setFailedTries(HARD_TRIES);
        assertEquals(HARD_TRIES, Difficulty.getFailedTries(), "The failed tries must be 10");
    }

    /**
     * Tests the setFailedTries method to ensure it sets the number of failed tries
     * correctly.
     */
    @Test
    void testSetFailedTriesGreaterMaxTries() {
        Difficulty.setMaxTries(TRIES);
        Difficulty.setFailedTries(TRIES + 1);
        assertEquals(TRIES, Difficulty.getFailedTries(), "The failed tries must be 50");
    }

    /**
     * Tests the setFailedTries method to ensure it sets the number of failed tries
     * correctly.
     */
    @Test
    void testSetFailedTriesLess0() {
        Difficulty.setFailedTries(-1);
        assertEquals(0, Difficulty.getFailedTries(), "The failed tries must be 0");
    }

    /**
     * Tests the getTries method to ensure it returns the correct number of tries
     * for a Difficulty enum.
     */
    @Test
    void testGetTries() {
        assertEquals(TRIES, Difficulty.EASY.getTries(), "The tries must be 50");
    }

    /**
     * Tests the toString method to ensure it returns the correct string
     * representation of a Difficulty enum.
     */
    @Test
    void testToString() {
        assertEquals("difficile", Difficulty.HARD.toString(), "The string must be 'difficile'");
    }

    /**
     * Tests the getDifficulty method.
     * This test does not contain any assertions and is incomplete.
     * Additional assertions or test cases can be added as needed.
     */
    @Test
    void testGetDifficultyDifficulty() {
        Difficulty.setDifficulty(Difficulty.HARD);
        assertEquals(Difficulty.HARD, Difficulty.getDifficulty(), "The difficulty must be HARD");
    }

    /**
     * Tests the getDifficulty method.
     * This test does not contain any assertions and is incomplete.
     * Additional assertions or test cases can be added as needed.
     */
    @Test
    void testGetDifficultyTries() {
        Difficulty.setDifficulty(Difficulty.HARD);
        assertEquals(HARD_TRIES, Difficulty.getDifficulty().getTries(), "The tries must be 10");
    }
}
