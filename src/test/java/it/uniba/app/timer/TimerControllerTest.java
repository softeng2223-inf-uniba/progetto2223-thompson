package it.uniba.app.timer.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniba.app.timer.controller.TimerController;

/**
 * Unit tests for the TimerController class.
 */
public class TimerControllerTest {
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
    public void setUp() {
        this.timerController = TimerController.CONTROLLER;
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
    public void testIsRunningBeforeStart() {
        assertFalse(this.timerController.isRunning());
    }

    /**
     * Tests the isRunning method after starting the game to ensure it returns true.
     */
    @Test
    public void testIsRunningStartGame() {
        this.timerController.startGame();
        assertTrue(this.timerController.isRunning());
    }

    /**
     * Tests the isRunning method after stopping the timer to ensure it returns
     * false.
     */
    @Test
    public void testIsRunningFinishGame() {
        this.timerController.startGame();
        this.timerController.stopTimer();
        assertFalse(this.timerController.isRunning());
    }

    /**
     * Tests the setMaxTime method with a positive number to ensure it sets the
     * maximum time correctly.
     */
    @Test
    public void testSetMaxTimePositiveNumber() {
        this.timerController.setMaxTime(MAX_TIME);
        assertEquals(MAX_TIME, this.timerController.getMaxTime());
    }

    /**
     * Tests the setMaxTime method with zero to ensure it sets the maximum time
     * correctly.
     */
    @Test
    public void testSetMaxTimeZero() {
        this.timerController.setMaxTime(0);
        assertEquals(0, this.timerController.getMaxTime());
    }

    /**
     * Tests the setMaxTime method with a negative number to ensure it throws an
     * IllegalArgumentException.
     */
    @Test
    public void testSetMaxTimeNegativeNumber() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    this.timerController.setMaxTime(MAX_TIME * -1);
                });
    }

}