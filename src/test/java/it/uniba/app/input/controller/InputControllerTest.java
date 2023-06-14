package it.uniba.app.input.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.uniba.app.grid.controller.GridController;
import it.uniba.app.grid.type.Column;
import it.uniba.app.grid.type.Coordinate;
import it.uniba.app.grid.type.SizeGrid;
import it.uniba.app.parser.Parser;
import it.uniba.app.timer.controller.TimerController;
import it.uniba.app.type.Command;
import it.uniba.app.type.Difficulty;

/**
 * Unit tests for the InputController class.
 */
class InputControllerTest {
    private InputController inputController = InputController.getInstance();
    private static final int TRIES = 500;
    private static final int MAX_TRIES = 10;
    private static final int CURRENT_TRIES = 3;
    private static final int RESULT = 7;
    private static final int SIZE = 10;

    /**
     * Tests the executeCommand method with a SetDifficultyCommand input.
     */
    @Test
    void testExecuteCommandSetDifficultyCommand() {
        String input = "/medio";
        inputController.executeCommand(Parser.parseInput(input, Command.getPatterns()));
        assertEquals(Difficulty.MEDIUM, Difficulty.getDifficulty(), "The difficulty must be MEDIUM");
    }

    /**
     * Tests the executeCommand method with a PlayCommand input.
     */
    @Test
    void testExecuteCommandPlayCommand() {
        String input = "/gioca";
        inputController.executeCommand(Parser.parseInput(input, Command.getPatterns()));
        assertTrue(inputController.isInGame(), "The game must be started");
    }

    /**
     * Tests the executeCommand method with a TriesCommand input.
     */
    @Test
    void testExecuteCommandTriesCommand() {
        String input = "/tentativi " + TRIES;
        inputController.executeCommand(Parser.parseInput(input, Command.getPatterns()));
        assertTrue(Difficulty.getMaxTries() == TRIES, "The max tries must be " + TRIES);
    }

    /**
     * Tests the quitGame method.
     */
    @Test
    void testQuitGame() {
        TimerController controller = TimerController.getIstance();
        controller.startGame();
        inputController.quitGame();
        assertFalse(inputController.isInGame(), "The game must be stopped");
    }

    /**
     * Tests the createGrid method.
     */
    @Test
    void testCreateGrid() {
        inputController.createGrid();
        assertFalse(inputController.isGameFinish(), "The game must be not finished");
    }

    /**
     * Tests the fireShoot method.
     */
    @Test
    void testFireShoot() {
        inputController.createGrid();
        Coordinate coordinate = new Coordinate(Column.B, 1);
        String result = inputController.fireShoot(coordinate);
        assertTrue(result.equals("acqua") || result.equals("colpito") || result.equals("colpito e affondato"),
                "The result must be 'acqua', 'colpito' or 'colpito e affondato'");
    }

    /**
     * Tests the getDifferenceTries method.
     */
    @Test
    void testGetDifferenceTries() {
        Difficulty.setMaxTries(MAX_TRIES);
        Difficulty.setFailedTries(CURRENT_TRIES);
        int difference = inputController.getDifferenceTries();
        assertEquals(RESULT, difference, "The difference must be " + RESULT);
    }

    /**
     * Tests the setSizeGrid method.
     */
    @Test
    void testSetSizeGrid() {
        String input = "/standard";
        inputController.executeCommand(Parser.parseInput(input, Command.getPatterns()));
        assertEquals(SIZE, SizeGrid.getSize(), "The size must be " + SIZE);
    }

    /**
     * Tests the setOnlyDifficulty method.
     */
    @Test
    void testSetOnlyDifficulty() {
        String input = "/facile";
        inputController.executeCommand(Parser.parseInput(input, Command.getPatterns()));
        assertEquals(Difficulty.EASY, Difficulty.getDifficulty(), "The difficulty must be EASY");
    }

    /**
     * Tests the setMaxTries method.
     */
    @Test
    void testSetMaxTries() {
        inputController.setMaxTries(TRIES);
        assertEquals(TRIES, Difficulty.getMaxTries(), "The max tries must be " + TRIES);
    }

    /**
     * Tests the getDifficultyString method.
     */
    @Test
    void testGetDifficultyString() {
        Difficulty.setDifficulty(Difficulty.MEDIUM);
        String difficultyString = inputController.getDifficultyString();
        assertEquals("medio", difficultyString, "The difficulty string must be 'medio'");
    }

    /**
     * Tests the getTotalTries method when there are no tries.
     */
    @Test
    void testGetTotalTriesNoTries() {
        assertEquals(0, inputController.getTotalTries(), "The total tries must be 0");
    }

    /**
     * Tests the getTotalTries method when there are some tries.
     */
    @Test
    void testGetTotalTriesTries() {
        inputController.createGrid();
        Coordinate coordinate = new Coordinate(Column.B, 1);
        inputController.fireShoot(coordinate);
        coordinate = new Coordinate(Column.B, 2);
        inputController.fireShoot(coordinate);
        assertEquals(2, inputController.getTotalTries(), "The total tries must be 2");
    }

    /**
     * Tests the isInGame method when the game is in progress.
     */
    @Test
    void testIsInGameTrue() {
        TimerController controller = TimerController.getIstance();
        controller.startGame();
        assertTrue(inputController.isInGame(), "The game must be in progress");
    }

    /**
     * Tests the isInGame method when the game is not in progress.
     */
    @Test
    void testIsInGameFalse() {
        GridController gridController = GridController.getInstance();
        gridController.newGrid();
        TimerController timerController = TimerController.getIstance();
        timerController.startGame();
        timerController.stopTimer();
        assertFalse(inputController.isInGame(), "The game must be not in progress");
    }

    /**
     * Tests the setUpGame method.
     */
    @Test
    void testSetUpGame() {
        inputController.setUpGame();
        assertTrue(inputController.isInGame(), "The game must be in progress");
    }
}
