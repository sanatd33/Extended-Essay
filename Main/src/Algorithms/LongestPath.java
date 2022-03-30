package Algorithms;

import Structure.Cell;
import Structure.DistanceGrid;
import Structure.Distances;
import Structure.Grid;
import io.vavr.Tuple2;

import java.math.BigInteger;

public class LongestPath {
    public static Grid on(DistanceGrid grid) {
        Cell start = grid.getCell(0, 0);
        Distances distances = start.getDistances();

        Cell newStart = distances.max()._1();

        Distances newDistances = newStart.getDistances();
        Cell goal = newDistances.max()._1();

        grid.distances = newDistances.pathTo(goal);
        return grid;
    }
}
