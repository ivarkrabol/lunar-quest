package ggf;

import java.util.HashMap;

public class GameControls {
    
    private GameInput input;
    private HashMap<String, Integer> keyControlMap;
    private HashMap<String, Integer> mouseControlMap;
    
    public void setInput(GameInput input) {
        this.input = input;
    }
    
    public void addKeyControl(String name, int keyCode) {
        keyControlMap.put(name, keyCode);
    }
    
    public void isPressed(String key)

}
