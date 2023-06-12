package it.uniba.app.input.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

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
public class InputControllerTest {
    private InputController inputController = InputController.CONTROLLER;
    private static final int TRIES = 500;
    private static final int MAX_TRIES = 10;
    private static final int CURRENT_TRIES = 3;
    private static final int RESULT = 7;
    private static final int SIZE = 10;

    /**
     * Tests the executeCommand method with a SetDifficultyCommand input.
     */
    @Test
    public void testExecuteCommandSetDifficultyCommand() {
        String input = "/medio";
        inputController.executeCommand(Parser.parseInput(input, Command.getPatterns()));
        assertEquals(Difficulty.MEDIUM, Difficulty.getDifficulty());
    }

    /**
     * Tests the executeCommand method with a PlayCommand input.
     */
    @Test
    public void testExecuteCommandPlayCommand() {
        String input = "/gioca";
        inputController.executeCommand(Parser.parseInput(input, Command.getPatterns()));
        assertTrue(inputController.isInGame());
    }

    /**
     * Tests the executeCommand method with a TriesCommand input.
     */
    @Test
    public void testExecuteCommandTriesCommand() {
        String input = "/tentativi " + TRIES;
        inputController.executeCommand(Parser.parseInput(input, Command.getPatterns()));
        assertTrue(Difficulty.getMaxTries() == TRIES);
    }

    /**
     * Tests the quitGame method.
     */
    @Test
    public void testQuitGame() {
        TimerController.CONTROLLER.startGame();
        inputController.quitGame();
        assertFalse(inputController.isInGame());
    }

    /**
     * Tests the createGrid method.
     */
    @Test
    public void testCreateGrid() {
        inputController.createGrid();
        assertFalse(inputController.isGameFinish());
    }

    /**
     * Tests the fireShoot method.
     */
    @Test
    public void testFireShoot() {
        inputController.createGrid();
        Coordinate coordinate = new Coordinate(Column.B, 1);
        String result = inputController.fireShoot(coordinate);
        assertTrue(result.equals("acqua") || result.equals("colpito") || result.equals("colpito e affondato"));
    }

    /**
     * Tests the getDifferenceTries method.
     */
    @Test
    public void testGetDifferenceTries() {
        Difficulty.setMaxTries(MAX_TRIES);
        Difficulty.setFailedTries(CURRENT_TRIES);

        int difference = inputController.getDifferenceTries();

        assertEquals(RESULT, difference);
    }

    /**
     * Tests the setSizeGrid method.
     */
    @Test
    public void testSetSizeGrid() {
        String input = "/standard";
        inputController.executeCommand(Parser.parseInput(input, Command.getPatterns()));
        assertEquals(SIZE, SizeGrid.getSize());
    }

    /**
     * Tests the setOnlyDifficulty method.
     */
    @Test
    public void testSetOnlyDifficulty() {
        String input = "/facile";
        inputController.executeCommand(Parser.parseInput(input, Command.getPatterns()));
        assertEquals(Difficulty.EASY, Difficulty.getDifficulty());
    }

    /**
     * Tests the setMaxTries method.
     */
    @Test
    public void testSetMaxTries() {
        inputController.setMaxTries(TRIES);
        assertEquals(TRIES, Difficulty.getMaxTries());
    }

    /**
     * Tests the getDifficultyString method.
     */
    @Test
    public void testGetDifficultyString() {
        Difficulty.setDifficulty(Difficulty.MEDIUM);
        String difficultyString = inputController.getDifficultyString();
        assertEquals("medio", difficultyString);
    }

    /**
     * Tests the getTotalTries method when there are no tries.
     */
    @Test
    void testGetTotalTriesNoTries() {
        assertEquals(0, inputController.getTotalTries());
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
        assertEquals(2, inputController.getTotalTries());
    }

    /**
     * Tests the isInGame method when the game is in progress.
     */
    @Test
    void testIsInGameTrue() {
        TimerController.CONTROLLER.startGame();
        assertTrue(inputController.isInGame());
    }

    /**
     * Tests the isInGame method when the game is not in progress.
     */
    @Test
    void testIsInGameFalse() {
        TimerController.CONTROLLER.startGame();
        TimerController.CONTROLLER.stopTimer();
        assertFalse(inputController.isInGame());
    }

    /**
     * Tests the setUpGame method.
     */
    @Test
    void testSetUpGame() {
        inputController.setUpGame();
        assertTrue(inputController.isInGame());
    }
}
