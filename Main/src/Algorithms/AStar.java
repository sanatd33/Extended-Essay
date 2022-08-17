package Algorithms;

import Structure.Cell;
import Structure.DistanceGrid;
import Structure.Grid;

import java.util.*;

public class AStar {
    public static Grid on(DistanceGrid grid) {
        //Sets the start and end cell of the desired path
        Cell start = grid.getCell(grid.getRow() - 1, 0);
        Cell goal = grid.getCell(0, grid.getCol() - 1);

        //Creates and Initializes a map to store the f-values
        HashMap<Cell, Integer> fValues = new HashMap<>();
        fValues.put(start, 0);
        //Creates and Initializes the queue and Closed List
        PriorityQueue<Cell> openList = new PriorityQueue<>(Comparator.comparingInt(fValues::get));
        openList.add(start);
        HashSet<Cell> closedList = new HashSet<>();
        //Creates a Map which stores each cell's parent
        HashMap<Cell, Cell> parent = new HashMap<>();

        //Continues until the goal has been found or all cells have been reached
        while (!openList.contains(goal) && openList.size() > 0) {
            //Moves the Cell at the front of the queue to the closed list
            Cell currentNode = openList.peek();
            closedList.add(openList.poll());

            //Updates each of the cell's links
            for (Cell c : currentNode.getLinks().keySet()) {
                //If the neighbor has already been found, skip it
                if (closedList.contains(c)) continue;

                //If the neighbor is not in the queue, update its f-value and add it to the queue
                if (!openList.contains(c)) {
                    fValues.put(c, fValues.get(currentNode) + 1);
                    openList.add(c);
                    parent.put(c, currentNode);
                }
                //Else, check if the new path is faster. If so, update the f-value
                else {
                    int sum = fValues.get(currentNode) + 1;
                    if (sum < fValues.get(c)) {
                        fValues.put(c, sum);
                        parent.put(c, currentNode);
                    }
                }
            }
        }

        //Reconstructs the path
        ArrayList<Cell> path = new ArrayList<>();
        path.add(goal);
        while (!path.contains(start)) {
            path.add(0, parent.get(path.get(0)));
        }

        //Stores the path
        grid.path = path;

        //Returns the new grid
        return grid;
    }
}
