package lunarquest.flystate;

import ggf.physics.RigidBody;
import ggf.framework.GameTime;
import ggf.framework.Controls;
import ggf.framework.GameStateManager;
import ggf.geom.Vector;
import ggf.geom.Vector;
import ggf.physics.PhysicsState;
import java.awt.Graphics2D;
import java.util.ArrayList;
import lunarquest.LQConstants;
import lunarquest.PolygonObject;

public class FlyState extends PhysicsState {
    
    public static final Vector WINDOW_CENTER = new Vector(LQConstants.WINDOW_WIDTH/2, LQConstants.WINDOW_HEIGHT/2);

    private ArrayList<SpaceObject> spaceObjects;
    private RocketObject rocket;
    private int focusIndex;
    private RigidBody focusObject;
    private Hud hud;
    
    
    public FlyState() {
        view.setPosition(WINDOW_CENTER);
        view.setScale(1000);
        
        
        
        
        spaceObjects = new ArrayList();
        
        EarthObject earth = new EarthObject(view, new Vector(0, 0));
        spaceObjects.add(earth);
        
        MoonObject moon = new MoonObject(view, new Vector(4000000, 0), earth);
        spaceObjects.add(moon);
        
        SpaceObject asteroid = new SpaceObject(view, new Vector(3970010, 2));
        PolygonObject asteroidGraphic = new PolygonObject(asteroid);
        asteroidGraphic.setPoints(
                new double[]{-1.1,-0.5,-0.4, 0.4, 0.4, 0.8, 1.0, 0.8, 0.1,-0.2,-0.7},
                new double[]{-0.1,-0.8,-1.1,-1.0,-0.7,-0.4, 0.1, 0.8, 0.9, 0.5, 0.5});
        asteroidGraphic.setFill(LQConstants.COLOR_LIGHT_GRAY);
        gameObjects.add(asteroidGraphic);
        asteroid.setShape(asteroidGraphic.getShape());
        asteroid.attemptCircularOrbit(earth);
        spaceObjects.add(asteroid);
        
        rocket = new RocketObject(view, new Vector(60001, 0));
//        rocket.attemptCircularOrbit(moon);
        spaceObjects.add(rocket);
        
        
        
        
        physicsObjects.addAll(spaceObjects);
        gameObjects.addAll(physicsObjects);
        
        
        
        
        focusObject = rocket;
        focusIndex = physicsObjects.indexOf(rocket);
        
        hud = new Hud(Vector.NULL, rocket, moon, earth);
        gameObjects.add(hud);
        
        
    }
    
    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        
        rocket.drawCollision(g, view.getAbsoluteTx());
    }

    @Override
    public void update(GameTime time, GameStateManager stateMgr, Controls controls) {
        
        // CONTROLS HANDLING
        
        //     ZOOM
        if(controls.is("zoom_in")) view.setScale(view.getScale()*Math.pow(1.1, time.deltaTime()*0.01));
        if(controls.is("zoom_out")) view.setScale(view.getScale()*Math.pow(1.1, -time.deltaTime()*0.01));
        
        //     SPEED
        if(controls.is("speed_up")) time.setTimeScale(time.getTimeScale()*Math.pow(1.1, time.deltaTime()*0.01));
        if(controls.is("slow_down")) time.setTimeScale(time.getTimeScale()*Math.pow(1.1, -time.deltaTime()*0.01));
        
        //     FOCUS
        if(controls.is("focus")) {
            if(++focusIndex >= physicsObjects.size()) focusIndex = 0;
            focusObject = physicsObjects.get(focusIndex);
        }
        
        //     PAUSE
        if(controls.is("pause")) paused = !paused;
        
        
        if(!paused) {
            
            
            // GRAVITY
            for(SpaceObject object1 : spaceObjects) {
                for(SpaceObject object2 : spaceObjects) {
                    if(object1 != object2) object1.gravitateTowards(object2, time.sDeltaTime());
                }
            }
            
            
        }
        
        view.setPosition(new Vector(WINDOW_CENTER.add(focusObject.getPosition().mul(view.getScale()).neg())));
    }

}
