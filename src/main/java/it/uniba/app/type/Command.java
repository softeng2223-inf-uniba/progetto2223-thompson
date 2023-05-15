package it.uniba.app.type;

import java.util.Arrays;

/**
 * Class for commands that the user may enter.
 */
public enum Command {
    /**
     * Displays the commands available to the user and their descriptions.
     */
    HELP("", "/help", "help", "--help", "-h"),
    /**
     * Close the application.
     */
    EXIT("", "/esci", "esci"),
    /**
     * Set the difficulty to easy.
     */
    EASY("", "/facile", "facile"),
    /**
     * Set the difficulty to medium.
     */
    MEDIUM("", "/medio", "medio"),
    /**
     * Set the difficulty to hard.
     */
    HARD("", "/difficile", "difficile"),
    /**
     * Show the current difficulty.
     */
    SHOW_LEVEL("", "/mostralivello", "mostralivello"),
    /**
     * Show the number of remaining ships.
     */
    SHOW_SHIPS("", "/mostranavi", "mostranavi"),
    /**
     * Start a new game.
     */
    PLAY("", "/gioca", "gioca"),
    /**
     * Display the grid with ship position.
     */
    REVEAL_GRID("", "/svelagriglia", "svelagriglia"),
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
