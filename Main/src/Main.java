import Algorithms.Dijkstra;
import Algorithms.LongestPath;
import Mazes.Sidewinder;
import Structure.DistanceGrid;
import Structure.Grid;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        DistanceGrid grid = new DistanceGrid(50, 50);
        Sidewinder.on(grid);
        Dijkstra.on(grid);
        System.out.println(grid);
        grid.toImage();
    }
}
