package Mazes;

import Structure.Cell;
import Structure.Grid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class HuntAndKill {
    public static Grid on(Grid grid) {
        Cell c = grid.randomCell();
        HashSet<Cell> visited = new HashSet<>();
        visited.add(c);

        while (visited.size() < grid.size()) {
            ArrayList<Cell> unvisited = new ArrayList<>();
            for (Cell n : c.getNeighbors()) {
                if (!visited.contains(n)) unvisited.add(n);
            }

            if (unvisited.size() > 0) {
                Cell n = unvisited.get((int) Math.floor(Math.random() * unvisited.size()));
                c.link(n);
                visited.add(n);
                c = n;
            }
            else {
                for (Cell cell : visited) {
                    boolean b = false;
                    for (Cell n : cell.getNeighbors()) {
                        if (!visited.contains(n)) {
                            c = cell;
                            b = true;
                            break;
                        }
                    }
                    if (b) break;
                }
            }
        }

        return grid;
    }
}
