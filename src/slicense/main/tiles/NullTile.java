package slicense.main.tiles;

import java.awt.Graphics;
import slicense.main.gfx.Assets;
import static slicense.main.tiles.Tile.TILEHEIGHT;
import static slicense.main.tiles.Tile.TILEWIDTH;

/**
 *
 * @author Alex
 */
public class NullTile extends Tile{
    
    public NullTile(int worldX, int worldY){
        super(worldX, worldY, Assets.grass, TileID.Null);
    }
    
    @Override
    public boolean isSolid(){
        return true;
    }
    
    @Override
    public void render(Graphics g, int x, int y) {
        //Don't render null tiles.
    }
}
