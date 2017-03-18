package slicense.main.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import slicense.main.Handler;
import slicense.main.gfx.Assets;
import slicense.main.gfx.GameCamera;
import slicense.main.levels.Level;
import slicense.main.tiles.Tile;

/**
 *
 * CodeNMore
 */
public abstract class Entity {

    public static final int DEFAULT_WIDTH=32, DEFAULT_HEIGHT=32;
    
    protected Handler handler;
    protected Level level;
    
    protected float screenX, screenY;
    protected int worldX, worldY, width, height;
    protected Rectangle bounds;
    protected boolean active = true;
    
    
    
    private final GameCamera cam;

    public Entity(Handler handler, int worldX, int worldY, int width, int height) {
        this.handler = handler;
        level = handler.getLevel();
        cam = handler.getCamera();
        this.width = width;
        this.height = height;
        bounds = new Rectangle(0, 0, width, height);
        this.worldX = worldX;
        this.worldY = worldY;
        
        
        level.getTileAt(worldX, worldY).setBlocked(true);
    }

    public abstract void tick();

    public abstract void render(Graphics g);
    
    //exending methods
    
    //getters and setters
    public float getScreenX() {
        return screenX;
    }

    public void setScreenX(float screenX) {
        this.screenX = screenX;
    }

    public float getScreenY() {
        return screenY;
    }

    public void setScreenY(float screenY) {
        this.screenY = screenY;
    }
    
    public int getWorldX() {
        return worldX;
    }
    
    public void setWorldX(int x) {
        worldX = x;
    }
    
    public void setWorldY(int y) {
        worldY = y;
    }
    
    public int getWorldY() {
        return worldY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
