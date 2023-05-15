package it.uniba.app.type;

import java.util.Arrays;

/**
 *
 * @author Utente
 */
public enum Command {
    HELP("1", "/help", "help", "--help", "-h"),
    EXIT("2", "/esci", "esci"),
    EASY("", "/facile", "facile"),
    MEDIUM("", "/medio", "medio"),
    HARD("", "/difficile", "difficile"),
    SHOW_LEVEL("", "/mostralivello", "mostralivello"),
    SHOW_SHIPS("", "/mostranavi", "mostranavi"),
    PLAY("", "/gioca", "gioca"),
    REVEAL_GRID("", "/svelagriglia", "svelagriglia"),
    YES("", "y", "yes", "si"),
    NO("", "n", "no");

    private final String description;
    private final String[] names;
    private static final Command[] VALUES = Command.values();

    Command(final String valDescription, final String... valNames) {
        this.names = valNames;
        this.description = valDescription;
    }

    public static Command fromString(final String text) {
        for (Command b : VALUES) {
            if (Arrays.asList(b.getNames()).contains(text)) {
                return b;
            }
        }
        return null;
    }

    public String[] getNames() {
        return names.clone();
    }

    public String getDescription() {
        return description;
    }
}
