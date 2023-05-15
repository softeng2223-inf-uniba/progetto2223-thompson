package it.uniba.app.parser;

import it.uniba.app.type.Command;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to parse command.
 */
public abstract class Parser {
    /**
     * Method to split the string into tokens.
     * @param command String to split
     * @return List with tokens
     */
    public static List<String> getTokens(final String command) {
        String[] arrayTokens = command.toLowerCase().split("\\s+");
        return Arrays.asList(arrayTokens);
    }

    /**
     * Method fo convert string to command.
     * @param command Command string
     * @return Map with Command as key and its list of argument as value
     */
    public static Map<Command, List<String>> parse(final String command) {
        Map<Command, List<String>> result = new HashMap<>();
        List<String> tokens = new ArrayList<>(getTokens(command));

        if (!tokens.isEmpty()) {
            String token = tokens.remove(0);
            Command value = Command.fromString(token);
            if (value != null) {
                result.put(value, tokens);
                return result;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}

