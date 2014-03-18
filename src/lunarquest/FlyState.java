package lunarquest;

import ggf.GameClock;
import ggf.GameInput;
import ggf.GameObject;
import ggf.GameState;
import ggf.GameStateManager;
import ggf.Vector;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class FlyState extends GameState implements FrameOfReference {

    private ArrayList<GameObject> gameObjects;
    private ArrayList<CelestialObject> celestialObjects;
    private ArrayList<GravityObject> gravityObjects;
    private Space space;
    private boolean active;
    
    
    public FlyState() {
        gameObjects = new ArrayList();
        celestialObjects = new ArrayList();
        gravityObjects = new ArrayList();
        
        Vector windowCenter = new Vector(LQConstants.WINDOW_WIDTH/2, LQConstants.WINDOW_HEIGHT/2);
        space = new Space(this, windowCenter);
        gameObjects.add(space);
        
        EarthObject earth = new EarthObject(space, Vector.NULL, 1000000);
        earth.setScale(0.0001);
        celestialObjects.add(earth);
        gameObjects.add(earth);
        
        RocketObject rocket = new RocketObject(space, new Vector(-150, 0), 0, new Vector(0, -0.1));
        rocket.setScale(5);
        gravityObjects.add(rocket);
        gameObjects.add(rocket);
        
        active = false;
    }
    
    @Override
    public void draw(Graphics2D g) {
        for(GameObject gameObject : gameObjects) {
            gameObject.draw(g);
        }
    }

    @Override
    public void update(GameClock clock, GameStateManager stateManager, GameInput input) {
        if(input.isKeyDown(KeyEvent.VK_SPACE)) active = true;
        if(!active) return;
        
        for(GravityObject object : gravityObjects) {
            for(CelestialObject body : celestialObjects) {
                object.gravitateTowards(body);
            }
        }
        
        for(GameObject gameObject : gameObjects) {
            gameObject.update(clock, stateManager, input);
        }
    }
    
    
    
    
    
    
    
    
    
    
    

    @Override
    public Vector getAbsPos() { return Vector.NULL; }
    @Override
    public double getAbsRotation() { return 0; }
    @Override
    public double getAbsScale() { return 1;}
    
    

}
