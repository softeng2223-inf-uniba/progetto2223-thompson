package it.uniba.app;

import it.uniba.app.grid.Grid;
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
public class Partita {

    /**
     * Grid of the current match.
     */
    private Grid grid;

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
     * Method for initializing variables and taking keyboard commands.
     */
    public final void execute(final boolean flag) {
        System.out.println(WELCOME_MESSAGE);
        if (flag) {
            this.help();
        } else {
            System.out.println(HELP_TIP);
        }
        this.scanner = new Scanner(System.in, "UTF-8");
        while (this.scanner.hasNextLine()) {
            this.executeCommand(Parser.parseInput(this.scanner.nextLine(), Command.PATTERNS));
        }
        this.scanner.close();
    }

    /**
     * Method for execute a command.
     */
    private void executeCommand(final Map<String, Map<Integer, String>> inputCommand) {
        Map<Command, List<String>> command = Command.parse(inputCommand);
        if (command == null) {
            System.out.println("Comando non riconosciuto");
        } else if (command.containsKey(Command.EXIT)) {
            this.closeGame();
        } else if (command.containsKey(Command.HELP)) {
            this.help();
        } else if (command.containsKey(Command.ATTEMPS)) {
            this.setOnlyCurrentTries(command.get(Command.ATTEMPS));
        } else if (command.containsKey(Command.EASY) || command.containsKey(Command.EASY_NOARG)) {
            this.setDifficulty(Command.EASY, command.get(Command.EASY));
        } else if (command.containsKey(Command.MEDIUM) || command.containsKey(Command.MEDIUM_NOARG)) {
            this.setDifficulty(Command.MEDIUM, command.get(Command.MEDIUM));
        } else if (command.containsKey(Command.HARD) || command.containsKey(Command.HARD_NOARG)) {
            this.setDifficulty(Command.HARD, command.get(Command.HARD));
        } else if (command.containsKey(Command.PLAY)) {
            this.playGame();
        } else if (command.containsKey(Command.SHOW_LEVEL)) {
            this.showLevel();
        } else if (command.containsKey(Command.REVAL_GRID)) {
            this.printCurrentGrid();
        } else if (command.containsKey(Command.SHOW_SHIPS)) {
            this.showShips();
        } else if (command.containsKey(Command.STANDARD)) {
            this.setSize(Command.STANDARD);
        } else if (command.containsKey(Command.LARGE)) {
            this.setSize(Command.LARGE);
        } else if (command.containsKey(Command.TIME)) {
            this.setMaxTime(command.get(Command.TIME));
        } else if (command.containsKey(Command.EXTRALARGE)) {
            this.setSize(Command.EXTRALARGE);
        } else {
            System.out.println("Comando non valido"); // stampa quando viene usato un comando che non può essere usato
        }
    }

    /**
     * Sets the maximum time for the game timer based on the provided arguments.
     *
     * @param args the list of arguments containing the maximum time.
     */
    private void setMaxTime(final List<String> args) {
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
    }

    /**
     * Method to set size of grid based on the specified command.
     *
     * @param command the command specifying the new size of the grid
     */
    private void setSize(final Command command) {
        if (!TimerPartita.isRunning()) {
            SizeGrid.setSize(SizeGrid.valueOf(command.toString()));
            System.out.println("OK");
        } else {
            System.out.println("Partita in corso!");
        }
    }

    /**
     * Method for close the application.
     */
    private void closeGame() {
        System.out.println("Chiudere il gioco? [s/n]");
        if (confirm()) {
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
            command = Command.parse(Parser.parseInput(this.scanner.nextLine(), Command.PATTERNS));
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
     * Method for change difficulty and the current tries if passed.
     */
    private void setDifficulty(final Command command, final List<String> args) {
        if (args == null) {
            this.setOnlyDifficulty(command);
            System.out.println("OK");
        } else {
            this.setOnlyDifficulty(command);
            this.setOnlyCurrentTries(args);
        }
    }

    /**
     * Method for change only the difficulty.
     */
    private void setOnlyDifficulty(final Command command) {
        Difficulty.setDifficulty(Difficulty.valueOf(command.toString()));
    }

    /**
     * Sets the maximum number of tries based on the provided arguments.
     *
     * @param args the list of arguments containing the maximum number of tries
     */
    private void setOnlyCurrentTries(final List<String> args) {
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
    }

    /**
     * Method for displaying the level and the maximum number of tries available to
     * the user.
     */
    private void showLevel() {
        System.out.print("Livello di difficoltà: " + Difficulty.getDifficulty().toString());
        System.out.println(", numero massimo di tentativi: " + Difficulty.getMaxTries());
    }

    /**
     * Method to display command help.
     */
    private void help() {
        System.out.println("ELENCO DEI COMANDI:");
        for (Command command : Command.values()) {
            String description = command.getDescription();
            if (!description.isEmpty()) {
                System.out.println(command.getNames()[0] + " : " + description);
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
     * Method to start a game.
     */
    private void playGame() {
        Coordinate coordinate;
        String input;
        int tries = Difficulty.getMaxTries();
        Difficulty.setCurrentTries(tries);
        TimerPartita timer = new TimerPartita();
        this.grid = new Grid();
        this.grid.generateGrid();
        this.grid.printCurrentGrid();
        timer.startGame();

        input = scanner.nextLine();
        while (TimerPartita.isRunning() && Difficulty.getCurrentTries() > 0) {
            coordinate = Coordinate.parse(Parser.parseInput(input, Coordinate.PATTERN));
            if (coordinate != null) {
                if (coordinate.isValid()) {
                    this.grid.printCurrentGrid();
                    System.out.print("Partita> ");
                    System.out.println(grid.hitCoordinate(coordinate));
                } else {
                    System.out.println("Coordinata non valida");
                }
            } else if (input != null) {
                this.executeCommand(Parser.parseInput(input, Command.PATTERNS));
            } else {
                System.out.println("Coordinata non riconosciuta");
            }
            System.out.println(Difficulty.getCurrentTries());
            if (Difficulty.getCurrentTries() <= 0) {
                System.out.println("Hai finito i tentativi a disposizione!");
                TimerPartita.setRunning(false);
                TimerPartita.stopTimer();
            }
            input = scanner.nextLine();
        }
        if (input != null) {
            if (input.contains("/")) {
                input = "/" + input.split("/")[1];
            }
            this.executeCommand(Parser.parseInput(input, Command.PATTERNS));
        }
    }

    /**
     * Method to display the grid with the ships if user is not in game.
     */
    private void printCurrentGrid() {
        if (TimerPartita.isRunning()) {
            this.grid.printGrid();
        } else {
            System.out.println("Non stai giocando, inizia a giocare con: /gioca");
        }
    }

    /**
     * Method to show current ships.
     */
    private void showShips() {
        if (TimerPartita.isRunning()) {
            this.grid.showShips();
        } else {
            System.out.print("E' necessario essere in partita per poter visualizzare l'elenco delle navi, ");
            System.out.println("inizia a giocare con /gioca");
        }
    }
}
