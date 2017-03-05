package slicense.main.entities;

import java.awt.Graphics;
import slicense.main.Handler;

/**
 *
 * @author Alex
 */
public class Unit1 extends Entity{
    
    public Unit1(Handler handler, int worldX, int worldY){
        super(handler, worldX, worldY, Entity.DEFAULT_WIDTH, Entity.DEFAULT_HEIGHT, 3);
    }
    
    @Override
    public void tick() {
        update();
    }

    @Override
    public void render(Graphics g) {
        g.fillRect((int)(screenX ), (int)(screenY), width, height);
        drawEffects(g);
    }
    
    
    
}
