package Structure;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;

public class DistanceGrid extends Grid{
    public Distances distances;

    public DistanceGrid(int rows, int columns) {
        super(rows, columns);
    }

    @Override
    public String contentsOf(Cell c) {
        if (distances != null && distances.get(c) != null) {
            return distances.get(c).toString(36);
        }
        else {
            return super.contentsOf(c);
        }
    }

    @Override
    public void toImage() throws IOException {
        int cell_size = 100;
        int stroke_size = 5;
        int img_width = cell_size * super.getCol(), img_height = cell_size * super.getRow();

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

            ig2.setStroke(new BasicStroke(10));
        }

        ig2.drawLine(0,stroke_size / 2, img_width, stroke_size / 2);
        ig2.drawLine(stroke_size / 2, 0, stroke_size / 2, img_height);
        ig2.drawLine(0,img_height - stroke_size / 2, img_width, img_height - stroke_size / 2);
        ig2.drawLine(img_width - stroke_size / 2, 0, img_width - stroke_size / 2, img_height);

        ig2.setColor(Color.RED);

        for (int i = 0; i < distances.getCells().size(); i++) {
            Cell c1 = distances.getCells().get(i);
            for (Cell c : c1.getLinks().keySet()) {
                if (distances.getCells().contains(c)) {
                    ig2.drawLine(c1.getColumn() * cell_size + cell_size / 2, c1.getRow() * cell_size + cell_size / 2, c.getColumn() * cell_size + cell_size / 2, c.getRow() * cell_size + cell_size / 2);
                }
            }

        }

        ImageIO.write(bi, "PNG", new File("Main/src/maze.png"));
    }
}
