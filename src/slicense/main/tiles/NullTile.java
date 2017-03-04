package slicense.main.tiles;

import slicense.main.gfx.Assets;

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
}
