package it.uniba.app.type;

import java.util.Arrays;

/**
 * Class for commands that the user may enter.
 */
public enum Command {
    /**
     * Displays the commands available to the user and their descriptions.
     */
    HELP("Visualizza l'elenco dei comandi", "/help"),
    /**
     * Start a new game.
     */
    PLAY("Inizia una nuova partita", "/gioca"),
    /**
     * Set the difficulty to easy.
     */
    EASY("Imposta a 50 il numero max di tentativi falliti", "/facile"),
    /**
     * Set the difficulty to medium.
     */
    MEDIUM("Imposta a 30 il numero max di tentativi falliti", "/medio"),
    /**
     * Set the difficulty to hard.
     */
    HARD("Imposta a 10 il numero max di tentativi falliti", "/difficile"),
    /**
     * Show the current difficulty.
     */
    SHOW_LEVEL("Visualizza livello e numero max di tentativi falliti", "/mostralivello"),
    /**
     * Show the number of remaining ships.
     */
    SHOW_SHIPS("Visualizza navi da affondare", "/mostranavi"),
    /**
     * Display the grid with ship position.
     */
    REVAL_GRID("Visualizza la griglia con le navi posizionate", "/svelagriglia"),
    /**
     * Close the application.
     */
    EXIT("Termina l'applicazione", "/esci"),
    /**
     * Confirm command.
     */
    YES("", "y", "yes", "si"),
    /**
     * Reject command.
     */
    NO("", "n", "no");

    /**
     * Description of what the command should do.
     */
    private final String description;
    /**
     * Command alias.
     */
    private final String[] names;
    /**
     * This array contains command values, is used as a buffer so as not to recreate it each time.
     */
    private static final Command[] VALUES = Command.values();

    /**
     * Constructor of the class.
     * @param valDescription Description of command
     * @param valNames String array with alias
     */
    Command(final String valDescription, final String... valNames) {
        this.names = valNames;
        this.description = valDescription;
    }

    /**
     * Method to convert a string to Command.
     * @param text string to conver
     * @return Command type
     */
    public static Command fromString(final String text) {
        for (Command b : VALUES) {
            if (Arrays.asList(b.getNames()).contains(text)) {
                return b;
            }
        }
        return null;
    }

    /**
     * Names getter.
     * @return string array with alias of the command.
     */
    public String[] getNames() {
        return names.clone();
    }

    /**
     * Description getter.
     * @return destription
     */
    public String getDescription() {
        return description;
    }
}
