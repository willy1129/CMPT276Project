package cells;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Parent class for Cells
 *
 * @author Sarah Li
 * @since 1.0
 * @version 1.0
 */
public class Cell {

    public static Cell[] cells = new Cell[500];
    public static Cell groundCell = new GroundCell(0);
    public static Cell wallCell = new WallCell(1);

    protected BufferedImage sprite;
    public static final int CELL_WIDTH = 32, CELL_HEIGHT = 32;
    protected final int id;

    public Cell(BufferedImage sprite, int id) {
        this.sprite = sprite;
        this.id = id;
        cells[id] = this;
    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(sprite, x, y, CELL_WIDTH, CELL_HEIGHT, null);
    }

    /**
     * Getter for cell ID
     *
     * @return ID of this cell
     */
    public int getCellID() {
        return id;
    }

    /**
     * Getter for cell sprites
     *
     * @return sprite of this cell
     */
    public BufferedImage getCellSprite() {
        return sprite;
    }

    /**
     * Returns false if the cell is not solid
     *
     * @return boolean
     */
    public boolean isSolid() {
        return false;
    }

}
