package slicense.main.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import slicense.main.Handler;
import slicense.main.gfx.Assets;
import slicense.main.tiles.Tile;

/**
 *
 * CodeNMore
 */
public abstract class Entity {

    public static final int DEFAULT_HEALTH = 3;
    public static final int DEFAULT_WIDTH=32,DEFAULT_HEIGHT=32;
    protected Handler handler;
    protected float x, y;
    protected int width, height;
    protected int health;
    protected boolean active = true;
    protected Rectangle bounds;
    protected boolean selected;
    protected float xStart,yStart;
    protected int moveRange;

    public Entity(Handler handler, float x, float y, int width, int height,int moveRange) {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.moveRange = moveRange;
        health = DEFAULT_HEALTH;

        bounds = new Rectangle(0, 0, width, height);
        xStart = x;
        yStart = y;
    }

    public abstract void tick();

    public abstract void render(Graphics g);
    
    //exending methods
    
    public void update(){
        select();
        move();
        x=xStart - handler.getGameCamera().getxOffset();
        y=yStart - handler.getGameCamera().getyOffset();
    }
    
    public void move(){
        if(selected){
            
        }
    }
    
    public void select(){
        if(handler.getMouseManager().getLeftPressed()){
            if(handler.getMouseManager().getMouseX()>x && handler.getMouseManager().getMouseX()<x+DEFAULT_WIDTH){
                if(handler.getMouseManager().getMouseY()>y && handler.getMouseManager().getMouseY()<y+DEFAULT_HEIGHT){
                    selected=true;
                } else {
                    selected=false;
                }
            } else {
                selected=false;
            }
        }
    }
    
    public void drawEffects(Graphics g){
        if(selected){
            //highlight
            g.drawImage(Assets.highlight,(int) x ,(int) y, Tile.TILEWIDTH,Tile.TILEHEIGHT, null);
        }
    }
    
    //getters and setters
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
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
