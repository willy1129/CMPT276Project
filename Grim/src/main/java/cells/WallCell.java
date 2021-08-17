package cells;

import gfx.Assets;

/**
 * Class to use wall Cells
 *
 * @author Sarah Li
 * @version 1.0
 * @since 1.0
 */
public class WallCell extends Cell {
    public WallCell(int id) {
        super(Assets.wall, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
