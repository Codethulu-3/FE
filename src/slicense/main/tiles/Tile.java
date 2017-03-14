package slicense.main.tiles;

import java.awt.Graphics;
import slicense.main.Handler;
import static slicense.main.entities.Entity.DEFAULT_HEIGHT;
import static slicense.main.entities.Entity.DEFAULT_WIDTH;

/**
 *
 * CodeNMore
 */

public abstract class Tile {

    //STATIC STUFF HERE
    public static Tile[] tiles = new Tile[256];

    //CLASS
    public static final int TILEWIDTH = 64, TILEHEIGHT = 64;
    protected final TileID id;
    protected Handler handler;
    protected int worldX, worldY;
    protected float screenX, screenY;
    protected boolean blocked;
    
    public Tile(Handler handler, int worldX, int worldY, TileID id) {
        this.id = id;
        this.handler = handler;
        this.worldX = worldX;
        this.worldY = worldY;
    }

    public void tick() {
        screenX = handler.getGameCamera().worldToScreenX(worldX);
        screenY = handler.getGameCamera().worldToScreenY(worldY);
        checkIfHovered();
    }

    public void checkIfHovered() {
        if (handler.getMouseManager().getMouseX() > screenX && handler.getMouseManager().getMouseX() < screenX + DEFAULT_WIDTH
                && handler.getMouseManager().getMouseY() > screenY && handler.getMouseManager().getMouseY() < screenY + DEFAULT_HEIGHT) {
            onMouseEnter();
        } else {
            onMouseExit();
        }
    }
    
    public void onMouseEnter() {
        //When the mouse hovers over the tile

    }
    
    public void onMouseExit() {
        //When the mouse leaves the tile
        
    }
    
    public abstract void render(Graphics g, int x, int y);

    public void setBlocked(boolean value) {
        blocked = value;
    }
    
    //Getters and setters
    public boolean isBlocked() {
        return blocked;
    }

    public TileID getID() {
        return id;
    }
    
    public int getWorldX() {
        return worldX;
    }
    
    public int getWorldY() {
        return worldY;
    }
}
