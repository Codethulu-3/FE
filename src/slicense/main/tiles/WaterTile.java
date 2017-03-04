package slicense.main.tiles;

import java.awt.Graphics;
import slicense.main.gfx.Assets;
import static slicense.main.tiles.Tile.TILEHEIGHT;
import static slicense.main.tiles.Tile.TILEWIDTH;

/**
 *
 * @author Alex
 */
public class WaterTile extends Tile{
    
    public WaterTile(int worldX, int worldY) {
	super(worldX, worldY, Assets.water, TileID.Water);
    }
    
    @Override
    public void render(Graphics g, int x, int y) {
        g.drawImage(Assets.water, x, y, TILEWIDTH, TILEHEIGHT, null);
    }
}
