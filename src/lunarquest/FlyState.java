package lunarquest;

import ggf.physics.RigidBody;
import ggf.framework.GameTime;
import ggf.GameObject;
import ggf.GameState;
import ggf.TransformObject;
import ggf.framework.Controls;
import ggf.framework.GameStateManager;
import ggf.geom.Vector;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class FlyState extends GameState {
    
    public static final Vector WINDOW_CENTER = new Vector(LQConstants.WINDOW_WIDTH/2, LQConstants.WINDOW_HEIGHT/2);

    private ArrayList<GameObject> gameObjects;
    private ArrayList<CelestialObject> celestialObjects;
    private ArrayList<SpaceObject> gravityObjects;
    private TransformObject space;
    private AffineTransform spaceTransform;
    private RocketObject rocket;
    private boolean paused;
    private int focusIndex;
    private RigidBody focusObject;
    private Hud hud;
    
    
    public FlyState(GameTime clock) {
        gameObjects = new ArrayList();
        celestialObjects = new ArrayList();
        gravityObjects = new ArrayList();
        
        space = new TransformObject();
        
        EarthObject earth = new EarthObject(space, Vector.NULL);
        celestialObjects.add(earth);
        gravityObjects.add(earth);
        gameObjects.add(earth);
        
        MoonObject moon = new MoonObject(space, new Vector(4000000, 0), earth);
        celestialObjects.add(moon);
        gravityObjects.add(moon);
        gameObjects.add(moon);
        
        rocket = new RocketObject(space, new Vector(3970000, 0));
        rocket.attemptCircularOrbit(moon);
        gravityObjects.add(rocket);
        gameObjects.add(rocket);
        
        paused = true;
        focusIndex = 2;
        focusObject = gravityObjects.get(focusIndex);
        
        hud = new Hud(Vector.NULL, rocket, moon, earth);
        gameObjects.add(hud);
    }
    
    @Override
    public void draw(Graphics2D g) {
        for(GameObject gameObject : gameObjects) {
            gameObject.draw(g);
        }
    }

    @Override
    public void update(GameTime time, GameStateManager stateMgr, Controls controls) {
        if(controls.ok("zoom_in")) space.setScale(space.getScale()*Math.pow(1.1, time.deltaTime()*0.01));
        if(controls.ok("zoom_out")) space.setScale(space.getScale()*Math.pow(1.1, -time.deltaTime()*0.01));
        if(controls.ok("speed_up")) time.setTimeScale(time.getTimeScale()*Math.pow(1.1, time.deltaTime()*0.01));
        if(controls.ok("slow_down")) time.setTimeScale(time.getTimeScale()*Math.pow(1.1, -time.deltaTime()*0.01));
        
        if(controls.ok("focus")) {
            if(++focusIndex >= gravityObjects.size()) focusIndex = 0;
            focusObject = gravityObjects.get(focusIndex);
        }
        
        if(controls.ok("pause")) paused = !paused;
        
        if(!paused) {
            for(SpaceObject object : gravityObjects) {
                for(CelestialObject body : celestialObjects) {
                    if(object != body) object.gravitateTowards(body, time.sDeltaTime());
                }
            }

            for(GameObject gameObject : gameObjects) {
                gameObject.update(time, stateMgr, controls);
            }
        }
        
        space.setPos(WINDOW_CENTER.add(focusObject.getPos().mul(space.getScale()).neg()));
    }

}
