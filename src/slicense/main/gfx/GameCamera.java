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
    public float cameraSpeed=2;

    public GameCamera(Handler handler) {
        this.handler = handler;
    }

    public void moveCamera() {
        xOffset=-handler.getMouseManager().getOrigin().x/cameraSpeed;
        yOffset=-handler.getMouseManager().getOrigin().y/cameraSpeed;
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
    }

    public float getxOffset() {
        return xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }
}
