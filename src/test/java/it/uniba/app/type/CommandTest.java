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
public class CommandTest {
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
            assertNotNull(pattern);
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
        assertEquals("/mostralivello", names[0]);
    }

    /**
     * Tests the getDescription method to ensure it returns the correct description
     * for a command.
     */
    @Test
    void testGetDescription() {
        Command command = Command.SHOW_GRID;
        assertEquals("Visualizza la griglia con le navi affondate e le sole parti già colpite delle navi non affondate",
                command.getDescription());
    }

    /**
     * Tests the getTypeToString method to ensure it returns the correct type as a
     * string for a command.
     */
    @Test
    void testGetTypeToString() {
        Command command = Command.YES;
        assertEquals("confirm", command.getTypeToString());
    }

    /**
     * Tests the getMaxArgs method to ensure it returns the correct maximum number
     * of arguments for a command.
     */
    @Test
    void testGetMaxArgs() {
        Command command = Command.TIME;
        assertEquals(1, command.getMaxArgs());
    }

}
