package ggf.framework;

import java.util.HashMap;

public class Controls {
    
    public enum InputType {
        KEY_DOWN, KEY_PRESSED, KEY_RELEASED, 
        MOUSE_DOWN, MOUSE_PRESSED, MOUSE_RELEASED
    }
    
    private final InputHandler input;
    private final HashMap<String, InputType> inputTypeMap;
    private final HashMap<String, Integer> inputCodeMap;
    
    public Controls(InputHandler input) {
        this.input = input;
        inputTypeMap = new HashMap();
        inputCodeMap = new HashMap();
    }
    
    public void bind(String name, int inputCode, InputType inputType) {
        inputTypeMap.put(name, inputType);
        inputCodeMap.put(name, inputCode);
    }
    
    public boolean hasControl(String name) {
        return inputTypeMap.containsKey(name);
    }
    
    public boolean is(String name) {
        if(!inputTypeMap.containsKey(name)) throw new IllegalArgumentException("No such key bound");
        switch(inputTypeMap.get(name)) {
            case KEY_DOWN:
                return input.isKeyDown(inputCodeMap.get(name));
            case KEY_PRESSED:
                return input.isKeyPressed(inputCodeMap.get(name));
            case KEY_RELEASED:
                return input.isKeyReleased(inputCodeMap.get(name));
            case MOUSE_DOWN:
                return input.isMouseDown(inputCodeMap.get(name));
            case MOUSE_PRESSED:
                return input.isMousePressed(inputCodeMap.get(name));
            case MOUSE_RELEASED:
                return input.isMouseReleased(inputCodeMap.get(name));
            default:
                return false;
        }
    }

}
