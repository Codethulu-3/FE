package slicense.main.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Alex
 */
public class KeyManager implements KeyListener{
    
    public boolean escapePressed;
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){escapePressed=true;}
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    
}
