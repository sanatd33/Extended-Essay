package Algorithms;

import Structure.Cell;
import Structure.DistanceGrid;
import Structure.Grid;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Dijkstra {
    public static Grid on(DistanceGrid grid) {
        //Sets the start and end cell of the desired path
        Cell start = grid.getCell(grid.getRow() - 1, 0);
        Cell end = grid.getCell(0, grid.getCol() - 1);

        //Creates maps to store a cell's information
        HashMap<Cell, Integer> distance = new HashMap<>();
        HashMap<Cell, Boolean> discovered = new HashMap<>();

        //Initializes the maps
        for (Cell c : grid.getCells()) {
            discovered.put(c, false);
            if (c.equals(start)) distance.put(c, 0);
            else distance.put(c, Integer.MAX_VALUE);
        }
        //Creates a queue and adds the start cell to it
        PriorityQueue<Cell> queue = new PriorityQueue<>(Comparator.comparingInt(distance::get));
        queue.add(start);

        //Repeats the process until the queue is empty
        while (!queue.isEmpty()) {
            //Gets the front item from the queue and discovers it
            Cell currentNode = queue.poll();
            discovered.put(currentNode, true);

            //Updates each of its neighbors
            for (Cell c : currentNode.getNeighbors()) {
                //Skips any cells already discovered or not linked
                if (discovered.get(c) || !c.isLinked(currentNode)) continue;

                //Updates the neighbor's distance
                int minDistance = Math.min(distance.get(c), distance.get(currentNode) + 1);
                if (minDistance != distance.get(c)) {
                    distance.put(c, minDistance);
                }

                //Adds the neighbor to the queue
                if (!queue.contains(c)) queue.add(c);
            }
        }

        //Reconstructs the path
        ArrayList<Cell> path = new ArrayList<>();
        path.add(end);
        while (!path.contains(start)) {
            for (Cell c : end.getLinks().keySet()) {
                if (distance.get(end) - 1 == distance.get(c)) {
                    path.add(c);
                    end = c;
                    break;
                }
            }
        }

        //Stores the path
        grid.path = path;

        //Returns the new grid
        return grid;
    }
}
