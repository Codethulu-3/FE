package slicense.main.gfx;

import java.awt.Graphics;
import slicense.main.Handler;
import slicense.main.tiles.Tile;

/**
 *
 * @author Alex
 */
public class GameCamera {

    private Handler handler;
    private float xOffset, yOffset;
    int oldX,oldY;

    public GameCamera(Handler handler) {
        this.handler = handler;
    }

    public void moveCamera() {
        xOffset=-handler.getMouseManager().getOrigin().x;
        yOffset=-handler.getMouseManager().getOrigin().y;
    }

    public void updateCamera(Graphics g) {
        moveCamera();
        checkBlankSpace();
    }

    public void checkBlankSpace() {
        
        if (xOffset < 0) {
            xOffset = 0;
        } else if (xOffset > handler.getLevel().getWidth() * Tile.TILEWIDTH - handler.getWidth()) {
            xOffset = handler.getLevel().getWidth() * Tile.TILEWIDTH - handler.getWidth();
        }

        if (yOffset < 0) {
            yOffset = 0;
        } else if (yOffset > handler.getLevel().getHeight() * Tile.TILEHEIGHT - handler.getHeight()) {
            yOffset = handler.getLevel().getHeight() * Tile.TILEHEIGHT - handler.getHeight();
        }
        
        if(handler.getMouseManager().getOrigin().x>0){
            handler.getMouseManager().getOrigin().x = 0;
        } else if(handler.getMouseManager().getOrigin().x < -(handler.getLevel().getWidth() * Tile.TILEWIDTH - handler.getWidth())){
            handler.getMouseManager().getOrigin().x = -(handler.getLevel().getWidth() * Tile.TILEWIDTH - handler.getWidth());
        }
        if(handler.getMouseManager().getOrigin().y>0){
            handler.getMouseManager().getOrigin().y=0;
        } else if(handler.getMouseManager().getOrigin().y < -(handler.getLevel().getHeight() * Tile.TILEHEIGHT - handler.getHeight())){
            handler.getMouseManager().getOrigin().y = -(handler.getLevel().getHeight() * Tile.TILEHEIGHT - handler.getHeight());
        }
    }
    
    public int getTileX(){
        int x;
        if(handler.getMouseManager().getRightPressed() && handler.getMouseManager().getOrigin().x<0){
            x = oldX;
        } else {
            x = (int) ((handler.getMouseManager().getMouseX() + xOffset) / Tile.TILEWIDTH);
        } 
        oldX = x;
        return x;
    }
    
    public int getTileY(){
        int y;
        if(handler.getMouseManager().getRightPressed() && handler.getMouseManager().getOrigin().y<0){
            y = oldY;
        } else {
            y = (int) ((handler.getMouseManager().getMouseY() + yOffset) / Tile.TILEHEIGHT);
        }
        oldY = y;
        return y;
    }

    public float getxOffset() {
        return xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }
    
    public int worldToScreenX(int worldX) {
        return (int) (worldX * Tile.TILEWIDTH - xOffset);
    }
    
    public int worldToScreenY(int worldY) {
        return (int) (worldY * Tile.TILEHEIGHT - yOffset);
    }
}
