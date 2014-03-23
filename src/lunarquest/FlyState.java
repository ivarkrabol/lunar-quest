package lunarquest;

import ggf.GameClock;
import ggf.GameInput;
import ggf.GameObject;
import ggf.GameState;
import ggf.GameStateManager;
import ggf.geom.Vector;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class FlyState extends GameState implements FrameOfReference {
    
    public static final Vector WINDOW_CENTER = new Vector(LQConstants.WINDOW_WIDTH/2, LQConstants.WINDOW_HEIGHT/2);

    private ArrayList<GameObject> gameObjects;
    private ArrayList<CelestialObject> celestialObjects;
    private ArrayList<GravityObject> gravityObjects;
    private Space space;
    private RocketObject rocket;
    private boolean active;
    private int focusIndex;
    private GravityObject focusObject;
    
    
    public FlyState(GameClock clock) {
        gameObjects = new ArrayList();
        celestialObjects = new ArrayList();
        gravityObjects = new ArrayList();
        
        space = new Space(this, WINDOW_CENTER);
        space.setScale(0.0005);
        gameObjects.add(space);
        
        EarthObject earth = new EarthObject(space, Vector.NULL);
        celestialObjects.add(earth);
        gravityObjects.add(earth);
        gameObjects.add(earth);
        
        MoonObject moon = new MoonObject(space, new Vector(4000000, 0), earth);
        celestialObjects.add(moon);
        gravityObjects.add(moon);
        gameObjects.add(moon);
        
        rocket = new RocketObject(space, new Vector(3970000, 0), 0, Vector.NULL);
        rocket.attemptCircularOrbit(moon);
        gravityObjects.add(rocket);
        gameObjects.add(rocket);
        
        clock.setTimeScale(1);
        active = false;
        focusIndex = 0;
        focusObject = gravityObjects.get(focusIndex);
    }
    
    @Override
    public void draw(Graphics2D g) {
        for(GameObject gameObject : gameObjects) {
            gameObject.draw(g);
        }
    }

    @Override
    public void update(GameClock clock, GameStateManager stateManager, GameInput input) {
        if(input.isKeyDown(KeyEvent.VK_UP)) space.setScale(space.getScale()*Math.pow(1.1, clock.deltaTime()*0.01));
        if(input.isKeyDown(KeyEvent.VK_DOWN)) space.setScale(space.getScale()*Math.pow(1.1, -clock.deltaTime()*0.01));
        if(input.isKeyDown(KeyEvent.VK_PERIOD)) clock.setTimeScale(clock.getTimeScale()*Math.pow(1.1, clock.deltaTime()*0.01));
        if(input.isKeyDown(KeyEvent.VK_COMMA)) clock.setTimeScale(clock.getTimeScale()*Math.pow(1.1, -clock.deltaTime()*0.01));
        
        if(input.isKeyPressed(KeyEvent.VK_ENTER)) {
            if(++focusIndex >= gravityObjects.size()) focusIndex = 0;
            focusObject = gravityObjects.get(focusIndex);
        }
        
        if(input.isKeyDown(KeyEvent.VK_SPACE)) active = true;
        
        if(active) {
            for(GravityObject object : gravityObjects) {
                for(CelestialObject body : celestialObjects) {
                    if(object != body) object.gravitateTowards(body, clock.sDeltaTime());
                }
            }

            for(GameObject gameObject : gameObjects) {
                gameObject.update(clock, stateManager, input);
            }
        }
        
        space.setPos(WINDOW_CENTER.add(focusObject.getPos().mul(space.getScale()).neg()));
    }
    
    
    
    
    
    
    
    
    
    
    

    @Override
    public Vector getAbsPos() { return Vector.NULL; }
    @Override
    public double getAbsRotation() { return 0; }
    @Override
    public double getAbsScale() { return 1;}
    
    

}
