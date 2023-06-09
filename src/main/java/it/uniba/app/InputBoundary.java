package it.uniba.app;

import it.uniba.app.controller.InputController;
import it.uniba.app.grid.type.Coordinate;
import it.uniba.app.grid.type.SizeGrid;
import it.uniba.app.parser.Parser;
import it.uniba.app.type.Command;
import it.uniba.app.type.Difficulty;
import it.uniba.app.utils.TimerPartita;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Class that interprets commands.
 */
public class InputBoundary {

    /**
     * Instance of the controller.
     */
    private InputController controller;

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
    private Scanner scanner;

    /**
     * Constructor of the class.
     */
    public final void execute(final boolean flag) {
        this.controller = InputController.getController(this);
        System.out.println(WELCOME_MESSAGE);
        if (flag) {
            this.help();
        } else {
            System.out.println(HELP_TIP);
        }
        this.scanner = new Scanner(System.in, "UTF-8");
        while (this.scanner.hasNextLine()) {
            controller.executeCommand(Parser.parseInput(this.scanner.nextLine(), Command.getPatterns()));
        }
        this.scanner.close();
    }

    /**
     * Sets the maximum time for the game timer based on the provided arguments.
     *
     * @param args the list of arguments containing the maximum time.
     */
    public void setMaxTime(final List<String> args) {
        if (!isInGame()) {
            if (args == null) {
                System.out.println("Non è stato passato nessun parametro");
            } else {
                try {
                    int time = Integer.parseInt(args.get(0));
                    TimerPartita.setMaxTime(time);
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
    public void showAttemps() {
        if (isInGame()) {
            int currentTries = Difficulty.getFailedTries();
            int maxTries = Difficulty.getMaxTries();
            int differenceTries = maxTries - currentTries;
            int totalTries = differenceTries + Difficulty.getCurrentTries();
            System.out.println("Hai effettuato " + totalTries + " tentativi.");
            System.out.println("Puoi effettuare ancora "
                    + currentTries + " errori. Hai effettuato " + differenceTries
                    + " tentativi falliti su "
                    + maxTries);
        } else {
            System.out.print(
                    "E' necessario essere in partita per poter visualizzare il numero dei tentativi rimanenti, ");
            System.out.println("inizia a giocare con /gioca");
        }
    }

    /**
     * Check if the game is currently in progress.
     *
     * @return true if the game is in progress, false otherwise
     */
    private boolean isInGame() {
        return TimerPartita.isRunning();
    }

    /**
     * Displays the current time to the user.
     * If a game is currently running, it shows the current elapsed time.
     * If no game is running, it provides a message indicating that no game is in
     * progress.
     */
    public void showCurrentTime() {
        if (isInGame()) {
            TimerPartita.printCurrentAndRemainingTime();
        } else {
            System.out.println("Non è in corso nessuna partita. Puoi creare una nuova partita con il comando /gioca");
        }
    }

    /**
     * Method to set size of grid based on the specified command.
     *
     * @param command the command specifying the new size of the grid
     */
    public void setSize(final Command command) {
        if (!isInGame()) {
            SizeGrid.setSize(SizeGrid.valueOf(command.toString()));
            System.out.println("OK");
        } else {
            System.out.println("Partita in corso!");
        }
    }

    /**
     * Method for close the application.
     */
    public void closeGame() {
        if (isInGame()) {
            System.out.print("Chiudere l'applicazione? [s/n] ");
            System.out.println("Puoi abbandonare la partita con /abbandona");
        } else {
            System.out.println("Chiudere l'applicazione? [s/n]");
        }
        if (this.confirm()) {
            Runtime.getRuntime().exit(0);
        }
    }

    /**
     * Prompts the user for confirmation and returns true if the user confirms with
     * "yes", and false if the user declines
     * with "no" or provides an invalid response.
     *
     * @return true if the user confirms, false otherwise
     */
    private boolean confirm() {
        Map<Command, List<String>> command;
        boolean canContinue = true;
        while (canContinue && this.scanner.hasNextLine()) {
            command = Command.parse(Parser.parseInput(this.scanner.nextLine(), Command.getPatterns()));
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
    public void setDifficulty(final Command command, final List<String> args) {
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
     * Method for change the difficulty.
     * 
     * @param command the command specifying the new difficulty
     */
    private void setOnlyDifficulty(final Command command) {
        Difficulty.setDifficulty(Difficulty.valueOf(command.toString()));
    }

    /**
     * Sets the maximum number of tries based on the provided arguments.
     *
     * @param args the list of arguments containing the maximum number of tries
     */
    public void setOnlyCurrentTries(final List<String> args) {
        if (!isInGame()) {
            if (args == null) {
                System.out.println("Non è stato passato nessun parametro");
            } else {
                try {
                    int tires = Integer.parseInt(args.get(0));
                    Difficulty.setMaxTries(tires);
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
    public void showLevel() {
        System.out.print("Livello di difficoltà: " + Difficulty.getDifficulty().toString());
        System.out.println(", numero massimo di tentativi: " + Difficulty.getMaxTries());
    }

    /**
     * Method to display command help.
     */
    public void help() {
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
            Runtime.getRuntime().exit(0);
        } else if (args.length == 1) {
            if (args[0].equals("-h") || args[0].equals("--help")) {
                flag = true;
            } else {
                System.out.println("ERRORE: Flag non valido, riprova con -h oppure --help \n");
                Runtime.getRuntime().exit(0);
            }
        } else {
            flag = false;
        }
        return flag;
    }

    /**
     * Prompt the user to exit the game and stop taking input if confirmed.
     */
    public void exitGame() {
        if (isInGame()) {
            System.out.println("Abbandonare la partita? [s/n]");
            if (confirm()) {
                printGrid();
                controller.quitGame();
            }
        } else {
            System.out.println("Non è in corso nessuna partita!");
        }
    }

    /**
     * Method to start a game.
     */
    public void playGame() {
        if (!isInGame()) {
            Coordinate coordinate;
            String input;
            controller.createGrid();
            controller.setUpGame();
            input = this.scanner.nextLine();
            while (isInGame() && Difficulty.getFailedTries() > 0) {
                coordinate = Coordinate.parse(Parser.parseInput(input, Coordinate.PATTERN));
                if (coordinate != null) {
                    if (coordinate.isValid()) {
                        String result = controller.fireShoot(coordinate);
                        System.out.print("Partita> ");
                        System.out.println(result);
                        System.out.println("Tentativi già effettuati: " + controller.getTotalTries());
                        TimerPartita.printCurrentTime();
                        if (controller.isGameFinish()) {
                            System.out.println("Hai affondato l'ultima nave, hai vinto!");
                            controller.quitGame();
                        }
                    } else {
                        System.out.println("Coordinata non valida");
                    }
                } else if (input != null) {
                    controller.executeCommand(Parser.parseInput(input, Command.getPatterns()));
                } else {
                    System.out.println("Coordinata non riconosciuta");
                }
                if (Difficulty.getFailedTries() <= 0) {
                    System.out.println("Hai finito i tentativi a disposizione, hai perso!");
                    controller.quitGame();
                }
                input = this.scanner.nextLine();
            }
            if (input != null) {
                if (input.contains("/")) {
                    input = "/" + input.split("/")[1];
                }
                controller.executeCommand(Parser.parseInput(input, Command.getPatterns()));
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
    public void printGrid() {
        if (isInGame()) {
            controller.getGrid().printGrid();
        } else {
            System.out.println("Non stai giocando, inizia a giocare con: /gioca");
        }
    }

    /**
     * Prints the current grid with the state of each cell.
     * Display HIT cells in red and MISS cells in white.
     * Format the grid display according to its size
     */
    public void printCurrentGrid() {
        if (isInGame()) {
            controller.getGrid().printCurrentGrid();
        } else {
            System.out.println("Non stai giocando, inizia a giocare con: /gioca");
        }
    }

    /**
     * Method to show current ships.
     */
    public void showShips() {
        if (isInGame()) {
            controller.getGrid().showShips();
        } else {
            System.out.print("E' necessario essere in partita per poter visualizzare l'elenco delle navi, ");
            System.out.println("inizia a giocare con /gioca");
        }
    }
}
