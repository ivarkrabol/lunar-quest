package ggf;

import java.awt.MouseInfo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Collection;

public class GameInput implements KeyListener, MouseListener {
    
    private boolean[] keyDown;
    private boolean[] keyChanged;
    private boolean[] mouseDown;
    private boolean[] mouseChanged;
    private Vector mousePos;
    
    private GameMouseArea[] mouseAreas;
    private GameMouseArea dragMouseArea;
    
    public GameInput() {
        keyDown = new boolean[525];
        keyChanged = new boolean[525];
        mouseDown = new boolean[3];
        mouseChanged = new boolean[3];
        mousePos = new Vector(MouseInfo.getPointerInfo().getLocation());
    }
    
    public void setMouseAreas(Collection<GameMouseArea> mouseAreaCollection) {
        mouseAreas = mouseAreaCollection.toArray(new GameMouseArea[0]);
        dragMouseArea = null;
    }
    
    public void update() {
        Vector prevMousePos = new Vector(mousePos);
        mousePos = new Vector(MouseInfo.getPointerInfo().getLocation());
        if(dragMouseArea != null) {
            dragMouseArea.drag(mousePos.subtract(prevMousePos));
        }
        
        Arrays.fill(keyChanged, false);
        Arrays.fill(mouseChanged, false);
    }
    
    
    
    
    
    

    @Override
    public void keyPressed(KeyEvent e) {
        keyDown[e.getKeyCode()] = true;
        keyChanged[e.getKeyCode()] = true;
//        
//        System.out.println(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyDown[e.getKeyCode()] = false;
        keyChanged[e.getKeyCode()] = true;
    }
    
    public boolean isKeyDown(int keyCode) {
        return keyDown[keyCode];
    }
    
    public boolean isKeyPressed(int keyCode) {
        return isKeyDown(keyCode) && keyChanged[keyCode];
    }
    
    public boolean isKeyReleased(int keyCode) {
        return !isKeyDown(keyCode) && keyChanged[keyCode];
    }

    
    
    
    
    
    private int getMouseCode(MouseEvent e) {
        int btn = e.getButton();
        return (btn > 0 && btn <= 3 ? btn - 1 : -1 );
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        int mouseCode = getMouseCode(e);
        if(mouseCode == -1) return;
        mouseDown[mouseCode] = true;
        mouseChanged[mouseCode] = true;
        
        if(mouseCode == 1) {
            for(GameMouseArea mouseArea : mouseAreas) {
                if(mousePos.isInside(mouseArea.getArea())) {
                    dragMouseArea = mouseArea;
                    break;
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int mouseCode = getMouseCode(e);
        if(mouseCode == -1) return;
        mouseDown[mouseCode] = false;
        mouseChanged[mouseCode] = true;
        
        if(mouseCode == 1) {
            dragMouseArea = null;
        }
    }
    
    public boolean isMouseDown(int mouseCode) {
        return mouseDown[mouseCode];
    }
    
    public boolean isMousePressed(int mouseCode) {
        return isMouseDown(mouseCode) && mouseChanged[mouseCode];
    }
    
    public boolean isMouseReleased(int mouseCode) {
        return !isMouseDown(mouseCode) && mouseChanged[mouseCode];
    }

    
    
    
    
    
    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

}
