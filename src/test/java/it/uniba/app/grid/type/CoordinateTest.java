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

public class CoordinateTest {
    private static final Column COLUMN = Column.B;
    private static final int ROW = 5;
    private static final int MAX_ROW = 26;

    @Test
    void testConstructorColumn() {
        Coordinate coordinate = new Coordinate(COLUMN, ROW);
        assertEquals(COLUMN, coordinate.getColumn(), "The column must be B");
    }

    @Test
    void testConstructorRow() {
        Coordinate coordinate = new Coordinate(COLUMN, ROW);
        assertEquals(ROW, coordinate.getRow(), "The row must be 5");
    }

    @Test
    void testEmptyConstructorColumn() {
        Coordinate coordinate = new Coordinate();
        assertEquals(Column.A, coordinate.getColumn(), "The column must be A");
    }

    @Test
    void testEmptyConstructorRow() {
        Coordinate coordinate = new Coordinate();
        assertEquals(0, coordinate.getRow(), "The row must be 0");
    }

    @Test
    void testSetRow() {
        Coordinate coordinate = new Coordinate();
        int row = 2;
        coordinate.setRow(row);
        assertEquals(row, coordinate.getRow(), "The row must be 2");
    }

    @Test
    void testSetColumn() {
        Coordinate coordinate = new Coordinate();
        Column column = Column.C;
        coordinate.setColumn(column);
        assertEquals(column, coordinate.getColumn(), "The column must be C");
    }

    @Test
    void testEqualsSameValue() {
        Coordinate coordinate1 = new Coordinate(COLUMN, ROW);
        Coordinate coordinate2 = new Coordinate(COLUMN, ROW);
        assertEquals(coordinate1, coordinate2, "The two coordinates must be equal");
    }

    @Test
    void testEqualsDifferentValue() {
        Coordinate coordinate1 = new Coordinate(COLUMN, ROW);
        Coordinate coordinate2 = new Coordinate(COLUMN, ROW + 1);
        assertNotEquals(coordinate1, coordinate2, "The two coordinates must be different");
    }

    @Test
    void testGenerateRandomColumn() {
        int nMaxColumn = SizeGrid.getSize();
        Column column = Coordinate.generateRandomColumn(nMaxColumn);
        assertTrue(column.getColumnInt() >= 0 && column.getColumnInt() < nMaxColumn,
                "The column must be between 0 and " + nMaxColumn);
    }

    @Test
    void testGenerateRandomRow() {
        int nMaxRow = SizeGrid.getSize();
        int row = Coordinate.generateRandomRow(nMaxRow);
        assertTrue(row >= 0 && row < nMaxRow, "The row must be between 0 and " + nMaxRow);
    }

    @Test
    void testRandomNotNull() {
        int nMaxColumn = SizeGrid.getSize();
        int nMaxRow = SizeGrid.getSize();
        Coordinate coordinate = Coordinate.random(nMaxColumn, nMaxRow);
        assertNotNull(coordinate);
    }

    @Test
    void testParseValidInputNotNull() {
        Map<String, Map<Integer, String>> input = new HashMap<>();
        Map<Integer, String> data = new HashMap<>();
        data.put(0, "A");
        data.put(1, "1");
        input.put("^([a-z])-(1[0-9]|2[0-6]|[1-9])$", data);
        Coordinate coordinate = Coordinate.parse(input);
        assertNotNull(coordinate, "The coordinate must not be null");
        assertEquals(Column.A, coordinate.getColumn());
        assertEquals(1, coordinate.getRow());
    }

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

    @Test
    void testIsValidValidCoordinate() {
        SizeGrid.setSize(SizeGrid.EXTRALARGE);
        Coordinate coordinate = new Coordinate(Column.Z, MAX_ROW);
        assertTrue(coordinate.isValid(), "The coordinate must be valid");
    }

    @Test
    void testIsValidInvalidRow() {
        Coordinate coordinate = new Coordinate(Column.A, SizeGrid.getSize() + 1);
        assertFalse(coordinate.isValid(), "The coordinate must be invalid");
    }

    @Test
    void testCopy() {
        Coordinate coordinate = new Coordinate(COLUMN, ROW);
        Coordinate copy = coordinate.copy();
        assertEquals(coordinate, copy, "The two coordinates must be equal");
    }

    @Test
    void testGetColumn() {
        Coordinate coordinate = new Coordinate(COLUMN, ROW);
        assertEquals(COLUMN, coordinate.getColumn(), "The column must be B");
    }

    @Test
    void testGetRow() {
        int row = ROW + 1;
        Coordinate coordinate = new Coordinate(COLUMN, row);
        assertEquals(row, coordinate.getRow(), "The row must be 6");
    }
}
