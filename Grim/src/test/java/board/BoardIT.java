package board;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import cells.Cell;
import gfx.Assets;
import java.awt.image.BufferedImage;

/**
 * Tests the generation of a board from board.txt if each cell is set as a wall or ground cell
 * @author Sarah Li
 * @version 1.0
 */
class BoardIT {
    Board board;
    int width = 26, height = 18, id;
    BufferedImage sprite;

    /**
     * Loads the board and checks if each cell's sprite and ID is a ground or wall cell depending if it's solid
     */
    @Test
    public void initBoard() {
        board = new Board("board.txt");
        Cell cell;
        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++) {
                cell = board.getCell(x, y);
                sprite = cell.getCellSprite();
                id = cell.getCellID();

                if(cell.isSolid()) {
                    assertEquals(sprite, Assets.wall);
                    assertEquals(id, 1);
                }
                else {
                    assertEquals(sprite, Assets.ground);
                    assertEquals(id, 0);
                }
            }
        }
    }
}