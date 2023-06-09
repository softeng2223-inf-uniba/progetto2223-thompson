package it.uniba.app.controller;

import java.util.List;
import java.util.Map;

import it.uniba.app.InputBoundary;
import it.uniba.app.grid.Grid;
import it.uniba.app.grid.type.Coordinate;
import it.uniba.app.type.Command;
import it.uniba.app.type.Difficulty;
import it.uniba.app.utils.TimerPartita;

/**
 * Class for commands that the user may enter.
 */
public final class InputController {
    private static InputController controller;
    private InputBoundary partita;

    /**
     * Grid of the current match.
     */
    private Grid grid;

    private InputController(final InputBoundary valPartita) {
        partita = valPartita;
    }

    /**
     * Get the instance of the controller.
     *
     * @param valPartita the instance of the game
     * @return the instance of the controller
     */
    public static InputController getController(final InputBoundary valPartita) {
        if (controller == null) {
            controller = new InputController(valPartita);
        }
        return controller;
    }

    /**
     * Execute the command.
     *
     * @param inputCommand the command to execute
     */
    public void executeCommand(final Map<String, Map<Integer, String>> inputCommand) {
        Map<Command, List<String>> command = Command.parse(inputCommand);
        if (command == null) {
            System.out.println("Comando non riconosciuto");
        } else if (command.containsKey(Command.EXIT)) {
            partita.closeGame();
        } else if (command.containsKey(Command.HELP)) {
            partita.help();
        } else if (command.containsKey(Command.ATTEMPS)) {
            partita.setOnlyCurrentTries(command.get(Command.ATTEMPS));
        } else if (command.containsKey(Command.EASY) || command.containsKey(Command.EASY_NOARG)) {
            partita.setDifficulty(Command.EASY, command.get(Command.EASY));
        } else if (command.containsKey(Command.MEDIUM) || command.containsKey(Command.MEDIUM_NOARG)) {
            partita.setDifficulty(Command.MEDIUM, command.get(Command.MEDIUM));
        } else if (command.containsKey(Command.HARD) || command.containsKey(Command.HARD_NOARG)) {
            partita.setDifficulty(Command.HARD, command.get(Command.HARD));
        } else if (command.containsKey(Command.PLAY)) {
            partita.playGame();
        } else if (command.containsKey(Command.SHOW_LEVEL)) {
            partita.showLevel();
        } else if (command.containsKey(Command.REVEAL_GRID)) {
            partita.printGrid();
        } else if (command.containsKey(Command.SHOW_GRID)) {
            partita.printCurrentGrid();
        } else if (command.containsKey(Command.SHOW_SHIPS)) {
            partita.showShips();
        } else if (command.containsKey(Command.STANDARD)) {
            partita.setSize(Command.STANDARD);
        } else if (command.containsKey(Command.LARGE)) {
            partita.setSize(Command.LARGE);
        } else if (command.containsKey(Command.EXTRALARGE)) {
            partita.setSize(Command.EXTRALARGE);
        } else if (command.containsKey(Command.TIME)) {
            partita.setMaxTime(command.get(Command.TIME));
        } else if (command.containsKey(Command.SHOW_ATTEMPS)) {
            partita.showAttemps();
        } else if (command.containsKey(Command.SHOW_TIME)) {
            partita.showCurrentTime();
        } else if (command.containsKey(Command.SURREND)) {
            partita.exitGame();
        } else {
            System.out.println("Comando non valido");
        }
    }

    /**
     * Quit the game by stopping the game timer and setting the game's running state
     * to false.
     */
    public void quitGame() {
        TimerPartita.setRunning(false);
        TimerPartita.stopTimer();
    }

    /**
     * Get the current grid.
     *
     * @return the current grid
     */
    public Grid getGrid() {
        return grid;
    }

    /**
     * Create a new grid.
     *
     * @return the new grid
     */
    public Grid createGrid() {
        this.grid = new Grid();
        this.grid.generateGrid();
        this.grid.printCurrentGrid();
        return grid;
    }

    /**
     * Check if the game is finished.
     *
     * @return true if the game is finished, false otherwise
     */
    public boolean isGameFinish() {
        return grid.isAllSunken();
    }

    /**
     * Try a shoot to the grid.
     *
     * @param coordinate the coordinate to hit
     * @return the result of the shoot
     */
    public String fireShoot(final Coordinate coordinate) {
        String result = this.grid.hitCoordinate(coordinate);
        this.grid.printCurrentGrid();
        return result;
    }

    /**
     * Set up the game.
     */
    public void setUpGame() {
        int tries = Difficulty.getMaxTries();
        Difficulty.setFailedTries(tries);
        Difficulty.setCurrentTries(0);
        TimerPartita timer = new TimerPartita();
        timer.startGame();
    }

    /**
     * Get the total tries.
     * @return the total tries
     */
    public int getTotalTries() {
        int currentTries = Difficulty.getFailedTries();
        int maxTries = Difficulty.getMaxTries();
        return maxTries - currentTries + Difficulty.getCurrentTries();
    }
}
