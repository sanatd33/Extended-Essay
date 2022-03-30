package Algorithms;

import Structure.Cell;
import Structure.DistanceGrid;
import Structure.Distances;
import Structure.Grid;

public class Dijkstra {
    public static Grid on(DistanceGrid grid) {
        Cell start = grid.getCell(grid.getRow() - 1, 0);
        Distances distances = start.getDistances();
        grid.distances = distances.pathTo(grid.getCell(grid.getRow() - 1, grid.getCol() - 1));
        return grid;
    }
}
