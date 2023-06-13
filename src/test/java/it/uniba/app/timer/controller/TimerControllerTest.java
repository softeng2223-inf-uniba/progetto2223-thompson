package it.uniba.app.timer.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniba.app.grid.controller.GridController;

/**
 * Unit tests for the TimerController class.
 */
class TimerControllerTest {
    private TimerController timerController;
    private static final int MAX_TIME = 5;
    private static final int TO_SECOND = 60;
    private static final int TO_MILLISECOND = 1000;
    private static final int TEST_SECOND = 63;
    private static final int RESULT_MINUTE = 1;
    private static final int RESULT_SECOND = 3;

    /**
     * Set up the TimerController instance before each test.
     */
    @BeforeEach
    void setUp() {
        this.timerController = TimerController.CONTROLLER;
        GridController.INSTANCE.newGrid();
        this.timerController.setMaxTime(MAX_TIME);
    }

    /**
     * Stop the timer after each test.
     */
    @AfterEach
    void tearDown() {
        this.timerController.stopTimer();
    }

    /**
     * Tests the isRunning method before starting the game to ensure it returns
     * false.
     */
    @Test
    void testIsRunningBeforeStart() {
        assertFalse(this.timerController.isRunning(), "The timer must not be running");
    }

    /**
     * Tests the isRunning method after starting the game to ensure it returns true.
     */
    @Test
    void testIsRunningStartGame() {
        this.timerController.startGame();
        assertTrue(this.timerController.isRunning(), "The timer must be running");
    }

    /**
     * Tests the isRunning method after stopping the timer to ensure it returns
     * false.
     */
    @Test
    void testIsRunningFinishGame() {
        this.timerController.startGame();
        this.timerController.stopTimer();
        assertFalse(this.timerController.isRunning(), "The timer must not be running");
    }

    /**
     * Tests the setMaxTime method with a positive number to ensure it sets the
     * maximum time correctly.
     */
    @Test
    void testSetMaxTimePositiveNumber() {
        this.timerController.setMaxTime(MAX_TIME);
        assertEquals(MAX_TIME, this.timerController.getMaxTime(), "The max time must be " + MAX_TIME);
    }

    /**
     * Tests the setMaxTime method with zero to ensure it sets the maximum time
     * correctly.
     */
    @Test
    void testSetMaxTimeZero() {
        this.timerController.setMaxTime(0);
        assertEquals(0, this.timerController.getMaxTime(), "The max time must be 0");
    }

    /**
     * Tests the setMaxTime method with a negative number to ensure it throws an
     * IllegalArgumentException.
     */
    @Test
    void testSetMaxTimeNegativeNumber() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    this.timerController.setMaxTime(MAX_TIME * -1);
                }, "The max time must be a positive number");
    }

    /**
     * Tests the getCurrentTimeSecond method to ensure it returns the current time
     * in seconds.
     */
    @Test
    void testGetCurrentTimeSecond() {
        this.timerController.startGame();
        try {
            Thread.sleep(TO_MILLISECOND);
        } catch (InterruptedException e) {
        }

        long currentTimeSeconds = this.timerController.getCurrentTimeSecond();
        assertTrue(currentTimeSeconds == 1, "The current time must be 1 second");
    }

    /**
     * Tests the getMaxTimeSecond method to ensure it returns the maximum time in
     * seconds.
     */
    @Test
    void testGetMaxTimeSecond() {
        this.timerController.setMaxTime(MAX_TIME);
        assertEquals(MAX_TIME * TO_SECOND, this.timerController.getMaxTimeSecond(),
                "The max time must be " + MAX_TIME * TO_SECOND + " seconds");
    }

    /**
     * Tests the getMinutePassed method to ensure it returns the correct number of
     * minutes passed.
     */
    @Test
    void testGetMinutePassed() {
        assertEquals(RESULT_MINUTE, this.timerController.getMinutePassed(TEST_SECOND),
                "The minutes passed must be " + RESULT_MINUTE);
    }

    /**
     * Tests the getSecondPassed method to ensure it returns the correct number of
     * seconds passed.
     */
    @Test
    void testGetSecondPassed() throws InterruptedException {
        assertEquals(RESULT_SECOND, this.timerController.getSecondPassed(TEST_SECOND),
                "The seconds passed must be " + RESULT_SECOND);
    }

    /**
     * Tests the setRunning method to ensure it sets the running status correctly.
     */
    @Test
    void testSetRunning() {
        this.timerController.setRunning(true);
        assertTrue(this.timerController.isRunning(), "The timer must be running");
    }
}
