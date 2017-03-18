package slicense.main.entities;

import java.awt.Graphics;
import main.actions.*;
import slicense.main.Handler;
import static slicense.main.entities.Entity.DEFAULT_HEIGHT;
import static slicense.main.entities.Entity.DEFAULT_WIDTH;

/**
 *
 * @author Matthew Galan
 */
public class Unit extends Entity {
    
    protected int health, moveRange;
    protected boolean selected;
    protected Action action;

    public Unit(Handler handler, int worldX, int worldY, int width, int height, int health, int moveRange) {
        super(handler, worldX, worldY, width, height);
        this.health = health;
        this.moveRange = moveRange;
        action = new Move(handler, this);
    }

    @Override
    public void tick() {
        screenX = handler.getCamera().worldToScreenX(worldX);
        screenY = handler.getCamera().worldToScreenY(worldY);
        if (selected)
            action.tick();
        checkIfClicked();
    }

    @Override
    public void render(Graphics g) {
    }
    
    public void checkIfClicked(){
        if(handler.getMouseManager().getLeftClicked()){
            if (handler.getMouseManager().getMouseX()>screenX && handler.getMouseManager().getMouseX()<screenX+DEFAULT_WIDTH
                    && handler.getMouseManager().getMouseY()>screenY && handler.getMouseManager().getMouseY()<screenY+DEFAULT_HEIGHT) {
                onClick();
            } else {
                onDeclick();
            }
        }
        
        if(handler.getKeyManager().escapePressed){
            onDeclick();
            handler.getKeyManager().escapePressed=false;
        }
    }
    
    public void onClick() { //When the entity is clicked
        selected = true;
        action.freshlyClicked();
    }

    public void onDeclick() { //When there's a click elsewhere
        selected = false;
    }
    
    public void drawEffects(Graphics g) {
        if (action != null && selected)
            action.visualize(g);
    }
    
    public int getMoveRange() {
        return moveRange;
    }
}
