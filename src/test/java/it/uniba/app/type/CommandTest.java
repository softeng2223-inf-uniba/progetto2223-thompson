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

}
