package Mazes;

import Structure.Cell;
import Structure.Grid;

import java.util.ArrayList;
import java.util.Random;

public class Sidewinder {
    public static Grid on(Grid grid) {
        for (int i = 0; i < grid.getRows().length; i++) {
            Cell[] row = grid.getRows()[i];
            ArrayList<Cell> run = new ArrayList<>();

            for (int j = 0; j < row.length; j++) {
                Random rand = new Random();
                Cell c = grid.getCell(i, j);
                run.add(c);

                if (c.North == null && c.East != null) {
                    c.link(c.East);
                }
                else if (c.East == null && c.North != null) {
                    Cell random = run.get(rand.nextInt(run.size()));
                    random.link(random.North);
                    run.clear();
                }
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
            run.clear();
        }
        return grid;
    }
}
