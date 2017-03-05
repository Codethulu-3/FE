package slicense.main.states;

import java.awt.Graphics;
import java.util.ArrayList;
import slicense.main.Handler;
import slicense.main.gfx.Assets;
import slicense.main.ui.Button;

/**
 *
 * @author Alex
 */
public class MenuState extends State{
    
    private Handler handler;
    
    private int buttonWidth = 256, buttonHeight = 64; //standard button size
    
    private ArrayList<Button> buttons =  new ArrayList(); 
    private Button startButton;
    private Button menuButton;
    private Button exitButton;
    
    public MenuState(Handler handler){
        super(handler);
        this.handler = handler;
        
        //set buttons
        startButton = new Button(handler, Assets.button, Assets.buttonHighlight, (handler.getWidth()/2) - (buttonWidth/2), 
                handler.getHeight()/2, buttonWidth,buttonHeight);
        
        menuButton = new Button(handler, Assets.button, Assets.buttonHighlight, (handler.getWidth()/2) - (buttonWidth/2), 
                handler.getHeight()/2  + (buttonHeight * 2), buttonWidth,buttonHeight);
        
        exitButton = new Button(handler, Assets.button, Assets.buttonHighlight, (handler.getWidth()/2) - (buttonWidth/2), 
                handler.getHeight()/2 + (buttonHeight * 4), buttonWidth,buttonHeight);
        
        
        //add to array list
        buttons.add(exitButton);
        buttons.add(menuButton);
        buttons.add(startButton);
    }
    
    @Override
    public void tick() {
        //update buttons
        for(Button b: buttons){
            b.tick();
        }
        
        //check for clicks
        updateClicking();
    }

    @Override
    public void render(Graphics g) {
        //draws all buttons
        for(Button b: buttons){
            b.render(g);
        }
        
    }
    
    private void updateClicking(){
        if(startButton.click()){
            State.setState(handler.getGame().getGameState());//go to game
        }
        
        if(exitButton.click()){
            handler.getGame().shutdown();//close game
        }
    }
    
    
}
