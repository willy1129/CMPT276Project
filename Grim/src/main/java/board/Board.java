package board;

import cells.Cell;
import utils.Utils;
import java.awt.*;

/**
 * Generates the game's board using a .txt file
 *
 * @author Sarah Li
 * @version 1.0
 * @since 1.0
 */
public class Board {
    private int width, height;

    private int[][] cells;

    public Board(String path) {
        loadBoard(path);
    }

    public void render(Graphics g) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                getCell(x, y).render(g, x * Cell.CELL_WIDTH, y * Cell.CELL_HEIGHT);
            }
        }
    }

    /**
     * Creates a cell at given position
     *
     * @param x position
     * @param y position
     * @return a cell
     */
    public Cell getCell(int x, int y) {
        Cell c = Cell.cells[cells[x][y]];

        if (c == null) {
            return Cell.groundCell;
        }
        return c;
    }

    /**
     * Generates the board from board.txt
     *
     * @param path of board.txt
     */
    private void loadBoard(String path) {
        // To account for current working directory
        String editedPath = Utils.editPath(path);
        String file = Utils.loadFileAsString(editedPath);
        // Gets rid of any spacing in the text file
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);

        cells = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                cells[x][y] = Utils.parseInt(tokens[(x + y * width) + 2]);
            }
        }
    }

    /**
     * Goes through the cells array and checks if a cell is solid. If so, store a new Rectangle object at that position.
     *
     * @author Wai Lee
     * @return array of Rectangles
     */
    public Rectangle[] walls(){
        int i = 0;
        Rectangle[] wallBounds = new Rectangle[500];
        for (int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                if(getCell(x,y).isSolid()) {
                    wallBounds[i] = new Rectangle(x*32, y*32, 25, 25);
                    i++;
                }
            }
        }
        return wallBounds;
    }
}
