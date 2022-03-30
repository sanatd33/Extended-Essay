package Structure;

import io.vavr.Tuple2;

import java.math.BigInteger;
import java.util.*;

public class Distances {
    Cell root;
    Cell goal;
    HashMap<Cell, BigInteger> cells;

    public Distances(Cell root) {
        this.root = root;
        this.goal = root;
        cells = new HashMap<>();
        cells.put(root, BigInteger.ZERO);
    }

    public BigInteger get(Cell c) {
        return cells.get(c);
    }

    public void set(Cell c, BigInteger distance) {
        cells.put(c, distance);
    }

    public List<Cell> getCells() {
        return Arrays.asList(cells.keySet().toArray(new Cell[0]));
    }

    public Distances pathTo(Cell goal) {
        Cell current = goal;
        this.goal = goal;

        Distances breadcrumbs = new Distances(root);
        breadcrumbs.set(current, cells.get(current));

        while (current != root) {
            for (Cell neighbor : current.getLinks().keySet()) {
                if (cells.get(neighbor).compareTo(cells.get(current)) < 0) {
                    breadcrumbs.set(neighbor, cells.get(neighbor));
                    current = neighbor;
                    break;
                }
            }
        }
        return breadcrumbs;
    }

    public Tuple2<Cell, BigInteger> max() {
        BigInteger max_distance = BigInteger.ZERO;
        Cell max_cell = root;

        for (Cell cell : cells.keySet()) {
            BigInteger distance = cells.get(cell);

            if (distance.compareTo(max_distance) > 0) {
                max_cell = cell;
                max_distance = distance;
            }
        }

        return new Tuple2<>(max_cell, max_distance);
    }
}
