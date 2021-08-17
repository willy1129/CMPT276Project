package cells;

import gfx.Assets;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests cells
 * @author Sarah
 * @version 1.0
 */
class CellTest {
    Cell ground = new GroundCell(0);
    Cell wall = new WallCell(1);

    /**
     * Tests if each cell has the correct sprite
     */
    @Test
    void assetsTest() {
        assertEquals(Assets.ground, ground.sprite);
        assertEquals(ground.getCellSprite(), Assets.ground);
        assertEquals(Assets.wall, wall.sprite);
        assertEquals(wall.getCellSprite(), Assets.wall);
    }

    /**
     * Tests if ground is not solid and wall is solid
     */
    @Test
    void isSolidTest() {
        assertFalse(ground.isSolid());
        assertEquals(ground.getCellID(), 0);
        assertTrue(wall.isSolid());
        assertEquals(wall.getCellID(), 1);
    }
}