package it.uniba.app.grid.type;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SizeGridTest {
    private static final int STANDARD_SIZE = 10;
    private static final int LARGE_SIZE = 18;
    private static final int EXTRALARGE_SIZE = 26;

    @Test
    void testGetSizeStandardSize() {
        SizeGrid.setSize(SizeGrid.STANDARD);
        assertEquals(STANDARD_SIZE, SizeGrid.getSize(), "The size must be 10");
    }

    @Test
    void testGetSizeLargeSize() {
        SizeGrid.setSize(SizeGrid.LARGE);
        assertEquals(LARGE_SIZE, SizeGrid.getSize(), "The size must be 18");
    }

    @Test
    void testGetSizeExtraLargeSize() {
        SizeGrid.setSize(SizeGrid.EXTRALARGE);
        assertEquals(EXTRALARGE_SIZE, SizeGrid.getSize(), "The size must be 26");
    }
}
