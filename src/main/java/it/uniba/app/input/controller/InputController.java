package it.uniba.app.input.controller;

import java.util.List;
import java.util.Map;

import it.uniba.app.grid.controller.GridController;
import it.uniba.app.grid.type.Coordinate;
import it.uniba.app.grid.type.SizeGrid;
import it.uniba.app.input.InputBoundary;
import it.uniba.app.timer.controller.TimerController;
import it.uniba.app.type.Command;
import it.uniba.app.type.Difficulty;

/**
 * <Control>
 *
 * The InputController class is responsible for handling user commands and
 * controlling the game flow. It receives input commands from the user
 * interface, interprets them, and executes the corresponding actions.
 */
public final class InputController extends InputBoundary {
    private static final InputController CONTROLLER = new InputController();

    /**
     * Returns the instance of the InputController class.
     */
    public static InputController getInstance() {
        return CONTROLLER;
    }

    private InputController() {
    }

    /**
     * Execute the command.
     *
     * @param inputCommand the command to execute
     */
    @Override
    protected void executeCommand(final Map<String, Map<Integer, String>> inputCommand) {
        Map<Command, List<String>> command = Command.parse(inputCommand);
        if (command == null) {
            printCommandNotRecognized();
        } else if (command.containsKey(Command.EXIT)) {
            closeGame();
        } else if (command.containsKey(Command.HELP)) {
            help();
        } else if (command.containsKey(Command.ATTEMPS)) {
            setOnlyCurrentTries(command.get(Command.ATTEMPS));
        } else if (command.containsKey(Command.EASY) || command.containsKey(Command.EASY_NOARG)) {
            setDifficulty(Command.EASY, command.get(Command.EASY));
        } else if (command.containsKey(Command.MEDIUM) || command.containsKey(Command.MEDIUM_NOARG)) {
            setDifficulty(Command.MEDIUM, command.get(Command.MEDIUM));
        } else if (command.containsKey(Command.HARD) || command.containsKey(Command.HARD_NOARG)) {
            setDifficulty(Command.HARD, command.get(Command.HARD));
        } else if (command.containsKey(Command.PLAY)) {
            playGame();
        } else if (command.containsKey(Command.SHOW_LEVEL)) {
            showLevel();
        } else if (command.containsKey(Command.REVEAL_GRID)) {
            boundaryPrintGrid();
        } else if (command.containsKey(Command.SHOW_GRID)) {
            boundaryPrintCurrentGrid();
        } else if (command.containsKey(Command.SHOW_SHIPS)) {
            boundaryShowShips();
        } else if (command.containsKey(Command.STANDARD)) {
            setSize(Command.STANDARD);
        } else if (command.containsKey(Command.LARGE)) {
            setSize(Command.LARGE);
        } else if (command.containsKey(Command.EXTRALARGE)) {
            setSize(Command.EXTRALARGE);
        } else if (command.containsKey(Command.TIME)) {
            setMaxTime(command.get(Command.TIME));
        } else if (command.containsKey(Command.SHOW_ATTEMPS)) {
            showAttemps();
        } else if (command.containsKey(Command.SHOW_TIME)) {
            boundaryShowCurrentTime();
        } else if (command.containsKey(Command.SURREND)) {
            exitGame();
        } else {
            printInvalidCommand();
        }
    }

    /**
     * Quit the game by stopping the game timer and setting the game's running state
     * to false.
     */
    @Override
    protected void quitGame() {
        TimerController controller = TimerController.getIstance();
        controller.setRunning(false);
        controller.stopTimer();
    }

    /**
     * Create a new grid.
     *
     */
    @Override
    protected void createGrid() {
        GridController controller = GridController.getInstance();
        controller.newGrid();
        controller.printCurrentGrid();
    }

    /**
     * Check if the game is finished.
     *
     * @return true if the game is finished, false otherwise
     */
    @Override
    protected boolean isGameFinish() {
        GridController controller = GridController.getInstance();
        return controller.isAllSunken();
    }

    /**
     * Try a shoot to the grid.
     *
     * @param coordinate the coordinate to hit
     * @return the result of the shoot
     */
    @Override
    protected String fireShoot(final Coordinate coordinate) {
        GridController controller = GridController.getInstance();
        String result = controller.hitCoordinate(coordinate);
        controller.printCurrentGrid();
        return result;
    }

    /**
     * Set up the game.
     */
    @Override
    protected void setUpGame() {
        TimerController controller = TimerController.getIstance();
        int tries = Difficulty.getMaxTries();
        Difficulty.setFailedTries(tries);
        Difficulty.setCurrentTries(0);
        controller.startGame();
    }

    /**
     * Get the total tries.
     *
     * @return the total tries
     */
    @Override
    protected int getTotalTries() {
        int currentTries = Difficulty.getFailedTries();
        int maxTries = Difficulty.getMaxTries();
        return maxTries - currentTries + Difficulty.getCurrentTries();
    }

    @Override
    protected boolean isInGame() {
        TimerController controller = TimerController.getIstance();
        return controller.isRunning();
    }

    /**
     * Prints the grid with the ships if the user is not in the game.
     */
    @Override
    public void printGrid() {
        GridController controller = GridController.getInstance();
        controller.printGrid();
    }

    /**
     * Prints the current grid with the state of each cell.
     * Display HIT cells in red and MISS cells in white.
     * The grid display format is based on its size.
     */
    @Override
    protected void printCurrentGrid() {
        GridController controller = GridController.getInstance();
        controller.printCurrentGrid();
    }

    /**
     * Displays the current state of the ships on the grid.
     */
    @Override
    protected void showShips() {
        GridController controller = GridController.getInstance();
        controller.showShips();
    }

    /**
     * Sets the maximum time allowed for the game.
     *
     * @param time The maximum time in seconds.
     */
    @Override
    protected void setMaxMinuteTime(final int time) throws IllegalArgumentException {
        TimerController controller = TimerController.getIstance();
        controller.setMaxTime(time);
    }

    /**
     * Gets the difference between the maximum tries and the current number of
     * tries.
     *
     * @return The difference between the maximum tries and the current number of
     *         tries.
     */
    @Override
    protected int getDifferenceTries() {
        return Difficulty.getMaxTries() - Difficulty.getFailedTries();
    }

    /**
     * Gets the maximum number of tries allowed for the game.
     *
     * @return The maximum number of tries.
     */
    @Override
    protected int getMaxTries() {
        return Difficulty.getMaxTries();
    }

    /**
     * Gets the current number of tries.
     *
     * @return The current number of tries.
     */
    @Override
    protected int getCurrentTries() {
        return Difficulty.getFailedTries();
    }

    /**
     * Terminates the current game session.
     */
    @Override
    protected void killGame() {
        Runtime.getRuntime().exit(0);
    }

    /**
     * Prints the current time and remaining time for the game.
     */
    @Override
    protected void printCurrentAndRemainingTime() {
        TimerController controller = TimerController.getIstance();
        controller.printCurrentAndRemainingTime();
    }

    /**
     * Sets the size of the grid based on the provided command.
     *
     * @param command The command specifying the grid size.
     */
    @Override
    protected void setSizeGrid(final Command command) {
        SizeGrid.setSize(SizeGrid.valueOf(command.toString()));
    }

    /**
     * Method for change the difficulty.
     *
     * @param command the command specifying the new difficulty
     */
    @Override
    protected void setOnlyDifficulty(final Command command) {
        Difficulty.setDifficulty(Difficulty.valueOf(command.toString()));
    }

    /**
     * Sets the maximum number of tries allowed for the game.
     *
     * @param tires The maximum number of tries to set.
     */
    @Override
    protected void setMaxTries(final int tires) {
        Difficulty.setMaxTries(tires);
    }

    /**
     * Prints the current time of the game.
     */
    @Override
    protected void printCurrentTime() {
        TimerController controller = TimerController.getIstance();
        controller.printCurrentTime();
    }

    /**
     * Gets the difficulty level as a string representation.
     *
     * @return The difficulty level as a string.
     */
    @Override
    protected String getDifficultyString() {
        return Difficulty.getDifficulty().toString();
    }
}
