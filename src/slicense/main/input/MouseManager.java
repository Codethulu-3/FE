package slicense.main.input;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Alex
 */
public class MouseManager implements MouseListener, MouseMotionListener {

    private boolean leftPressed, rightPressed;
    private int mouseX,mouseY;
    private Point origin = new Point(0,0);
    private Point mousePt;

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftPressed = true;
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            rightPressed = true;
        }
        if(rightPressed){
            mousePt = e.getPoint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftPressed = false;
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            rightPressed = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(rightPressed){
            int dx = e.getX() - mousePt.x;
            int dy = e.getY() - mousePt.y;
            origin.setLocation(origin.x + dx, origin.y + dy);
            mousePt = e.getPoint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }
    
    //getters and setters
    
    public boolean getLeftPressed(){
        return leftPressed;
    }
    public boolean getRightPressed(){
        return rightPressed;
    }
    
    public int getMouseX(){
        return mouseX;
    }
    public int getMouseY(){
        return mouseY;
    }
    
    public Point getOrigin(){
        return origin;
    }
}
