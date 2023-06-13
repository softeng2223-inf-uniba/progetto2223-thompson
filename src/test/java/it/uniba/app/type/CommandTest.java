package it.uniba.app.type;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import it.uniba.app.parser.Parser;

/**
 * Unit tests for the Command class.
 */
class CommandTest {
    private static final String COMMAND = "/gioca";
    private static final String REGEX = "(/[a-z]+)";

    /**
     * Tests the fromString method with an empty string to ensure it returns null.
     */
    @Test
    void testFromStringNull() {
        Command command = Command.fromString("", REGEX);
        assertNull(command, "The command must be null");
    }

    /**
     * Tests the fromString method with a valid command string to ensure it returns
     * the corresponding command.
     */
    @Test
    void testFromStringGioca() {
        Command command = Command.fromString(COMMAND, REGEX);
        assertEquals(Command.PLAY, command, "The command must be PLAY");
    }

    /**
     * Tests the getPatterns method to ensure it returns non-null patterns.
     */
    @Test
    void testGetPatterns() {
        for (Pattern pattern : Command.getPatterns()) {
            assertNotNull(pattern, "The pattern must be non-null");
        }
    }

    /**
     * Tests the getNames method to ensure it returns the correct names for a
     * command.
     */
    @Test
    void testGetNames() {
        Command command = Command.SHOW_LEVEL;
        String[] names = command.getNames();
        assertEquals("/mostralivello", names[0], "The name must be /mostralivello");
    }

    /**
     * Tests the getDescription method to ensure it returns the correct description
     * for a command.
     */
    @Test
    void testGetDescription() {
        Command command = Command.SHOW_GRID;
        assertEquals("Visualizza la griglia con le navi affondate e le sole parti già colpite delle navi non affondate",
                command.getDescription(), "The description must be correct");
    }

    /**
     * Tests the getTypeToString method to ensure it returns the correct type as a
     * string for a command.
     */
    @Test
    void testGetTypeToString() {
        Command command = Command.YES;
        assertEquals("confirm", command.getTypeToString(), "The type must be confirm");
    }

    /**
     * Tests the getMaxArgs method to ensure it returns the correct maximum number
     * of arguments for a command.
     */
    @Test
    void testGetMaxArgs() {
        Command command = Command.TIME;
        assertEquals(1, command.getMaxArgs(), "The maximum number of arguments must be 1");
    }

    /**
     * Tests the parse method with a command that has no arguments to ensure it
     * parses correctly.
     */
    @Test
    void testParseNoArg() {
        Map<Command, List<String>> result = Command.parse(Parser.parseInput(COMMAND, Pattern.compile(REGEX)));
        assertTrue(result.containsKey(Command.PLAY) && result.get(Command.PLAY).isEmpty(), "The command must be PLAY");
    }

    /**
     * Tests the parse method with a command that has an argument to ensure it
     * parses correctly.
     */
    @Test
    void testParseArg() {
        String command = "/facile 500";
        String regex = "(/[a-z]+) ([-|+]*[0-9][0-9]*)";
        Map<Command, List<String>> result = Command.parse(Parser.parseInput(command, Pattern.compile(regex)));
        System.out.println(result);
        assertTrue(result.containsKey(Command.EASY) && result.get(Command.EASY).contains("500"),
                "The command must be EASY");
    }

    /**
     * Tests the parse method with a command that does not match the regex to ensure
     * it returns null.
     */
    @Test
    void testParseNull() {
        String command = "/facil 500";
        String regex = "(/[a-z]+) ([-|+]*[1-9][0-9]*)";
        Map<Command, List<String>> result = Command.parse(Parser.parseInput(command, Pattern.compile(regex)));
        assertNull(result, "The command must be null");
    }

}
