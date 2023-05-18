package it.uniba.app.grid.type;

import java.util.Objects;

/**
 * Class to handle coordinates.
 */
public final class Coordinate {

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
        this.column = Column.A;
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
}
