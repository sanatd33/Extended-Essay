package Mazes;

import Structure.Cell;
import Structure.Grid;

public class RecursiveDivision {
    public static Grid on(Grid grid) {
        //Creates a fully linked grid
        for (Cell c : grid.getCells()) {
            for (Cell n : c.getNeighbors()) {
                c.link(n, false);
            }
        }

        //Recursively divides the given grid into two
        divide(grid, 0, 0, grid.getRow(), grid.getCol());
        //Returns the new grid
        return grid;
    }

    //Divides a rectangular section of a grid in two
    private static void divide(Grid g, int row, int col, int height, int width) {
        if (height <= 1 || width <= 1) return;

        if (height > width) {
            divideHoriz(g, row, col, height, width);
        }
        else {
            divideVert(g, row, col, height, width);
        }
    }

    //Divides a rectangular section of a grid in two horizontally
    private static void divideHoriz(Grid g, int row, int col, int h, int w) {
        //Chooses where the wall and passage between the wall should be
        int southBorder = (int) (Math.random() * (h-1));
        int passage = (int) (Math.random() * w);

        //Creates that wall
        for (int i = 0; i < w; i++) {
            if (i == passage) continue;

            Cell c = g.getCell(row + southBorder, col + i);
            c.unlink(c.South);
        }

        //Divides the two new areas created
        divide(g, row, col, southBorder + 1, w);
        divide(g, row + southBorder + 1, col, h - southBorder - 1, w);
    }

    //Divides a rectangular section of a grid in two vertically
    private static void divideVert(Grid g, int row, int col, int h, int w) {
        //Chooses where the wall and passage between the wall should be
        int eastBorder = (int) (Math.random() * (w-1));
        int passage = (int) (Math.random() * h);

        //Creates that wall
        for (int i = 0; i < h; i++) {
            if (i == passage) continue;

            Cell c = g.getCell(row + i, col + eastBorder);
            c.unlink(c.East);
        }

        //Divides the two new areas created
        divide(g, row, col, h, eastBorder + 1);
        divide(g, row, col + eastBorder + 1, h, w - eastBorder - 1);
    }
}
