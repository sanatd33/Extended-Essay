package Structure;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Class used to represent each cell in a maze
 */
public class Cell {
    private int row, column;
    public Cell North, South, East, West;
    private LinkedHashMap<Cell, Boolean> links;

    /**
     * Initializes the Cell given its location
     * @param row
     * @param column
     */
    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
        links = new LinkedHashMap<>();
    }

    /**
     * Links this cell with another cell
     * If desired, will link bidirectionally
     * @param c
     * @param bidi
     */
    public void link(Cell c, boolean bidi) {
        links.put(c, true);
        if (bidi) c.link(this, false);
    }

    /**
     * Links this cell with another cell bidirectionally
     * @param c
     */
    public void link(Cell c) {
        link(c, true);
    }

    /**
     * Unlinks this cell from another cell
     * If desired, will unlink bidirectionally
     * @param c
     * @param bidi
     */
    public void unlink(Cell c, boolean bidi) {
        links.remove(c);
        if (bidi) c.unlink(this, false);
    }
    /**
     * Unlinks this cell from another cell bidirectionally
     * @param c
     */
    public void unlink(Cell c) {
        unlink(c, true);
    }

    /**
     * Returns a LinkedHashMap containing all the cells that this cell is linked to
     * @return The cell's links
     */
    public LinkedHashMap<Cell, Boolean> getLinks() {
        return links;
    }

    /**
     * Checks if this cell is linked to another cell
     * @param c
     * @return whether this cell is linked to the other cell
     */
    public boolean isLinked(Cell c) {
        if (c == null) return false;
        return links.get(c) != null && links.get(c);
    }

    /**
     * Returns a list of all the cell's neighbors
     * @return a list of the cell's neighbors
     */
    public ArrayList<Cell> getNeighbors() {
        ArrayList<Cell> neighbors = new ArrayList<>();
        if (North != null) neighbors.add(North);
        if (South != null) neighbors.add(South);
        if (East != null) neighbors.add(East);
        if (West != null) neighbors.add(West);
        return neighbors;
    }

    /**
     * Returns the cell's row in the grid
     * @return the cell's row
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns the cell's column in the grid
     * @return the cell's column
     */
    public int getColumn() {
        return column;
    }

    /**
     * Returns the cell to the left of this cell given a certain heading.
     * The heading is in degrees, where 0 is East and 90 is North
     * @param heading
     * @return the cell to the left of this cell
     */
    public Cell getLeft(int heading) {
        if (heading == 0) {
            return North;
        }
        else if (heading == 90) {
            return West;
        }
        else if (heading == 180) {
            return South;
        }
        else {
            return East;
        }
    }

    /**
     * Returns the cell in front of this cell given a certain heading.
     * The heading is in degrees, where 0 is East and 90 is North
     * @param heading
     * @return the cell in front of this cell
     */
    public Cell getFront(int heading) {
        if (heading == 0) {
            return East;
        }
        else if (heading == 90) {
            return North;
        }
        else if (heading == 180) {
            return West;
        }
        else {
            return South;
        }
    }

    /**
     * Determines if an object is equal to this cell
     * Two cells are equal if they share the same row and column
     * @param o
     * @return whether this cell is equal to another object
     */
    @Override
    public boolean equals(Object o) {
        Cell cell = (Cell) o;
        return (cell.getRow() == this.getRow() && cell.getColumn() == this.getColumn());
    }

    /**
     * Creates a hashcode for this cell
     * The hashcode is based on this cell's row and column
     * @return the hashcode for this cell
     */
    @Override
    public int hashCode() {
        int prime = 53;
        int result = 1;
        result = prime * result + row;
        result = prime * result + column;
        return result;
    }

    /**
     * Creates a string that carries the cell's position
     * @return a string representation of the cell
     */
    @Override
    public String toString() {
        return "Cell{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }
}
