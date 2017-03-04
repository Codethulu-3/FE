package slicense.main.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import slicense.main.gfx.Assets;

/**
 *
 * CodeNMore
 */

public class Tile {

    //STATIC STUFF HERE
    
    public static Tile[] tiles = new Tile[256];

    
    //CLASS
    public static final int TILEWIDTH = 64, TILEHEIGHT = 64;
    protected final TileID id;
    private int worldX, worldY;

    public Tile(int worldX, int worldY, TileID id) {
        this.id = id;

        this.worldX = worldX;
        this.worldY = worldY;
    }

    public void tick() {
    }

    public void render(Graphics g, int x, int y) {
        System.err.println("Render has not been overridden!");
    }

    public boolean isSolid() {
        return false;
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
