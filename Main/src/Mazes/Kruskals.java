package Mazes;

import Structure.Cell;
import Structure.Grid;
import io.vavr.Tuple2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Kruskals {
    public static Grid on(Grid grid) {
        //Creates a new State
        State state = new State(grid);
        //Creates and randomizes a list of neighbors
        ArrayList<Tuple2 <Cell, Cell>> neighbors = state.neighbors;
        Collections.shuffle(neighbors);

        //Continues the algorithm until all neighbor pairs have been evaluated
        while (!neighbors.isEmpty()) {
            //Gets the last pair of neighbors in the list
            Cell left = neighbors.get(neighbors.size()-1)._1;
            Cell right = neighbors.get(neighbors.size()-1)._2;
            neighbors.remove(neighbors.size()-1);

            //If the two neighbors can merge, merges them
            if (state.canMerge(left, right)) state.merge(left, right);
        }

        //Returns the new grid
        return grid;
    }

    //Class which handles merges
    private static class State {
        //Initialize an adjacency matrix of neighbors
        ArrayList<Tuple2<Cell, Cell>> neighbors = new ArrayList<>();
        //Initialize Maps which give the set for each cell and the cells in a set
        HashMap<Cell, Integer> set_for_cells = new HashMap<>();
        HashMap<Integer, ArrayList<Cell>> cells_in_set = new HashMap<>();


        //Constructs the adjacency matrix and the sets
        public State(Grid g) {
            for (Cell cell : g.getCells()) {
                int set = set_for_cells.size();

                set_for_cells.put(cell, set);
                cells_in_set.computeIfAbsent(set, k -> new ArrayList<>());
                cells_in_set.get(set).add(cell);

                if (cell.South != null) neighbors.add(new Tuple2<>(cell, cell.South));
                if (cell.East != null) neighbors.add(new Tuple2<>(cell, cell.East));
            }
        }

        //If two cells are in different sets, they can merge sets
        public boolean canMerge(Cell left, Cell right) {
            return !set_for_cells.get(left).equals(set_for_cells.get(right));
        }

        //Merges two sets
        public void merge(Cell left, Cell right) {
            //Links the two cells together
            left.link(right);

            //Moves all cells from one set to another set
            int winner = set_for_cells.get(left);
            int loser = set_for_cells.get(right);
            ArrayList<Cell> alternate = new ArrayList<>();
            alternate.add(right);
            ArrayList<Cell> losers = (cells_in_set.get(loser) != null) ? cells_in_set.get(loser) : alternate;

            for (Cell c : losers) {
                cells_in_set.get(winner).add(c);
                set_for_cells.put(c, winner);
            }

            cells_in_set.remove(loser);
        }
    }
}
