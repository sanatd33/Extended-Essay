package Algorithms;

import Structure.Cell;
import Structure.DistanceGrid;
import Structure.Grid;

import java.util.*;

public class BFS {
    public static Grid on(DistanceGrid grid) {
        //Sets the start and end cell of the desired path
        Cell start = grid.getCell(grid.getRow() - 1, 0);
        Cell goal = grid.getCell(0, grid.getCol() - 1);
        //Creates and initializes the queue of cells
        ArrayDeque<Cell> queue = new ArrayDeque<>();
        queue.push(start);
        //Creates and initializes a visited map
        HashMap<Cell, Boolean> visited = new HashMap<>();
        for (Cell c : grid.getCells()) {
            if (c.equals(start)) visited.put(c, true);
            else visited.put(c, false);
        }

        //Creates a Map which stores each cell's parent
        HashMap<Cell, Cell> parent = new HashMap<>();

        //Continues until the queue is empty
        while (!queue.isEmpty()) {
            //Retrieve the first item from the queue
            Cell c = queue.poll();
            //Iterate through each link
            for (Cell n : c.getLinks().keySet()) {
                //If the neighbor has not been visited, add it to the queue
                if (!visited.get(n)) {
                    parent.put(n, c);
                    queue.add(n);
                    visited.put(n, true);
                }
            }
        }

        //Reconstruct the path
        ArrayList<Cell> path = new ArrayList<>();
        path.add(goal);
        while (!path.contains(start)) {
            path.add(0, parent.get(path.get(0)));
        }

        //Store the path
        grid.path = path;

        //Return the new grid
        return grid;
    }
}