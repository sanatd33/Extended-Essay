package Programs;

import Algorithms.*;
import Mazes.*;
import Structure.DistanceGrid;
import Structure.Grid;

import java.io.IOException;
import java.util.ArrayList;

public class SolvingTimeTest {
    public static void main(String[] args) throws IOException {
        for (int j = 0; j < 5; j++) {
            for (int i = -15; i < 1000; i++) {
                DistanceGrid grid = new DistanceGrid(100, 100);
                if (j == 0) {
                    BinaryTree.on(grid);
                }
                else if (j == 1) {
                    Sidewinder.on(grid);
                }
                else if (j == 2) {
                    AldousBroder.on(grid);
                }
                else if (j == 3){
                    Kruskals.on(grid);
                }
                else {
                    RecursiveDivision.on(grid);
                }

                long wT1 = System.nanoTime();
                WallFollower.on(grid);
                long wT2 = System.nanoTime();
                long dT1 = System.nanoTime();
                Dijkstra.on(grid);
                long dT2 = System.nanoTime();
                long aT1 = System.nanoTime();
                AStar.on(grid);
                long aT2 = System.nanoTime();
                long bT1 = System.nanoTime();
                BFS.on(grid);
                long bT2 = System.nanoTime();
                long dfT1 = System.nanoTime();
                DFS.on(grid);
                long dfT2 = System.nanoTime();
                if (i < 0) continue;
                System.out.print(((dT2 - dT1)/1000000.) + " ");
                System.out.print(((aT2 - aT1)/1000000.) + " ");
                System.out.print(((bT2 - bT1)/1000000.) + " ");
                System.out.print(((dfT2 - dfT1)/1000000.) + " ");
                System.out.print(((wT2 - wT1)/1000000.) + " ");
                System.out.println();
            }
//            System.out.println("Average MS: " + times.stream().mapToDouble(a -> a).average());
        }
    }
}
