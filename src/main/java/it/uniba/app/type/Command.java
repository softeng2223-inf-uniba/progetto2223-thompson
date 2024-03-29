package it.uniba.app.type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * <noECB>
 *
 * The Command class represents different commands that the user can enter. It
 * is an enumeration that contains various commands along with their
 * descriptions, aliases, and types.
 */
public enum Command {
    /**
     * Displays the commands available to the user and their descriptions.
     */
    HELP("Visualizza l'elenco dei comandi", TypeCommand.NOARG, "/help"),
    /**
     * Start a new game.
     */
    PLAY("Inizia una nuova partita", TypeCommand.NOARG, "/gioca"),
    /**
     * Set the difficulty to easy and set attempts.
     */
    EASY("Cambia la difficoltà in facile ed imposta a [numero] il numero max di tentativi falliti", TypeCommand.NUMERO,
            "/facile"),
    /**
     * Set the difficulty to easy.
     */
    EASY_NOARG("Cambia la difficoltà in facile ed imposta a 50 il numero max di tentativi falliti (\u00E8 il default)",
            TypeCommand.NOARG, "/facile"),
    /**
     * Set the difficulty to medium and set attempts.
     */
    MEDIUM("Cambia la difficoltà in medio ed imposta a [numero] il numero max di tentativi falliti", TypeCommand.NUMERO,
            "/medio"),
    /**
     * Set the difficulty to medium.
     */
    MEDIUM_NOARG("Cambia la difficoltà in medio ed imposta a 30 il numero max di tentativi falliti (\u00E8 il default)",
            TypeCommand.NOARG, "/medio"),
    /**
     * Set the difficulty to hard and set attempts.
     */
    HARD("Cambia la difficoltà in difficile ed imposta a [numero] il numero max di tentativi falliti",
            TypeCommand.NUMERO, "/difficile"),
    /**
     * Set the difficulty to hard.
     */
    HARD_NOARG("Cambia la difficoltà in difficile ed imposta a 10 il numero max di tentativi falliti",
            TypeCommand.NOARG, "/difficile"),
    /**
     * Set the max number of tries.
     */
    ATTEMPS("Imposta a [numero] il numero max di tentativi falliti", TypeCommand.NUMERO, "/tentativi"),
    /**
     * Show the current difficulty.
     */
    SHOW_LEVEL("Visualizza livello e numero max di tentativi falliti", TypeCommand.NOARG, "/mostralivello"),
    /**
     * Show the number of remaining ships.
     */
    SHOW_SHIPS("Visualizza navi da affondare", TypeCommand.NOARG, "/mostranavi"),
    /**
     * Display the grid with ship position.
     */
    REVEAL_GRID("Visualizza la griglia con le navi posizionate", TypeCommand.NOARG, "/svelagriglia"),
    /**
     * Close the application.
     */
    EXIT("Termina l'applicazione", TypeCommand.NOARG, "/esci"),
    /**
     * Confirm command.
     */
    YES("", TypeCommand.CONFIRM, "y", "yes", "si", "s"),
    /**
     * Reject command.
     */
    NO("", TypeCommand.DECLINE, "n", "no"),
    /**
     * Standard size command.
     */
    STANDARD("Imposta a 10x10 la dimensione della griglia (è il default)", TypeCommand.NOARG, "/standard"),
    /**
     * Large size command.
     */
    LARGE("Imposta a 18x18 la dimensione della griglia", TypeCommand.NOARG, "/large"),
    /**
     * Extralarge size command.
     */
    EXTRALARGE("Imposta a 26x26 la dimensione della griglia", TypeCommand.NOARG, "/extralarge"),
    /**
     * Time set command.
     */
    TIME("Imposta la durata della partita in minuti", TypeCommand.NUMERO, "/tempo"),
    /**
     * Show attemps set command.
     */
    SHOW_ATTEMPS("Visualizza il numero di tentativi rimanenti", TypeCommand.NOARG, "/mostratentativi"),
    /**
     * Display the grid with ship position.
     */
    SHOW_GRID("Visualizza la griglia con le navi affondate e le sole parti già colpite delle navi non affondate",
            TypeCommand.NOARG, "/mostragriglia"),
    /**
     * Show attemps set command.
     */
    SHOW_TIME("Visualizza il tempo rimanente", TypeCommand.NOARG, "/mostratempo"),
    /**
     * Surrend set command.
     */
    SURREND("Abbandona la partita", TypeCommand.NOARG, "/abbandona");

    /**
     * Description of what the command should do.
     */
    private final String description;
    /**
     * Command alias.
     */
    private final String[] names;
    /**
     * Command Type.
     */
    private final TypeCommand type;

    /**
     * This array contains command values, is used as a buffer so as not to recreate
     * it each time.
     */
    private static final Command[] VALUES = Command.values();

    private static final Map<String, List<String>> TYPE_COMMANDS = new HashMap<>();

    private static Pattern[] patterns;

    static {
        patterns = new Pattern[TypeCommand.VALUES.length];
        for (TypeCommand type : TypeCommand.VALUES) {
            patterns[type.ordinal()] = Pattern.compile(type.getRegex(), Pattern.CASE_INSENSITIVE);
            TYPE_COMMANDS.put(type.getRegex(), new LinkedList<>());
        }
        for (Command command : VALUES) {
            TYPE_COMMANDS.get(command.type.getRegex()).addAll(Arrays.asList(command.getNames()));
        }
    }

    /**
     * Constructor of the class.
     *
     * @param valDescription Description of command
     * @param valNames       String array with alias
     */
    Command(final String valDescription, final TypeCommand valType, final String... valNames) {
        this.names = valNames;
        this.description = valDescription;
        this.type = valType;
    }

    /**
     * Method to convert a string to Command.
     *
     * @param text string to conver
     * @return Command type
     */
    public static Command fromString(final String text, final String regex) {
        for (Command b : VALUES) {
            if (Arrays.asList(b.getNames()).contains(text) && b.type.getRegex().equals(regex)) {
                return b;
            }
        }
        return null;
    }

    /**
     * Returns the array of patterns used by the Command class.
     *
     * @return the array of patterns
     */
    public static Pattern[] getPatterns() {
        return patterns.clone();
    }

    /**
     * Names getter.
     *
     * @return string array with alias of the command.
     */
    public String[] getNames() {
        return this.names.clone();
    }

    /**
     * Description getter.
     *
     * @return destription
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Get a lowercase string representation of the type.
     *
     * @return the lowercase string representation of the type
     */
    public String getTypeToString() {
        return this.type.toString().toLowerCase();
    }

    /**
     * Get the maximum number of arguments allowed for the type.
     *
     * @return the maximum number of arguments allowed for the type
     */
    public int getMaxArgs() {
        return this.type.getMaxArgs();
    }

    /**
     * Parses the input map and returns a map of commands with their corresponding
     * arguments.
     *
     * @param input the input map to parse
     * @return a map of commands with their arguments, or null if the input is null
     *         or the parsing fails
     */
    public static Map<Command, List<String>> parse(final Map<String, Map<Integer, String>> input) {
        Map<Command, List<String>> result = new HashMap<>();
        if (input != null) {
            for (TypeCommand type : TypeCommand.VALUES) {
                if (input.containsKey(type.getRegex())) {
                    Command value = Command.fromString(input.get(type.getRegex()).remove(0).toLowerCase(),
                            type.getRegex());
                    if (value != null) {
                        if (input.get(type.getRegex()).size() == value.type.getMaxArgs()) {
                            result.put(value, new ArrayList<>(input.get(type.getRegex()).values()));
                            return result;
                        }
                    }
                }
            }
            return null;
        }
        return null;
    }

    /**
     * Enum for different types of commands.
     */
    private enum TypeCommand {
        CONFIRM("y|yes|si|s", 0),
        DECLINE("n|no", 0),
        NOARG("(/[a-z]+)", 0),
        NUMERO("(/[a-z]+)\\h[\\h]*([-|+]*[0-9][0-9]*)", 1);

        private final String regex;
        private final int maxArgs;

        public static final TypeCommand[] VALUES = TypeCommand.values();

        /**
         * Constructor of the enum.
         *
         * @param valRegex   regex pattern for the command type
         * @param valMaxArgs maximum number of arguments for the command type
         */
        TypeCommand(final String valRegex, final int valMaxArgs) {
            this.regex = valRegex;
            this.maxArgs = valMaxArgs;
        }

        /**
         * Regex getter.
         *
         * @return regex pattern for the command type
         */
        public String getRegex() {
            return this.regex;
        }

        /**
         * MaxArgs getter.
         *
         * @return maximum number of arguments for the command type
         */
        public int getMaxArgs() {
            return this.maxArgs;
        }
    }
}
