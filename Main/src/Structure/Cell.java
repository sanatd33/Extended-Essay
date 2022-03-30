package Structure;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Objects;

public class Cell {
    private int row, column;
    public Cell North, South, East, West;
    private Hashtable<Cell, Boolean> links;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
        links = new Hashtable<>();
    }

    public void link(Cell c, boolean bidi) {
        links.put(c, true);
        if (bidi) c.link(this, false);
    }
    public void link(Cell c) {
        link(c, true);
    }

    public void unlink(Cell c, boolean bidi) {
        links.remove(c);
        if (bidi) c.unlink(this, false);
    }
    public void unlink(Cell c) {
        unlink(c, true);
    }

    public Hashtable<Cell, Boolean> getLinks() {
        return links;
    }

    public boolean isLinked(Cell c) {
        if (c == null) return false;
        return links.get(c) != null && links.get(c);
    }

    public ArrayList<Cell> getNeighbors() {
        ArrayList<Cell> neighbors = new ArrayList<>();
        if (North != null) neighbors.add(North);
        if (South != null) neighbors.add(South);
        if (East != null) neighbors.add(East);
        if (West != null) neighbors.add(West);
        return neighbors;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Distances getDistances() {
        Distances distances = new Distances(this);
        ArrayList<Cell> frontier = new ArrayList<>();
        frontier.add(this);

        while (!frontier.isEmpty()) {
            ArrayList<Cell> new_frontier = new ArrayList<>();

            for (Cell cell : frontier) {
                for (Cell linked : cell.links.keySet()) {
                    if (distances.get(linked) != null) continue;
                    distances.set(linked, distances.get(cell).add(BigInteger.ONE));
                    new_frontier.add(linked);
                }
            }

            frontier = new_frontier;
        }
        return distances;
    }

    @Override
    public boolean equals(Object o) {
        Cell cell = (Cell) o;
        return (cell.getRow() == this.getRow() && cell.getColumn() == this.getColumn());
    }
}
