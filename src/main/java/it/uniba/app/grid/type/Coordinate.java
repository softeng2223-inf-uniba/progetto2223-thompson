package it.uniba.app.grid.type;

import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * <Entity>
 *
 * Class to handle coordinates.
 */
public final class Coordinate {
    /**
     * Used for generate random elements.
     */
    private static final Random RAND = new Random();

    public static final Pattern PATTERN = Pattern.compile("^([a-z])-(1[0-9]|2[0-6]|[1-9])$",
            Pattern.CASE_INSENSITIVE);
    /**
     * Contain the column.
     */
    private Column column;
    /**
     * Number of row.
     */
    private int row;
    /**
     * Hash constant.
     */
    private static final int HASH1 = 7;
    /**
     * Hash constant.
     */
    private static final int HASH2 = 61;

    /**
     * Coordinate constructor.
     *
     * @param valColumn Column type
     * @param valRow    integer of row
     */
    public Coordinate(final Column valColumn, final int valRow) {
        this.row = valRow;
        this.column = valColumn;
    }

    /**
     * Coordinate constructor.
     */
    public Coordinate() {
        this.column = Column.fromInt(0);
        this.row = 0;
    }

    /**
     * Row getter.
     *
     * @return row
     */
    public int getRow() {
        return row;
    }

    /**
     * Row setter.
     *
     * @param valRow row
     */
    public void setRow(final int valRow) {
        this.row = valRow;
    }

    /**
     * Column getter.
     *
     * @return column
     */
    public Column getColumn() {
        return column;
    }

    /**
     * Column getter.
     */
    public void setColumn(final Column valColumn) {
        this.column = valColumn;
    }

    /**
     * Method to generate hash code of objects.
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int hash = HASH1;
        hash = HASH2 * hash + Objects.hashCode(this.row);
        hash = HASH2 * hash + this.row;
        return hash;
    }

    /**
     * Method to compare two coordinates.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Coordinate other = (Coordinate) obj;
        if (this.column != other.column) {
            return false;
        }
        return this.row == other.row;
    }

    @Override
    public String toString() {
        return "Coordinate{" + "row=" + row + ", column=" + column + '}';
    }

    /**
     * Method to generate a random column.
     */
    public static Column generateRandomColumn(final int nMaxColumn) {
        return Column.fromInt(RAND.nextInt(nMaxColumn));
    }

    /**
     * Method to generate a random row.
     */
    public static int generateRandomRow(final int nMaxRow) {
        return RAND.nextInt(nMaxRow);
    }

    /**
     * Generates a random Coordinate within the specified maximum column and row
     * values.
     *
     * @param nMaxColumn the maximum value for the column
     * @param nMaxRow    the maximum value for the row
     * @return a randomly generated Coordinate
     */
    public static Coordinate random(final int nMaxColumn, final int nMaxRow) {
        Column column = generateRandomColumn(nMaxColumn);
        int row = generateRandomRow(nMaxRow);
        return new Coordinate(column, row);
    }

    /**
     * Parses the input map to create a Coordinate object.
     *
     * @param input the input map containing the coordinate information
     * @return the parsed Coordinate object, or null if parsing fails
     */
    public static Coordinate parse(final Map<String, Map<Integer, String>> input) {
        if (input != null) {
            if (input.containsKey(PATTERN.pattern())) {
                Column column = Column.valueOf(input.get(PATTERN.pattern()).get(0).toUpperCase());
                int row = Integer.parseInt(input.get(PATTERN.pattern()).get(1));
                Coordinate coord = new Coordinate(column, row);
                if (coord.isValid()) {
                    return coord;
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    /**
     * Checks if the Coordinate is valid, i.e., within the boundaries of the grid.
     *
     * @return true if the Coordinate is valid, false otherwise
     */
    public boolean isValid() {
        if (this.row < 1 || this.row > SizeGrid.getSize()) {
            return false;
        }
        if (this.column.ordinal() < 0 || this.column.ordinal() >= SizeGrid.getSize()) {
            return false;
        }
        return true;
    }

    /**
     * Creates a copy of the current Coordinate object.
     *
     * @return a new Coordinate object with the same column and row values as the
     *         original
     */
    public Coordinate copy() {
        return new Coordinate(this.column, this.row);
    }
}
