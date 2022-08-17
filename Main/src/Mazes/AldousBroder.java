package Mazes;

import Structure.Cell;
import Structure.Grid;

import java.util.Random;

public class AldousBroder {
    public static Grid on(Grid grid) {
        //Starts off at a random cell
        Cell c = grid.randomCell();
        //Initializes the number of unvisited cells remaining
        int unvisited = grid.size() - 1;

        //Continue the algorihtm while some cells are still unvisited
        while (unvisited > 0) {
            //Get a random neighbor from the current cell
            Cell neighbor = c.getNeighbors().get(new Random().nextInt(c.getNeighbors().size()));

            //If that neighbor is unvisited, link with it
            if (neighbor.getLinks().size() == 0) {
                c.link(neighbor);
                unvisited--;
            }
            //Move to the neighbor
            c = neighbor;
        }
        //Return the new grid
        return grid;
    }
}
