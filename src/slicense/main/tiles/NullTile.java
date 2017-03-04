package slicense.main.tiles;

import slicense.main.gfx.Assets;

/**
 *
 * @author Alex
 */
public class NullTile extends Tile{
    
    public NullTile(int id){
        super(Assets.grass, id);
    }
    
    @Override
    public boolean isSolid(){
        return true;
    }
}
