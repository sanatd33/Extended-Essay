package Mazes;

import Structure.Cell;
import Structure.Grid;

public class BinaryTree {
    public static Grid on(Grid grid) {
        for (int i = 0; i < grid.getRow(); i++) {
            for (int j = 0; j < grid.getCol(); j++) {
                //Iterates through all cells
                Cell c = grid.getCell(i, j);

                //If the cell is not on any edge link it randomly with either the North or East neighbor
                if (c.North != null && c.East != null) {
                    c.link(Math.random() > 0.5 ? c.East : c.North);
                }
                //If the cell is on the North edge but not the East edge, link it with the East neighbor
                else if (c.North == null && c.East != null) {
                    c.link(c.East);
                }
                //If the cell is on the East edge but not the North edge, link it with the North neighbor
                else if (c.North != null) {
                    c.link(c.North);
                }
            }
        }
        //Return the new grid
        return grid;
    }
}
