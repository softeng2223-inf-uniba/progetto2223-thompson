package it.uniba.app.grid.type;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Column enum.
 */
public class ColumnTest {
    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 25;
    private static final int INVALID_MIN_VALUE = -1;
    private static final int INVALID_MAX_VALUE = 26;

    /**
     * Test converting the minimum valid column number to a Column enum value.
     */
    @Test
    void testFromIntMinValue() {
        assertEquals(Column.A, Column.fromInt(MIN_VALUE), "The column must be A");
    }

    /**
     * Test converting the maximum valid column number to a Column enum value.
     */
    @Test
    void testFromIntValidMaxValue() {
        assertEquals(Column.Z, Column.fromInt(MAX_VALUE), "The column must be Z");
    }

    /**
     * Test converting a column number less than the minimum valid value.
     * The result should be null.
     */
    @Test
    void testFromIntLesserMinValue() {
        assertNull(Column.fromInt(INVALID_MIN_VALUE), "The column number -1 must be null");
    }

    /**
     * Test converting a column number greater than the maximum valid value.
     * The result should be null.
     */
    @Test
    void testFromIntGreaterMaxValue() {
        assertNull(Column.fromInt(INVALID_MAX_VALUE), "The column number 26 must be null");
    }

    /**
     * Test converting a column number equal to the minimum valid value.
     */
    @Test
    void testGetColumnIntMin() {
        assertEquals(MIN_VALUE, Column.A.getColumnInt(), "The column number must be 0");
    }

    /**
     * Test converting a column number equal to the maximum valid value.
     */
    @Test
    void testGetColumnIntMax() {
        assertEquals(MAX_VALUE, Column.Z.getColumnInt(), "The column number must be 25");
    }
}
