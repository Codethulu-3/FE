package slicense.main;

import slicense.main.gfx.GameCamera;
import slicense.main.input.KeyManager;
import slicense.main.input.MouseManager;
import slicense.main.levels.Level;

public class Handler {

    private Game game;
    private Level level;

    public Handler(Game game) {
        this.game = game;
    }
    
    public GameCamera getCamera(){
        return game.getGameCamera();
    }
    
    public MouseManager getMouseManager(){
        return game.getMouseManager();
    }
    
    public KeyManager getKeyManager(){
        return game.getKeyManager();
    }

    public int getWidth() {
        return game.getWidth();
    }

    public int getHeight() {
        return game.getHeight();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
    
    public Level getLevel(){
        return level;
    }
    
    public void setLevel(Level level){
        this.level = level;
    }
}
