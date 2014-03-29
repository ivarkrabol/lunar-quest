package ggf.framework;

import java.util.HashMap;

public class Controls {
    
    private enum InputType {
        KEY_DOWN, KEY_PRESSED, KEY_RELEASED, 
        MOUSE_DOWN, MOUSE_PRESSED, MOUSE_RELEASED
    }
    
    private InputHandler input;
    private HashMap<String, InputType> inputTypeMap;
    private HashMap<String, Integer> inputCodeMap;
    
    public Controls(InputHandler input) {
        this.input = input;
        inputTypeMap = new HashMap();
        inputCodeMap = new HashMap();
    }
    
    public void bindKeyDown(String name, int inputCode) {
        inputTypeMap.put(name, InputType.KEY_DOWN);
        inputCodeMap.put(name, inputCode);
    }
    
    public void bindKeyPressed(String name, int inputCode) {
        inputTypeMap.put(name, InputType.KEY_PRESSED);
        inputCodeMap.put(name, inputCode);
    }
    
    public void bindKeyReleased(String name, int inputCode) {
        inputTypeMap.put(name, InputType.KEY_RELEASED);
        inputCodeMap.put(name, inputCode);
    }
    
    public void bindMouseDown(String name, int inputCode) {
        inputTypeMap.put(name, InputType.MOUSE_DOWN);
        inputCodeMap.put(name, inputCode);
    }
    
    public void bindMousePressed(String name, int inputCode) {
        inputTypeMap.put(name, InputType.MOUSE_PRESSED);
        inputCodeMap.put(name, inputCode);
    }
    
    public void bindMouseReleased(String name, int inputCode) {
        inputTypeMap.put(name, InputType.MOUSE_RELEASED);
        inputCodeMap.put(name, inputCode);
    }
    
    public boolean ok(String name) {
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
