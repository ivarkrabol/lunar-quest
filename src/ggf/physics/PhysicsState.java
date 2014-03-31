package ggf.physics;

import ggf.framework.GameTime;
import ggf.GameObject;
import ggf.GameState;
import ggf.TransformObject;
import ggf.framework.Controls;
import ggf.framework.GameStateManager;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class PhysicsState extends GameState {

    protected ArrayList<GameObject> gameObjects;
    protected ArrayList<RigidBody> physicsObjects;
    protected TransformObject view;
    protected boolean paused;
    
    
    public PhysicsState() {
        gameObjects = new ArrayList();
        physicsObjects = new ArrayList();
        
        view = new TransformObject();
        
        paused = true;
    }
    
    @Override
    public void draw(Graphics2D g) {
        for(GameObject gameObject : gameObjects) {
            gameObject.draw(g);
        }
    }

    @Override
    public void update(GameTime time, GameStateManager stateMgr, Controls controls) {
        
        if(!paused) {

            // GAMEOBJECTS
            for(GameObject gameObject : gameObjects) {
                gameObject.update(time, stateMgr, controls);
            }
            
            
            // COLLISION TESTING
            for(RigidBody object1 : physicsObjects) {
                for(RigidBody object2 : physicsObjects) {
                    if(object1 == object2) continue;
                    if(object1.collision(object2, time.sDeltaTime())) paused = true;
                }
            }
        }
    }

}
