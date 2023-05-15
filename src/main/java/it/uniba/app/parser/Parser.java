package it.uniba.app.parser;

import it.uniba.app.type.Command;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Utente
 */
public abstract class Parser {

    public static List<String> getTokens(final String command) {
        String[] arrayTokens = command.toLowerCase().split("\\s+");
        return Arrays.asList(arrayTokens);
    }

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

