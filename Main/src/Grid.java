public class Grid {
    private int rows, columns;
    private Cell[][] grid;

    public Grid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        grid = prepareGrid();
        configureCells();
    }

    protected Cell[][] prepareGrid() {
        Cell[][] arr = new Cell[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                arr[i][j] = new Cell(i, j);
            }
        }
        return arr;
    }

    protected void configureCells() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                grid[row][col].North = getCell(row - 1, col);
                grid[row][col].South = getCell(row + 1, col);
                grid[row][col].West = getCell(row, col - 1);
                grid[row][col].East = getCell(row, col + 1);
            }
        }
    }

    public Cell getCell(int row, int col) {
        if (row < 0 || row > rows - 1) return null;
        if (col < 0 || col > columns - 1) return null;
        return grid[row][col];
    }

    public Cell randomCell() {
        int row = (int) Math.round(Math.random() * (rows - 1));
        int col = (int) Math.round(Math.random() * (columns - 1));
        return getCell(row, col);
    }

    public int size() {
        return rows * columns;
    }
}
