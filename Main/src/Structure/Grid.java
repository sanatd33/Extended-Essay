package Structure;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Grid {
    private int rows, columns;
    public Cell[][] grid;

    public Grid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        grid = prepareGrid();
        configureCells();
    }

    public Grid(int rows, int columns, boolean blank) {
        this.rows = rows;
        this.columns = columns;

        grid = prepareGrid();
        configureCells();
        if (blank) {
            for (Cell[] row : grid) {
                for (Cell c : row) {
                    for (Cell n : c.getNeighbors()) {
                        c.link(n, false);
                    }
                }
            }
        }
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

    public Cell[] getCells() {
        Cell[] arr = new Cell[size()];
        int i = 0;
        for (Cell[] row : grid) {
            for (Cell c : row) {
                arr[i] = c;
                i++;
            }
        }
        return arr;
    }

    public Cell[][] getRows() {
        return grid;
    }

    public String contentsOf(Cell c) {
        return " ";
    }

    public Color backgroundColorFor(Cell c) {
        return Color.WHITE;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("+");
        for (int i = 0; i < columns; i++) {
            output.append("---+");
        }
        output.append("\n");

        for (Cell[] row : getRows()) {
            StringBuilder top = new StringBuilder("|");
            StringBuilder bottom = new StringBuilder("+");

            for (Cell cell : row) {
                if (cell == null) cell = new Cell(-1, -1);

                String body = " " + contentsOf(cell) + " ";
                String east_boundary = (cell.isLinked(cell.East) ? " " : "|");

                top.append(body).append(east_boundary);

                String south_boundary = (cell.isLinked(cell.South) ? "   ": "---");
                String corner = "+";

                bottom.append(south_boundary).append(corner);
            }

            output.append(top).append("\n").append(bottom).append("\n");
        }

        return output.toString();
    }

    public void toImage() throws IOException {
        int cell_size = 100;
        int stroke_size = 5;
        int img_width = cell_size * columns, img_height = cell_size * rows;

        BufferedImage bi = new BufferedImage(img_width, img_height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D ig2 = bi.createGraphics();

        ig2.setColor(Color.WHITE);
        ig2.fillRect(0, 0, img_width, img_height);

        ig2.setStroke(new BasicStroke(stroke_size));
        for (Cell cell : getCells()) {

            int x1 = cell.getColumn() * cell_size, y1 = cell.getRow() * cell_size;
            int x2 = (cell.getColumn() + 1) * cell_size, y2  = (cell.getRow() + 1) * cell_size;


            ig2.setColor(Color.BLACK);
            if (cell.North == null) ig2.drawLine(x1, y1, x2, y1);
            if (cell.West == null) ig2.drawLine(x1, y1, x1, y2);

            if (!cell.isLinked(cell.East)) ig2.drawLine(x2, y1, x2, y2);
            if (!cell.isLinked(cell.South)) ig2.drawLine(x1, y2, x2, y2);
        }

        ig2.drawLine(0,stroke_size / 2, img_width, stroke_size / 2);
        ig2.drawLine(stroke_size / 2, 0, stroke_size / 2, img_height);
        ig2.drawLine(0,img_height - stroke_size / 2, img_width, img_height - stroke_size / 2);
        ig2.drawLine(img_width - stroke_size / 2, 0, img_width - stroke_size / 2, img_height);

        ImageIO.write(bi, "PNG", new File("Main/src/Output/maze.png"));
    }

    public void toColorized() throws IOException {
        int cell_size = 100;
        int stroke_size = 5;
        int img_width = cell_size * columns, img_height = cell_size * rows;

        BufferedImage bi = new BufferedImage(img_width, img_height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D ig2 = bi.createGraphics();

        ig2.setColor(Color.WHITE);
        ig2.fillRect(0, 0, img_width, img_height);

        ig2.setStroke(new BasicStroke(stroke_size));
        for (Cell cell : getCells()) {
            ig2.setColor(Color.RED);
            if (cell.getLinks().size() == 1) ig2.fillRect(cell.getColumn() * cell_size + stroke_size / 2, cell.getRow() * cell_size + stroke_size / 2, cell_size - stroke_size / 2, cell_size - stroke_size / 2);

            int x1 = cell.getColumn() * cell_size, y1 = cell.getRow() * cell_size;
            int x2 = (cell.getColumn() + 1) * cell_size, y2  = (cell.getRow() + 1) * cell_size;


            ig2.setColor(Color.BLACK);
            if (cell.North == null) ig2.drawLine(x1, y1, x2, y1);
            if (cell.West == null) ig2.drawLine(x1, y1, x1, y2);

            if (!cell.isLinked(cell.East)) ig2.drawLine(x2, y1, x2, y2);
            if (!cell.isLinked(cell.South)) ig2.drawLine(x1, y2, x2, y2);
        }

        ig2.drawLine(0,stroke_size / 2, img_width, stroke_size / 2);
        ig2.drawLine(stroke_size / 2, 0, stroke_size / 2, img_height);
        ig2.drawLine(0,img_height - stroke_size / 2, img_width, img_height - stroke_size / 2);
        ig2.drawLine(img_width - stroke_size / 2, 0, img_width - stroke_size / 2, img_height);

        ImageIO.write(bi, "PNG", new File("Main/src/Output/maze4.png"));
    }

    public void toTree() throws IOException {
        int cell_size = 100;
        int stroke_size = 5;
        int img_width = cell_size * columns, img_height = cell_size * rows;

        BufferedImage bi = new BufferedImage(img_width, img_height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D ig2 = bi.createGraphics();

        ig2.setColor(Color.WHITE);
        ig2.fillRect(0, 0, img_width, img_height);

        ig2.setStroke(new BasicStroke(stroke_size));



        for (Cell c : getCells()) {
            ig2.setColor(Color.RED);
            ig2.fillOval(c.getColumn() * cell_size + cell_size / 2 - 8, c.getRow() * cell_size + cell_size / 2 - 8, 16, 16);
            ig2.setColor(Color.RED);
            for (Cell c1 : c.getLinks().keySet()) {
                ig2.drawLine(c1.getColumn() * cell_size + cell_size / 2, c1.getRow() * cell_size + cell_size / 2, c.getColumn() * cell_size + cell_size / 2, c.getRow() * cell_size + cell_size / 2);
            }
        }

        ImageIO.write(bi, "PNG", new File("Main/src/Output/maze1.png"));
    }


    public int getCol() {
        return columns;
    }
    public int getRow() {
        return rows;
    }
}
