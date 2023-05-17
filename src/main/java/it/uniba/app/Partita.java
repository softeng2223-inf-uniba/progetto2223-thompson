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
    private Difficulty gameDifficulty ;

    /**
     * Flag that indicates if a game is in progress.
     */
    private boolean isInGame;

    /**
     * Welcome message that prints when the program starts correctly.
     */
    private static final String WELCOME_MESSAGE
            = "\n=========================================================================================\n"
            + "BENVENUTO/A NELLA BATTAGLIA NAVALE\n"
            + "In questo gioco, il sistema posizionerà le navi nemiche su una griglia.\n"
            + "Il tuo obiettivo sarà quello di indovinare la loro posizione e affondarle una per una.\n"
            + "Vincerai solo se riuscirai ad affondarle tutte prima di esaurire le tue mosse!\n"
            + "Il numero di mosse a disposizione dipende dalla modalità di gioco scelta.\n"
            + "Preparati a sfidare il sistema e dimostra le tue abilità strategiche per vincere.\n"
            + "Buona fortuna!\n"
            + "=========================================================================================\n";

    /**
     * Message suggesting how to view the list of available commands.
     */        
    private static final String HELP_TIP = "Puoi utilizzare /help per visualizzare l'elenco dei comandi\n";

    /**
     * Method for starting a game.
     */
    public final void execute() {
        System.out.println(WELCOME_MESSAGE);
        if (flag) {
            help();
        } else {
            System.out.println(HELP_TIP);
        }
        gameDifficulty = Difficulty.MEDIUM; //default difficulty
        Scanner scanner = new Scanner(System.in, "UTF-8");
        while (scanner.hasNextLine()) {
            executeCommand(Parser.parse(scanner.nextLine()));
        }
    }

    /**
     * Method for execute a command.
     */
    private void executeCommand(final Map<Command, List<String>> command) {
        if (command == null) {
            System.out.println("Comando non riconosciuto");
        } else if (command.containsKey(Command.EXIT) && command.get(Command.EXIT).isEmpty()) {
            closeGame();
        } else if (command.containsKey(Command.EASY) && command.get(Command.EASY).isEmpty()) {
            setDifficulty(Command.EASY);
        } else if (command.containsKey(Command.HELP) && command.get(Command.HELP).isEmpty()) {
            help();
        } else if (command.containsKey(Command.MEDIUM) && command.get(Command.MEDIUM).isEmpty()) {
            setDifficulty(Command.MEDIUM);
        } else if (command.containsKey(Command.HARD) && command.get(Command.HARD).isEmpty()) {
            setDifficulty(Command.HARD);
        } else if (command.containsKey(Command.SHOW_LEVEL) && command.get(Command.SHOW_LEVEL).isEmpty()) {
            showLevel();
        } else {
            System.out.println("Comando non valido");
        }
    }

    /**
     * Method for close the application.
     */
    private void closeGame() {
        Scanner scanner = new Scanner(System.in, "UTF-8");
        System.out.println("Chiudere il gioco? [Y/N]");
        if (scanner.hasNextLine()) {
            Map<Command, List<String>> p = Parser.parse(scanner.nextLine());
            if (p.containsKey(Command.YES)) {
                Runtime.getRuntime().exit(0);
            }
        }
    }

    /**
     * Method for change difficulty
     */
    private void setDifficulty(final Command command) {
        if (isInGame) {
            System.out.println("E' in corso una partita, non puoi cambiare difficoltà");
        } else {
            if (command == Command.EASY) {
                gameDifficulty = Difficulty.EASY;
            } else if (command == Command.MEDIUM) {
                gameDifficulty = Difficulty.MEDIUM;
            } else if (command == Command.HARD) {
                gameDifficulty = Difficulty.HARD;
            }
            System.out.println("OK");
        }
    }

    /**
     * this method display level and maximum tries to users.
     */
    private void showLevel(){
        System.out.print("Livello di difficoltà: "+gameDifficulty.toString());
        System.out.print(", numero massimo di tentativi: "+gameDifficulty.getTries());
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
     * @param flag The initial value of the flag.
     * @return The updated value of the flag based on the command line
     * arguments. If the flag is set to true, it means the help flag was
     * specified. If the flag is set to false, it means no help flag was
     * specified.
     * @throws RuntimeException If there are too many command line arguments or
     * an invalid flag is specified.
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
}
