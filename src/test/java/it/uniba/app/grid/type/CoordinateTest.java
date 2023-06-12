package it.uniba.app.grid.type;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Coordinate class.
 */
class CoordinateTest {
    private static final Column COLUMN = Column.B;
    private static final int ROW = 5;
    private static final int MAX_ROW = 26;

    /**
     * Tests the constructor of the Coordinate class with a specified column and
     * row.
     */
    @Test
    void testConstructorColumn() {
        Coordinate coordinate = new Coordinate(COLUMN, ROW);
        assertEquals(COLUMN, coordinate.getColumn(), "The column must be B");
    }

    /**
     * Tests the constructor of the Coordinate class with a specified column and
     * row.
     */
    @Test
    void testConstructorRow() {
        Coordinate coordinate = new Coordinate(COLUMN, ROW);
        assertEquals(ROW, coordinate.getRow(), "The row must be 5");
    }

    /**
     * Tests the empty constructor of the Coordinate class, which initializes the
     * column to A and the row to 0.
     */
    @Test
    void testEmptyConstructorColumn() {
        Coordinate coordinate = new Coordinate();
        assertEquals(Column.A, coordinate.getColumn(), "The column must be A");
    }

    /**
     * Tests the empty constructor of the Coordinate class, which initializes the
     * column to A and the row to 0.
     */
    @Test
    void testEmptyConstructorRow() {
        Coordinate coordinate = new Coordinate();
        assertEquals(0, coordinate.getRow(), "The row must be 0");
    }

    /**
     * Tests the setRow method of the Coordinate class to ensure it sets the row
     * correctly.
     */
    @Test
    void testSetRow() {
        Coordinate coordinate = new Coordinate();
        int row = 2;
        coordinate.setRow(row);
        assertEquals(row, coordinate.getRow(), "The row must be 2");
    }

    /**
     * Tests the setColumn method of the Coordinate class to ensure it sets the
     * column correctly.
     */
    @Test
    void testSetColumn() {
        Coordinate coordinate = new Coordinate();
        Column column = Column.C;
        coordinate.setColumn(column);
        assertEquals(column, coordinate.getColumn(), "The column must be C");
    }

    /**
     * Tests the equals method of the Coordinate class with two coordinates having
     * the same column and row values.
     * The two coordinates should be considered equal.
     */
    @Test
    void testEqualsSameValue() {
        Coordinate coordinate1 = new Coordinate(COLUMN, ROW);
        Coordinate coordinate2 = new Coordinate(COLUMN, ROW);
        assertEquals(coordinate1, coordinate2, "The two coordinates must be equal");
    }

    /**
     * Tests the equals method of the Coordinate class with two coordinates having
     * different row values.
     * The two coordinates should be considered different.
     */
    @Test
    void testEqualsDifferentValue() {
        Coordinate coordinate1 = new Coordinate(COLUMN, ROW);
        Coordinate coordinate2 = new Coordinate(COLUMN, ROW + 1);
        assertNotEquals(coordinate1, coordinate2, "The two coordinates must be different");
    }

    /**
     * Tests the generateRandomColumn method of the Coordinate class to ensure it
     * generates a random column within the specified range.
     */
    @Test
    void testGenerateRandomColumn() {
        int nMaxColumn = SizeGrid.getSize();
        Column column = Coordinate.generateRandomColumn(nMaxColumn);
        assertTrue(column.getColumnInt() >= 0 && column.getColumnInt() < nMaxColumn,
                "The column must be between 0 and " + nMaxColumn);
    }

    /**
     * Tests the generateRandomRow method of the Coordinate class to ensure it
     * generates a random row within the specified range.
     */
    @Test
    void testGenerateRandomRow() {
        int nMaxRow = SizeGrid.getSize();
        int row = Coordinate.generateRandomRow(nMaxRow);
        assertTrue(row >= 0 && row < nMaxRow, "The row must be between 0 and " + nMaxRow);
    }

    /**
     * Tests the random method of the Coordinate class to ensure it generates a
     * random coordinate within the specified range.
     */
    @Test
    void testRandomNotNull() {
        int nMaxColumn = SizeGrid.getSize();
        int nMaxRow = SizeGrid.getSize();
        Coordinate coordinate = Coordinate.random(nMaxColumn, nMaxRow);
        assertNotNull(coordinate, "The coordinate must not be null");
    }

    /**
     * Tests the parse method of the Coordinate class with a valid input pattern and
     * data map.
     * The method should parse the input and return a coordinate object.
     */
    @Test
    void testParseValidInputNotNull() {
        Map<String, Map<Integer, String>> input = new HashMap<>();
        Map<Integer, String> data = new HashMap<>();
        data.put(0, "A");
        data.put(1, "1");
        input.put("^([a-z])-(1[0-9]|2[0-6]|[1-9])$", data);
        Coordinate coordinate = Coordinate.parse(input);
        assertNotNull(coordinate, "The coordinate must not be null");
    }

    /**
     * Tests the parse method of the Coordinate class with a valid input pattern and
     * data map.
     * The method should parse the input and return a coordinate object with the
     * specified column.
     */
    @Test
    void testParseValidInputEqualColumn() {
        Map<String, Map<Integer, String>> input = new HashMap<>();
        Map<Integer, String> data = new HashMap<>();
        data.put(0, "C");
        data.put(1, "6");
        input.put("^([a-z])-(1[0-9]|2[0-6]|[1-9])$", data);
        Coordinate coordinate = Coordinate.parse(input);
        assertEquals(Column.C, coordinate.getColumn(), "The column must be C");
    }

    /**
     * Tests the parse method of the Coordinate class with a valid input pattern and
     * data map.
     * The method should parse the input and return a coordinate object with the
     * specified row.
     */
    @Test
    void testParseValidInputEqualRow() {
        Map<String, Map<Integer, String>> input = new HashMap<>();
        Map<Integer, String> data = new HashMap<>();
        data.put(0, "D");
        data.put(1, "5");
        input.put("^([a-z])-(1[0-9]|2[0-6]|[1-9])$", data);
        Coordinate coordinate = Coordinate.parse(input);
        assertEquals(ROW, coordinate.getRow(), "The row must be 5");
    }

    /**
     * Tests the parse method of the Coordinate class with an invalid input pattern
     * and data map.
     * The method should return null since the input is not valid.
     */
    @Test
    void testParseInvalidInput() {
        Map<String, Map<Integer, String>> input = new HashMap<>();
        Map<Integer, String> data = new HashMap<>();
        data.put(0, "A");
        data.put(1, "27");
        input.put("^([a-z])-(1[0-9]|2[0-6]|[1-9])$", data);
        Coordinate coordinate = Coordinate.parse(input);
        assertNull(coordinate, "The coordinate must be null");
    }

    /**
     * Tests the isValid method of the Coordinate class with a valid coordinate.
     * The method should return true.
     */
    @Test
    void testIsValidValidCoordinate() {
        SizeGrid.setSize(SizeGrid.EXTRALARGE);
        Coordinate coordinate = new Coordinate(Column.Z, MAX_ROW);
        assertTrue(coordinate.isValid(), "The coordinate must be valid");
    }

    /**
     * Tests the isValid method of the Coordinate class with an invalid row value.
     * The method should return false.
     */
    @Test
    void testIsValidInvalidRow() {
        Coordinate coordinate = new Coordinate(Column.A, SizeGrid.getSize() + 1);
        assertFalse(coordinate.isValid(), "The coordinate must be invalid");
    }

    /**
     * Tests the copy method of the Coordinate class to ensure it creates a copy of
     * the coordinate.
     * The copy should be equal to the original coordinate.
     */
    @Test
    void testCopy() {
        Coordinate coordinate = new Coordinate(COLUMN, ROW);
        Coordinate copy = coordinate.copy();
        assertEquals(coordinate, copy, "The two coordinates must be equal");
    }

    /**
     * Tests the getColumn method of the Coordinate class to ensure it returns the
     * correct column.
     */
    @Test
    void testGetColumn() {
        Coordinate coordinate = new Coordinate(COLUMN, ROW);
        assertEquals(COLUMN, coordinate.getColumn(), "The column must be B");
    }

    /**
     * Tests the getRow method of the Coordinate class to ensure it returns the
     * correct row.
     */
    @Test
    void testGetRow() {
        int row = ROW + 1;
        Coordinate coordinate = new Coordinate(COLUMN, row);
        assertEquals(row, coordinate.getRow(), "The row must be 6");
    }
}
