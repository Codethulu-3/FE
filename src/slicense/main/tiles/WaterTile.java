package slicense.main.tiles;

import slicense.main.gfx.Assets;

/**
 *
 * @author Alex
 */
public class WaterTile extends Tile{
    
    public WaterTile(int worldX, int worldY) {
	super(worldX, worldY, Assets.water, TileID.Water);
    }
    
}
