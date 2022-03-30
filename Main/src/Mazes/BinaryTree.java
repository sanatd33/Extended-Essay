package Mazes;

import Structure.Cell;
import Structure.Grid;

import java.util.Random;

public class BinaryTree {
    public static Grid on(Grid grid) {
        for (int i = 0; i < grid.size(); i++) {
            Cell c = grid.getCells()[i];

            if (c.North != null && c.East != null) {
                c.link(new Random().nextBoolean() ? c.East : c.North);
            }
            else if (c.North == null && c.East != null) {
                c.link(c.East);
            }
            else if (c.North != null) {
                c.link(c.North);
            }

        }
        return grid;
    }
}
