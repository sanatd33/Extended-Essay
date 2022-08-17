package Mazes;

import Structure.Cell;
import Structure.Grid;

import java.util.ArrayList;
import java.util.Random;

public class Sidewinder {
    public static Grid on(Grid grid) {
        //Loop through each row of the maze
        for (int i = 0; i < grid.getRow(); i++) {
            //Instantiate a run in that row
            ArrayList<Cell> run = new ArrayList<>();

            for (int j = 0; j < grid.getCol(); j++) {
                Random rand = new Random();
                Cell c = grid.getCell(i, j);
                run.add(c);
                //Loop through each cell in the row and add it to the run

                //If the cell is on the North edge but not the East edge, link it with the East neighbor
                if (c.North == null && c.East != null) {
                    c.link(c.East);
                }
                //If the cell is on the East edge but not the North edge, close the run
                else if (c.East == null && c.North != null) {
                    Cell random = run.get(rand.nextInt(run.size()));
                    random.link(random.North);
                    run.clear();
                }
                //If the cell is not on either edge, either end the run or link it with its East neighbor
                else if (c.North != null){
                    boolean b = rand.nextBoolean();

                    if (b) {
                        Cell random = run.get(rand.nextInt(run.size()));
                        random.link(random.North);
                        run.clear();
                    }
                    else {
                        c.link(c.East);
                    }
                }
            }
            //Clear the run at the end of the row
            run.clear();
        }
        //Return the new grid
        return grid;
    }
}
