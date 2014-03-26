package ggf.framework;

import java.util.HashMap;

public class Controls {
    
    private InputHandler input;
    private HashMap<String, Boolean> isKeyControl;
    private HashMap<String, Integer> keyControlMap;
    private HashMap<String, Integer> mouseControlMap;
    
    public Controls(InputHandler input) {
        this.input = input;
    }
    
    public void addKeyControl(String name, int keyCode) {
        isKeyControl.put(name, true);
        keyControlMap.put(name, keyCode);
    }
    
    public void addMouseControl(String name, int mouseCode) {
        isKeyControl.put(name, false);
        mouseControlMap.put(name, mouseCode);
    }
    
    private boolean isKeyDown(String name) {
        return input.isKeyDown(keyControlMap.get(name));
    }
    
    private boolean isKeyPressed(String name) {
        return input.isKeyPressed(keyControlMap.get(name));
    }
    
    private boolean isKeyReleased(String name) {
        return input.isKeyReleased(keyControlMap.get(name));
    }
    
    private boolean isMouseDown(String name) {
        return input.isMouseDown(mouseControlMap.get(name));
    }
    
    private boolean isMousePressed(String name) {
        return input.isMousePressed(mouseControlMap.get(name));
    }
    
    private boolean isMouseReleased(String name) {
        return input.isMouseReleased(mouseControlMap.get(name));
    }
    
    public boolean isDown(String name) {
        if(isKeyControl.get(name)) return isKeyDown(name);
        else return isMouseDown(name);
    }
    
    public boolean isPressed(String name) {
        if(isKeyControl.get(name)) return isKeyPressed(name);
        else return isMousePressed(name);
    }
    
    public boolean isReleased(String name) {
        if(isKeyControl.get(name)) return isKeyReleased(name);
        else return isMouseReleased(name);
    }

}
