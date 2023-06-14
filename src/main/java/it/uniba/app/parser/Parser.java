package it.uniba.app.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Map;

/**
 * <noECB>
 *
 * The Parser class is an abstract class that provides functionality for parsing
 * strings using regular expression patterns.
 */
public abstract class Parser {
    /**
     * Parses the input string using the provided patterns and returns a map with
     * the matched results.
     *
     * @param input    the input string to parse
     * @param patterns the regex patterns to match against the input
     * @return a map with the matched patterns as keys and their corresponding
     *         groups as values,
     *         or null if no match is found or an exception occurs
     */
    public static Map<String, Map<Integer, String>> parseInput(final String input, final Pattern... patterns) {
        try {
            Map<String, Map<Integer, String>> result = new HashMap<>();
            for (Pattern pattern : patterns) {
                Matcher matcher = pattern.matcher(input.trim());
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
