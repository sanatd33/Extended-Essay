package Programs;

import Algorithms.*;
import Mazes.*;
import Structure.Grid;

import java.util.ArrayList;

public class CreationTimeTest {
    public static void main(String[] args) {
        for (int i = -15; i < 1000; i++) {
            Grid grid = new Grid(100, 100);
            long bT1 = System.nanoTime();
            BinaryTree.on(grid);
            long bT2 = System.nanoTime();
            grid = new Grid(100, 100);
            long sT1 = System.nanoTime();
            Sidewinder.on(grid);
            long sT2 = System.nanoTime();
            grid = new Grid(100, 100);
            long aT1 = System.nanoTime();
            AldousBroder.on(grid);
            long aT2 = System.nanoTime();
            grid = new Grid(100, 100);
            long hT1 = System.nanoTime();
            Kruskals.on(grid);
            long hT2 = System.nanoTime();
            grid = new Grid(10, 10);
            long rT1 = System.nanoTime();
            RecursiveDivision.on(grid);
            long rT2 = System.nanoTime();
            if (i < 0) continue;
            System.out.print(((bT2 - bT1)/1000000.) + " ");
            System.out.print(((sT2 - sT1)/1000000.) + " ");
            System.out.print(((aT2 - aT1)/1000000.) + " ");
            System.out.print(((hT2 - hT1)/1000000.) + " ");
            System.out.print(((rT2 - rT1)/1000000.) + " ");
            System.out.println();
        }
    }
}
