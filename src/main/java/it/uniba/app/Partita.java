package it.uniba.app;

import it.uniba.app.grid.Grid;
import it.uniba.app.parser.Parser;
import it.uniba.app.type.Command;
import it.uniba.app.type.Difficulty;
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
     * Difficulty of the current match.
     */
    private Difficulty gameDifficulty;

    /**
     * Flag that indicates if a game is in progress.
     */
    private boolean isInGame;

    /**
     * Welcome message that prints when the program starts correctly.
     */
    private static final String WELCOME_MESSAGE = ""
            + "\n=========================================================================================\n"
            + "______       _   _   _           _     _       "
            + "| ___ \\     | | | | | |         | |   (_)      "
            + "| |_/ / __ _| |_| |_| | ___  ___| |__  _ _ __  "
            + "| ___ \\/ _` | __| __| |/ _ \\/ __| '_ \\| | '_ \\ "
            + "| |_/ / (_| | |_| |_| |  __/\\__ \\ | | | | |_) |"
            + "\\____/ \\__,_|\\__|\\__|_|\\___||___/_| |_|_| .__/ "
            + "                                        | |    "
            + "                                        |_|    "
            + "\n=========================================================================================\n"
            + "BENVENUTO/A NELLA BATTAGLIA NAVALE\n"
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
     * Method for starting a game.
     */
    public final void execute(final boolean flag) {
        this.grid = new Grid();
        System.out.println(WELCOME_MESSAGE);
        if (flag) {
            this.help();
        } else {
            System.out.println(HELP_TIP);
        }
        this.gameDifficulty = Difficulty.MEDIUM; // default difficulty
        scanner = new Scanner(System.in, "UTF-8");
        while (scanner.hasNextLine()) {
            this.executeCommand(Parser.parse(scanner.nextLine()));
        }
        scanner.close();
    }

    /**
     * Method for execute a command.
     */
    private void executeCommand(final Map<Command, List<String>> command) {
        if (command == null) {
            System.out.println("Comando non riconosciuto");
        } else if (command.containsKey(Command.EXIT) && command.get(Command.EXIT).isEmpty()) {
            this.closeGame();
        } else if (command.containsKey(Command.EASY) && command.get(Command.EASY).isEmpty()) {
            this.setDifficulty(Command.EASY);
        } else if (command.containsKey(Command.HELP) && command.get(Command.HELP).isEmpty()) {
            this.help();
        } else if (command.containsKey(Command.MEDIUM) && command.get(Command.MEDIUM).isEmpty()) {
            this.setDifficulty(Command.MEDIUM);
        } else if (command.containsKey(Command.HARD) && command.get(Command.HARD).isEmpty()) {
            this.setDifficulty(Command.HARD);
        } else if (command.containsKey(Command.PLAY) && command.get(Command.PLAY).isEmpty()) {
            this.playGame();
        } else if (command.containsKey(Command.SHOW_LEVEL) && command.get(Command.SHOW_LEVEL).isEmpty()) {
            this.showLevel();
        } else if (command.containsKey(Command.REVAL_GRID) && command.get(Command.REVAL_GRID).isEmpty()) {
            this.printCurrentGrid();
        } else if (command.containsKey(Command.SHOW_SHIPS) && command.get(Command.SHOW_SHIPS).isEmpty()) {
            this.showShips();
        } else {
            System.out.println("Comando non valido");
        }
    }

    /**
     * Method for close the application.
     */
    private void closeGame() {
        System.out.println("Chiudere il gioco? [s/n]");
        if (scanner.hasNextLine()) {
            Map<Command, List<String>> command = Parser.parse(scanner.nextLine());
            if (command == null) {
                System.out.println("Comando non riconosciuto");
            } else if (command.containsKey(Command.YES) && command.get(Command.YES).isEmpty()) {
                Runtime.getRuntime().exit(0);
            } else if (command.containsKey(Command.NO) && command.get(Command.NO).isEmpty()) {
                System.out.println("Ok");
            } else {
                System.out.println("Comando non valido");
            }
        }
    }

    /**
     * Method for change difficulty.
     */
    private void setDifficulty(final Command command) {
        if (command == Command.EASY) {
            this.gameDifficulty = Difficulty.EASY;
        } else if (command == Command.MEDIUM) {
            this.gameDifficulty = Difficulty.MEDIUM;
        } else if (command == Command.HARD) {
            this.gameDifficulty = Difficulty.HARD;
        }
        System.out.println("OK");
    }

    /**
     * this method display level and maximum tries to users.
     */
    private void showLevel() {
        System.out.print("Livello di difficoltà: " + this.gameDifficulty.toString());
        System.out.println(", numero massimo di tentativi: " + this.gameDifficulty.getTries());
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
        this.grid = new Grid();
        this.grid.generateGrid();
        this.isInGame = true;
    }

    /**
     * Method to display the grid with the ships if user is not in game.
     */
    private void printCurrentGrid() {
        if (this.isInGame) {
            grid.printGrid();
        } else {
            System.out.println("Non stai giocando, inizia a giocare con: /gioca");
        }
    }

    /**
     * Method to show current ships.
     */
    private void showShips() {
        if (this.isInGame) {
            this.grid.showShips();
        } else {
            System.out.print("E' necessario essere in partita per poter visualizzare l'elenco delle navi, ");
            System.out.println("inizia a giocare con /gioca");
        }
    }
}
