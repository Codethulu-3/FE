package slicense.main;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import slicense.main.gfx.Assets;
import slicense.main.gfx.GameCamera;
import slicense.main.input.KeyManager;
import slicense.main.input.MouseManager;
import slicense.main.states.GameState;
import slicense.main.states.MenuState;
import slicense.main.states.State;

/**
 *
 * @author Alex
 */
public class Game extends GameLoop{
    
    private Display display;
    private BufferStrategy bs;
    private Graphics g;
    private int width,height;
    
    private Handler handler;
    
    private MouseManager mouseManager;
    private KeyManager keyManager;
    
    private GameCamera camera;
    
    //states
    private State gameState;
    private State menuState;
    
    @Override
    public void startup() {
        //sets width and height, don't touch this
        Toolkit tk = Toolkit.getDefaultToolkit();  
        //width = ((int) tk.getScreenSize().getWidth());  
        //height = ((int) tk.getScreenSize().getHeight());  
        
        width=1280;
        height=720;
        
        //initiate variables
        display = new Display("RougeLike", width, height);
        handler = new Handler(this);
        camera = new GameCamera(handler);
        
        mouseManager = new MouseManager();
        keyManager = new KeyManager();
        
        //initiate images, sounds, etc.
        Assets.init();
        
        //initates states
        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        State.setState(gameState);
        
        
        //set up frame listeners
        display.getFrame().addMouseListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        
        display.getFrame().addKeyListener(keyManager);
        display.getCanvas().addKeyListener(keyManager);
        
        //set up closing operation, don't touch this
        display.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                stop();
            }
        });
        
    }
    
    @Override
    public void shutdown() {
        System.exit(0);
    }
    
    @Override
    public void update() {
        State.getState().tick();
        mouseManager.tick();
    }

    @Override
    public void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //Clear Screen
        g.clearRect(0, 0, width, height);
        
        draw(g);
        
        bs.show();
        g.dispose();
        
    }
    
    public void draw(Graphics g){//draws
        g.clipRect(0, 0, width, height);
        State.getState().render(g);
    }
    
    public int getWidth(){return width;}
    public int getHeight(){return height;}
    
    public GameCamera getGameCamera(){
        return camera;
    }
    
    public MouseManager getMouseManager(){
        return mouseManager;
    }
    public KeyManager getKeyManager(){
        return keyManager;
    }
    
    public State getGameState(){
        return gameState;
    }
    
}
