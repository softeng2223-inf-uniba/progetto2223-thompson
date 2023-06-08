package it.uniba.app.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Map;

/**
 * Class to parse command.
 */
public abstract class Parser {
    /**
     * Method fo convert string to command.
     *
     * @param input Command string
     * @return Map with Command as key and its list of argument as value
     */
    public static Map<String, Map<Integer, String>> parseInput(final String input, final Pattern... patterns) {
        try {
            Map<String, Map<Integer, String>> result = new HashMap<>();
            for (Pattern pattern : patterns) {
                Matcher matcher = pattern.matcher(input);
                if (matcher.matches()) {
                    result.put(pattern.pattern(), new HashMap<>());
                    if (matcher.groupCount() == 0) {
                        result.get(pattern.pattern()).put(0, matcher.group());
                    } else {
                        for (int i = 1; i <= matcher.groupCount(); i++) {
                            result.get(pattern.pattern()).put(i - 1, matcher.group(i));
                        }
                    }
                }
            }
            return result.isEmpty() ? null : result;
        } catch (Exception e) {
            return null;
        }
    }
}
