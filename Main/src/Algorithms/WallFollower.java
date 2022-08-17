package Algorithms;

import Structure.Cell;
import Structure.DistanceGrid;
import Structure.Grid;

import java.util.*;

public class WallFollower {
    public static Grid on(DistanceGrid grid) {
        //Sets the start and end cell of the desired path
        Cell start = grid.getCell(grid.getRow() - 1, 0);
        Cell goal = grid.getCell(0, grid.getCol() - 1);
        //Creates a list to store the path
        ArrayList<Cell> path = new ArrayList<>();
        Cell c = start;
        path.add(c);
        //Stores the direction of the algorithm in degrees
        //90 degrees is facing north, 0 degrees is facing east
        int direction = 90;
        boolean flagL, flagF;
        //Continues until the goal has been found
        while (true) {
            //Check if there is a wall on the left
            flagL = (!c.isLinked(c.getLeft(direction)));

            //If not, turn left
            if (!flagL) {
                if (direction == 270) {
                    direction = 0;
                }
                else {
                    direction += 90;
                }
            }
            //Check if there is a wall in front
            flagF = (!c.isLinked(c.getFront(direction)));
            //If so, turn right
            if (flagF) {
                if (direction == 0) {
                    direction = 270;
                }
                else {
                    direction -= 90;
                }
            }
            //If not, move forward
            else {
                //Update the current cell and add it to the path
                c = c.getFront(direction);
                path.add(c);
                //If the goal has been reached, exit the program
                if (c.equals(goal)) break;
            }
        }

        //Store the path
        grid.path = path;

        //Return the new grid
        return grid;
    }
}
