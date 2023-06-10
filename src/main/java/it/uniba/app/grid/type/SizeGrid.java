package it.uniba.app.grid.type;

/**
 * <Entity>
 * 
 * Represents the size of a grid.
 */
public enum SizeGrid {
    STANDARD(10),
    LARGE(18),
    EXTRALARGE(26);

    private final int size;

    private static SizeGrid currentSize = SizeGrid.STANDARD;

    /**
     * Constructs a SizeGrid object with the specified size.
     *
     * @param valSize the size value of the grid
     */
    SizeGrid(final int valSize) {
        this.size = valSize;
    }

    /**
     * Returns the current size of the grid.
     *
     * @return the current size of the grid
     */
    public static int getSize() {
        return currentSize.size;
    }

    /**
     * Sets the size of the grid to the specified value.
     *
     * @param valSize the new size of the grid
     */
    public static void setSize(final SizeGrid valSize) {
        currentSize = valSize;
    }
}
