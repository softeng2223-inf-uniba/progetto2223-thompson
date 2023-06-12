package it.uniba.app.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Parser class.
 */
class ParserTest {
    private static final String INPUT1 = "Hello";
    private static final String INPUT2 = "World";
    private static final Pattern PATTERN1 = Pattern.compile("Hello", Pattern.CASE_INSENSITIVE);
    private static final Pattern PATTERN2 = Pattern.compile("World", Pattern.CASE_INSENSITIVE);

    /**
     * Tests the parseInput method to ensure the result is not null.
     */
    @Test
    void testParseInputNotNull() {
        Map<String, Map<Integer, String>> result = Parser.parseInput(INPUT1, PATTERN1);
        assertNotNull(result, "The result must not be null");
    }

    /**
     * Tests the parseInput method to ensure the expected match is present in the
     * result.
     */
    @Test
    void testParseInputMatch() {
        Map<String, Map<Integer, String>> result = Parser.parseInput(INPUT1, PATTERN1);
        assertTrue(result.containsKey("Hello") && result.get("Hello").containsValue("hello"),
                "The result must contain the key Hello and the value hello");
    }

    /**
     * Tests the parseInput method when a single pattern is provided.
     */
    @Test
    void testParseInputSinglePattern() {
        Map<String, Map<Integer, String>> result = Parser.parseInput(INPUT1, PATTERN1);
        assertEquals("hello", result.get("Hello").get(0), "The result must contain the value hello");
    }

    /**
     * Tests the parseInput method when multiple patterns are provided.
     */
    @Test
    void testParseInputMultiplePatterns() {
        String input = INPUT1 + " " + INPUT2;
        Pattern pattern3 = Pattern.compile("Hello World", Pattern.CASE_INSENSITIVE);
        Map<String, Map<Integer, String>> result = Parser.parseInput(input, PATTERN1, PATTERN2, pattern3);
        assertTrue(result.containsKey(pattern3.pattern()),
                "The result must contain the key Hello World and the value hello world");

    }

    /**
     * Tests the parseInput method when there is no match.
     */
    @Test
    void testParseInputNoMatch() {
        Pattern pattern = Pattern.compile("Goodbye", Pattern.CASE_INSENSITIVE);
        Map<String, Map<Integer, String>> result = Parser.parseInput(INPUT1, pattern);

        assertNull(result, "The result must be null");
    }

    /**
     * Tests the parseInput method when an exception occurs during pattern matching.
     */
    @Test
    void testParseInputException() {
        Pattern pattern = Pattern.compile("[0-9]*", Pattern.CASE_INSENSITIVE);
        Map<String, Map<Integer, String>> result = Parser.parseInput(INPUT1, pattern);
        assertNull(result, "The result must be null");
    }
}
