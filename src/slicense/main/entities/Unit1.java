package slicense.main.entities;

import java.awt.Graphics;
import slicense.main.Handler;

/**
 *
 * @author Alex
 */
public class Unit1 extends Entity{
    
    public Unit1(Handler handler, float x, float y){
        super(handler,x,y,Entity.DEFAULT_WIDTH,Entity.DEFAULT_HEIGHT,5);
    }
    
    @Override
    public void tick() {
        update();
    }

    @Override
    public void render(Graphics g) {
        g.fillRect((int)(x ), (int)(y), width, height);
        drawEffects(g);
    }
    
    
    
}
