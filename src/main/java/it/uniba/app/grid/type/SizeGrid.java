package it.uniba.app.grid.type;

/**
 * Enum class to describe the dimensions of the grid.
 */
public enum SizeGrid {
    STANDARD(10),
    LARGE(18),
    EXTRALARGE(26);

    private final int size;

    private static SizeGrid currentSize = SizeGrid.STANDARD;

    SizeGrid(final int valSize) {
        this.size = valSize;
    }

    public static int getSize() {
        return currentSize.size;
    }

    public static void setSize(final SizeGrid valSize) {
        currentSize = valSize;
    }
}
