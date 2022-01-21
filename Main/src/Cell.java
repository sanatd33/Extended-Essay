import java.util.ArrayList;
import java.util.Hashtable;

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
        return links.get(c);
    }

    public ArrayList<Cell> getNeighbors() {
        ArrayList<Cell> neighbors = new ArrayList<>();
        if (North != null) neighbors.add(North);
        if (South != null) neighbors.add(South);
        if (East != null) neighbors.add(East);
        if (West != null) neighbors.add(West);
        return neighbors;
    }
}
