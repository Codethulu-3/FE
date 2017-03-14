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

    public static final int DEFAULT_HEALTH = 3;
    public static final int DEFAULT_WIDTH=32,DEFAULT_HEIGHT=32;
    protected Handler handler;
    protected Level level;
    protected float screenX, screenY;
    protected int width, height;
    protected int health;
    protected boolean active = true;
    protected Rectangle bounds;
    protected int worldX,worldY;
    protected int moveRange;
    protected boolean selected;
    
    protected ArrayList<Tile> highlights, path;
    protected Tile hoveredTile;
    
    private final GameCamera cam;

    public Entity(Handler handler, int worldX, int worldY, int width, int height,int moveRange) {
        this.handler = handler;
        level = handler.getLevel();
        this.width = width;
        this.height = height;
        this.moveRange = moveRange;
        health = DEFAULT_HEALTH;
        cam = handler.getGameCamera();
        bounds = new Rectangle(0, 0, width, height);
        this.worldX = worldX;
        this.worldY = worldY;
        highlights = new ArrayList<>();
        path = new ArrayList<>();
        
        level.getTileAt(worldX, worldY).setBlocked(true);
    }

    public abstract void tick();

    public abstract void render(Graphics g);
    
    //exending methods
    
    public void update(){//to be called in units
        screenX = cam.worldToScreenX(worldX);
        screenY = cam.worldToScreenY(worldY);
        updateTileHover();
        checkIfClicked();
    }

    public void checkIfClicked(){
        if(handler.getMouseManager().getLeftClicked()){
            if (handler.getMouseManager().getMouseX()>screenX && handler.getMouseManager().getMouseX()<screenX+DEFAULT_WIDTH
                    && handler.getMouseManager().getMouseY()>screenY && handler.getMouseManager().getMouseY()<screenY+DEFAULT_HEIGHT) {
                onClick();
            } else if (highlights.contains(hoveredTile)) {
                onHighlightClick();
            } else {
                onDeclick();
            }
        }
    }
    
    public void onClick() { //When the entity is clicked
        selected = true;
        calcHighlights();
    }

    public void onDeclick() { //When there's a click elsewhere
        selected = false;
        highlights.clear();
        path.clear();
    }
    
    public void onHighlightClick() {
        path.clear();
        level.getTileAt(worldX, worldY).setBlocked(false);
        worldX = hoveredTile.getWorldX();
        worldY = hoveredTile.getWorldY();
        level.getTileAt(worldX, worldY).setBlocked(true);
        calcHighlights();
    }

    public void updateTileHover() {
        int x = (int) ((handler.getMouseManager().getMouseX() + cam.getxOffset()) / Tile.TILEWIDTH);
        int y = (int) ((handler.getMouseManager().getMouseY() + cam.getyOffset()) / Tile.TILEHEIGHT);
        if (!level.outOfBounds(x, y)) {
            Tile newHoveredTile = level.getTileAt(x,y);
            if (newHoveredTile != hoveredTile) {
                hoveredTile = newHoveredTile;
                calcPath();
            }
        }
    }
 
    public void calcHighlights() {
        highlights.clear();
        highlights.add(level.getTileAt(worldX, worldY));
        for (int i = 1; i <= moveRange; i++) { //Generate selection
            int origSize = highlights.size();
            for (int j = 0; j < origSize; j++) {
                Tile cur = highlights.get(j);
                for (int xCheck = -1; xCheck <= 2; xCheck += 2) { //Check east/west neighbors
                    if (!level.outOfBounds(cur.getWorldX() + xCheck, cur.getWorldY())) { //If we're in the map
                        Tile neighbor = level.getTileAt(cur.getWorldX() + xCheck, cur.getWorldY());
                        if (!highlights.contains(neighbor) && !neighbor.isBlocked()) { //If we haven't already added it && it's walkable
                            highlights.add(neighbor);
                        }
                    }
                }
                for (int yCheck = -1; yCheck <= 2; yCheck += 2) { //Check north/south neighbors
                    if (!level.outOfBounds(cur.getWorldX(), cur.getWorldY() + yCheck)) { //If we're in the map
                        Tile neighbor = level.getTileAt(cur.getWorldX(), cur.getWorldY() + yCheck);
                        if (!highlights.contains(neighbor) && !neighbor.isBlocked()) { //If we haven't already added it && it's walkable
                            highlights.add(neighbor);
                        }
                    }
                }
            }
        }
        highlights.remove(level.getTileAt(worldX, worldY));
    }
    
    public void drawEffects(Graphics g) {
        for (Tile t : highlights) { //Render selection highlights
            g.drawImage(Assets.highlight, cam.worldToScreenX(t.getWorldX()),
                    cam.worldToScreenY(t.getWorldY()), Tile.TILEWIDTH, Tile.TILEHEIGHT, null);
        }
        if (selected) {
            Color c = g.getColor();
            g.setColor(Color.PINK);
            for (Tile t : path) {
                g.fillRect(cam.worldToScreenX(t.getWorldX()),
                        cam.worldToScreenY(t.getWorldY()), Tile.TILEWIDTH, Tile.TILEHEIGHT);
            }
            g.setColor(c);
        }
    }
    
    public void calcPath() {
        if (highlights.contains(hoveredTile)) {
            path = level.getPathing().getPath(level.getTileAt(worldX, worldY), hoveredTile);
        }
        path.remove(level.getTileAt(worldX, worldY));
    }

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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
