package slicense.main.tiles;

import java.awt.Graphics;
import slicense.main.Handler;
import slicense.main.gfx.Assets;
import static slicense.main.tiles.Tile.TILEHEIGHT;
import static slicense.main.tiles.Tile.TILEWIDTH;

/**
 *
 * @author Alex
 */
public class WaterTile extends Tile{
    
    public WaterTile(Handler handler, int worldX, int worldY) {
	super(handler, worldX, worldY, TileID.Water);
        setBlocked(true);
    }
    
    @Override
    public void render(Graphics g, int x, int y) {
        g.drawImage(Assets.water, x, y, TILEWIDTH, TILEHEIGHT, null);
    }
}
