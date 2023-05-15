package it.uniba.app;

import it.uniba.app.grid.Grid;
import it.uniba.app.parser.Parser;
import it.uniba.app.type.Command;
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
     * Method for starting a game.
     */
    public final void execute() {
        System.out.println("================================");
        System.out.println("*   Battleship  -  2022/2023   *");
        System.out.println("================================");
        System.out.println();
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
}
