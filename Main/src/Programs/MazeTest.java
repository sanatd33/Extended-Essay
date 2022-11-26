package Programs;

import Algorithms.*;
import Mazes.*;
import Structure.DistanceGrid;
import Structure.Grid;

import java.io.IOException;

public class MazeTest {

    public static void main(String[] args) throws IOException {
        Grid grid = new Grid(100, 100);
        AldousBroder.on(grid);
//        System.out.println(grid);
//        System.out.println(grid);
//        grid.toImage();
//        grid.toColorized();
//        grid.toTree();
        DistanceGrid g = DistanceGrid.fromGrid(grid);
//        WallFollower.on(g);
//        g.toImage();/
        DFS.on(g);
//        System.out.println(g);
        g.toImage();
//        g.toColorized(2);
//        DistanceGrid g2 = DistanceGrid.fromGrid(grid);
//        BFS.on(g2);
//        g2.toColorized(5);
//        DistanceGrid g3 = DistanceGrid.fromGrid(grid);
//        Dijkstra.on(g3);
//        g3.toColorized(6);
//        DistanceGrid g4 = DistanceGrid.fromGrid(grid);
//        AStar.on(g4);
//        g4.toColorized(7);
    }
}
