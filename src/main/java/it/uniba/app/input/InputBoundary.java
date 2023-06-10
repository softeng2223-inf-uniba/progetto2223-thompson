package it.uniba.app.input;

import it.uniba.app.grid.type.Coordinate;
import it.uniba.app.parser.Parser;
import it.uniba.app.type.Command;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * <Boundary>
 *
 * Class that interprets commands.
 */
public abstract class InputBoundary {

    /**
     * Welcome message that prints when the program starts correctly.
     */
    private static final String WELCOME_MESSAGE = ""
            + "\n=========================================================================================\n"
            + "______       _   _   _           _     _       \n"
            + "| ___ \\     | | | | | |         | |   (_)      \n"
            + "| |_/ / __ _| |_| |_| | ___  ___| |__  _ _ __  \n"
            + "| ___ \\/ _` | __| __| |/ _ \\/ __| '_ \\| | '_ \\ \n"
            + "| |_/ / (_| | |_| |_| |  __/\\__ \\ | | | | |_) |\n"
            + "\\____/ \\__,_|\\__|\\__|_|\\___||___/_| |_|_| .__/ \n"
            + "                                        | |    \n"
            + "                                        |_|    \n"
            + "\n=========================================================================================\n"
            + "BENVENUTO/A\n"
            + "In questo gioco, il sistema posizionerà le navi nemiche su una griglia.\n"
            + "Il tuo obiettivo sarà quello di indovinare la loro posizione e affondarle una per una.\n"
            + "Vincerai solo se riuscirai ad affondarle tutte prima di esaurire le tue mosse!\n"
            + "Il numero di mosse a disposizione dipende dalla modalità di gioco scelta.\n"
            + "Preparati a sfidare il sistema e dimostra le tue abilità strategiche per vincere.\n"
            + "Buona fortuna!\n"
            + "=========================================================================================\n"
            + "Il livello di default è medio.\n";

    /**
     * Message suggesting how to view the list of available commands.
     */
    private static final String HELP_TIP = "Puoi utilizzare /help per visualizzare l'elenco dei comandi\n";

    /**
     * Scanner of the class.
     */
    private static Scanner scanner = new Scanner(System.in, "UTF-8");

    /**
     * Execute the command.
     *
     * @param inputCommand the command to execute
     */
    protected abstract void executeCommand(Map<String, Map<Integer, String>> inputCommand);

    /**
     * Quit the game by stopping the game timer and setting the game's running state
     * to false.
     */
    protected abstract void quitGame();

    /**
     * Create a new grid.
     */
    protected abstract void createGrid();

    /**
     * Check if the game is finished.
     *
     * @return true if the game is finished, false otherwise
     */
    protected abstract boolean isGameFinish();

    /**
     * Try a shoot to the grid.
     *
     * @param coordinate the coordinate to hit
     * @return the result of the shoot
     */
    protected abstract String fireShoot(Coordinate coordinate);

    /**
     * Set up the game.
     */
    protected abstract void setUpGame();

    /**
     * Get the total tries.
     *
     * @return the total tries
     */
    protected abstract int getTotalTries();

    /**
     * Gets the difference between the maximum tries and the current number of
     * tries.
     *
     * @return The difference between the maximum tries and the current number of
     *         tries.
     */
    protected abstract int getDifferenceTries();

    /**
     * Gets the maximum number of tries allowed for the game.
     *
     * @return The maximum number of tries.
     */
    protected abstract int getMaxTries();

    /**
     * Gets the current number of tries.
     *
     * @return The current number of tries.
     */
    protected abstract int getCurrentTries();

    /**
     * Check if the game is currently in progress.
     *
     * @return true if the game is in progress, false otherwise
     */
    protected abstract boolean isInGame();

    /**
     * Prints the grid with the ships if the user is not in the game.
     */
    protected abstract void printGrid();

    /**
     * Prints the current grid with the state of each cell.
     * Display HIT cells in red and MISS cells in white.
     * The grid display format is based on its size.
     */
    protected abstract void printCurrentGrid();

    /**
     * Displays the current state of the ships on the grid.
     */
    protected abstract void showShips();

    /**
     * Sets the maximum time allowed for the game.
     *
     * @param time The maximum time in seconds.
     */
    protected abstract void setMaxTime(int time);

    /**
     * Terminates the current game session.
     */
    protected abstract void killGame();

    /**
     * Prints the current time and remaining time for the game.
     */
    protected abstract void printCurrentAndRemainingTime();

    /**
     * Sets the size of the grid based on the provided command.
     *
     * @param command The command specifying the grid size.
     */
    protected abstract void setSizeGrid(Command command);

    /**
     * Method for change the difficulty.
     *
     * @param command the command specifying the new difficulty
     */
    protected abstract void setOnlyDifficulty(Command command);

    /**
     * Sets the maximum number of tries allowed for the game.
     *
     * @param tires The maximum number of tries to set.
     */
    protected abstract void setMaxTries(int tires);

    /**
     * Prints the current time of the game.
     */
    protected abstract void printCurrentTime();

    /**
     * Gets the difficulty level as a string representation.
     *
     * @return The difficulty level as a string.
     */
    protected abstract String getDifficultyString();

    /**
     * Constructor of the class.
     */
    public final void start(final boolean flag) {
        System.out.println(WELCOME_MESSAGE);
        if (flag) {
            this.help();
        } else {
            System.out.println(HELP_TIP);
        }
        while (scanner.hasNextLine()) {
            executeCommand(Parser.parseInput(scanner.nextLine(), Command.getPatterns()));
        }
        scanner.close();
    }

    /**
     * Sets the maximum time for the game timer based on the provided arguments.
     *
     * @param args the list of arguments containing the maximum time.
     */
    protected void setMaxTime(final List<String> args) {
        if (!isInGame()) {
            if (args == null) {
                System.out.println("Non è stato passato nessun parametro");
            } else {
                try {
                    int time = Integer.parseInt(args.get(0));
                    setMaxTime(time);
                    System.out.println("OK");
                } catch (IllegalArgumentException e) {
                    System.out.println("Numero non valido");
                }
            }
        } else {
            System.out.println("Non puoi cambiare il tempo massimo mentre una partita in corso!");
        }
    }

    /**
     * Displays the number of remaining attempts to the user.
     * If the game is currently running, it shows the remaining attempts based on
     * the current and maximum tries.
     * If the game is not running, it provides a message prompting the user to start
     * the game.
     */
    protected void showAttemps() {
        if (isInGame()) {
            System.out.println("Hai effettuato " + getTotalTries() + " tentativi.");
            System.out.println("Puoi effettuare ancora "
                    + getCurrentTries() + " errori. Hai effettuato " + getDifferenceTries()
                    + " tentativi falliti su "
                    + getMaxTries());
        } else {
            System.out.print(
                    "E' necessario essere in partita per poter visualizzare il numero dei tentativi rimanenti, ");
            System.out.println("inizia a giocare con /gioca");
        }
    }

    /**
     * Displays the current time to the user.
     * If a game is currently running, it shows the current elapsed time.
     * If no game is running, it provides a message indicating that no game is in
     * progress.
     */
    protected void boundaryShowCurrentTime() {
        if (isInGame()) {
            printCurrentAndRemainingTime();
        } else {
            System.out.println("Non è in corso nessuna partita. Puoi creare una nuova partita con il comando /gioca");
        }
    }

    /**
     * Method to set size of grid based on the specified command.
     *
     * @param command the command specifying the new size of the grid
     */
    protected void setSize(final Command command) {
        if (!isInGame()) {
            setSizeGrid(command);
            System.out.println("OK");
        } else {
            System.out.println("Partita in corso!");
        }
    }

    /**
     * Method for close the application.
     */
    protected void closeGame() {
        if (isInGame()) {
            System.out.print("Chiudere l'applicazione? [s/n] ");
            System.out.println("Puoi abbandonare la partita con /abbandona");
        } else {
            System.out.println("Chiudere l'applicazione? [s/n]");
        }
        if (this.confirm()) {
            killGame();
        }
    }

    /**
     * Prompts the user for confirmation and returns true if the user confirms with
     * "yes", and false if the user declines
     * with "no" or provides an invalid response.
     *
     * @return true if the user confirms, false otherwise
     */
    protected boolean confirm() {
        Map<Command, List<String>> command;
        boolean canContinue = true;
        while (canContinue && scanner.hasNextLine()) {
            command = Command.parse(Parser.parseInput(scanner.nextLine(), Command.getPatterns()));
            if (command == null) {
                System.out.println("Risposta non riconosciuta");
            } else if (command.containsKey(Command.YES)) {
                return true;
            } else if (command.containsKey(Command.NO)) {
                System.out.println("OK");
                canContinue = false;
            } else {
                System.out.println("Risposta non valida");
            }
        }
        return false;
    }

    /**
     * Method for change the difficulty and current tries.
     *
     * @param command the command specifying the new difficulty
     * @param args    the list of arguments containing the maximum number of tries
     */
    protected void setDifficulty(final Command command, final List<String> args) {
        if (!isInGame()) {
            if (args == null) {
                this.setOnlyDifficulty(command);
                System.out.println("OK");
            } else {
                this.setOnlyDifficulty(command);
                this.setOnlyCurrentTries(args);
            }
        } else {
            System.out.println("Non puoi cambiare la difficoltà durante la partita!");
        }

    }

    /**
     * Sets the maximum number of tries based on the provided arguments.
     *
     * @param args the list of arguments containing the maximum number of tries
     */
    protected void setOnlyCurrentTries(final List<String> args) {
        if (!isInGame()) {
            if (args == null) {
                System.out.println("Non è stato passato nessun parametro");
            } else {
                try {
                    int tires = Integer.parseInt(args.get(0));
                    setMaxTries(tires);
                    System.out.println("OK");
                } catch (IllegalArgumentException e) {
                    System.out.println("Numero non valido");
                }
            }
        } else {
            System.out.println("Non puoi cambiare il numero di tentativi durante la partita!");
        }
    }

    /**
     * Method to show the current difficulty and max tries.
     */
    protected void showLevel() {
        System.out.print("Livello di difficoltà: " + getDifficultyString());
        System.out.println(", numero massimo di tentativi: " + getMaxTries());
    }

    /**
     * Method to display command help.
     */
    protected void help() {
        System.out.println("ELENCO DEI COMANDI:");
        for (Command command : Command.values()) {
            String description = command.getDescription();
            if (!description.isEmpty()) {
                String args = command.getMaxArgs() > 0 ? command.getTypeToString() : "";
                if (!args.isEmpty()) {
                    args = " [" + args + "] ";
                }
                System.out.println(command.getNames()[0] + args + ": " + description);
            }
        }
        System.out.println();
    }

    /**
     * Controls the value of a flag based on command line arguments.
     *
     * @param args The command line arguments.
     * @return The updated value of the flag based on the command line
     *         arguments. If the flag is set to true, it means the help flag was
     *         specified. If the flag is set to false, it means no help flag was
     *         specified.
     */
    public boolean controlFlag(final String[] args) {
        boolean flag = false;
        if (args.length > 1) {
            System.out.print("ERRORE: Sono stati inseriti troppi flag all'avvio!");
            System.out.println(" Riprova inserendone solo uno.");
            killGame();
        } else if (args.length == 1) {
            if (args[0].equals("-h") || args[0].equals("--help")) {
                flag = true;
            } else {
                System.out.println("ERRORE: Flag non valido, riprova con -h oppure --help \n");
                killGame();
            }
        } else {
            flag = false;
        }
        return flag;
    }

    /**
     * Prompt the user to exit the game and stop taking input if confirmed.
     */
    protected void exitGame() {
        if (isInGame()) {
            System.out.println("Abbandonare la partita? [s/n]");
            if (confirm()) {
                printGrid();
                quitGame();
            }
        } else {
            System.out.println("Non è in corso nessuna partita!");
        }
    }

    /**
     * Method to start a game.
     */
    protected void playGame() {
        if (!isInGame()) {
            Coordinate coordinate;
            String input;
            createGrid();
            setUpGame();
            input = scanner.nextLine();
            while (isInGame() && getCurrentTries() > 0) {
                coordinate = Coordinate.parse(Parser.parseInput(input, Coordinate.PATTERN));
                if (coordinate != null) {
                    if (coordinate.isValid()) {
                        String result = fireShoot(coordinate);
                        System.out.print("Partita> ");
                        System.out.println(result);
                        System.out.println("Tentativi già effettuati: " + getTotalTries());
                        printCurrentTime();
                        if (isGameFinish()) {
                            System.out.println("Hai affondato l'ultima nave, hai vinto!");
                            quitGame();
                        }
                    } else {
                        System.out.println("Coordinata non valida");
                    }
                } else if (input != null) {
                    executeCommand(Parser.parseInput(input, Command.getPatterns()));
                } else {
                    System.out.println("Coordinata non riconosciuta");
                }
                if (getCurrentTries() <= 0) {
                    System.out.println("Hai finito i tentativi a disposizione, hai perso!");
                    quitGame();
                }
                input = scanner.nextLine();
            }
            if (input != null) {
                if (input.contains("/")) {
                    input = "/" + input.split("/")[1];
                }
                executeCommand(Parser.parseInput(input, Command.getPatterns()));
            }
        } else {
            System.out.print("E' già in corso una partita!");
            System.out.println(
                    "Per creare una nuova partita, è nesessario abbandonare la partita in corso con /abbandona");
        }
    }

    /**
     * Method to display the grid with the ships if user is not in game.
     */
    protected void boundaryPrintGrid() {
        if (isInGame()) {
            printGrid();
        } else {
            System.out.println("Non stai giocando, inizia a giocare con: /gioca");
        }
    }

    /**
     * Prints the current grid with the state of each cell.
     * Display HIT cells in red and MISS cells in white.
     * Format the grid display according to its size
     */
    protected void boundaryPrintCurrentGrid() {
        if (isInGame()) {
            printCurrentGrid();
        } else {
            System.out.println("Non stai giocando, inizia a giocare con: /gioca");
        }
    }

    /**
     * Method to show current ships.
     */
    protected void boundaryShowShips() {
        if (isInGame()) {
            showShips();
        } else {
            System.out.print("E' necessario essere in partita per poter visualizzare l'elenco delle navi, ");
            System.out.println("inizia a giocare con /gioca");
        }
    }
}
