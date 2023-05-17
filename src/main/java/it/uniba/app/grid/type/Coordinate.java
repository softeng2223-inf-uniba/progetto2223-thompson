package it.uniba.app.grid.type;
import java.util.Objects;

/**
 * Class to handle coordinates.
 */
public final class Coordinate {

    /**
     * Contain the Row.
     */
    private Row row;
    /**
     * Number of column.
     */
    private int column;
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
     * @param valRow Row type
     * @param valColumn integer of column
     */
    public Coordinate(final Row valRow, final int valColumn) {
        this.row = valRow;
        this.column = valColumn;
    }

    /**
     * Coordinate constructor.
     */
    public Coordinate() {
        this.row = Row.A;
        this.column = 0;
    }

    /**
     * Column getter.
     * @return column
     */
    public int getColumn() {
        return column;
    }

    /**
     * Column setter.
     * @param valColumn column
     */
    public void setColumn(final int valColumn) {
        this.column = valColumn;
    }

    /**
     * Row getter.
     * @return row
     */
    public Row getRow() {
        return row;
    }

    /**
     * Row getter.
     */
    public void setRow(final Row valRow) {
        this.row = valRow;
    }

    /**
     * Method to generate hash code of objects.
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int hash = HASH1;
        hash = HASH2 * hash + Objects.hashCode(this.row);
        hash = HASH2 * hash + this.column;
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
}
