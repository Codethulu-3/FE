package slicense.main.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import slicense.main.Handler;

/**
 *
 * @author Alex
 */
public class Button {
    
    private Handler handler;
    private BufferedImage texture;
    private BufferedImage highlightTexture;
    private int x,y;
    private int width, height;
    
    private boolean highlighted=false;
    
    public Button(Handler handler, BufferedImage texture, BufferedImage highlightTexture, int x, int y, int width, int height){
        this.handler = handler;
        this.texture = texture;
        this.highlightTexture = highlightTexture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public void tick(){
        if(handler.getMouseManager().getMouseX() > x && handler.getMouseManager().getMouseX() < x + width){
            if(handler.getMouseManager().getMouseY() > y && handler.getMouseManager().getMouseY() < y + height){
                highlighted = true;
            } else {
                highlighted = false;
            }
        } else {
            highlighted = false;
        }
    }
    
    public void render(Graphics g){
        if(highlighted){
            g.drawImage(highlightTexture, x, y, width, height, null);
        } else {
            g.drawImage(texture, x, y, width, height,null);
        }
    }
    
    public boolean click(){
        if(highlighted){
            if(handler.getMouseManager().getLeftPressed()){
                return true;
            }
        }
        return false;
    }
    
}
