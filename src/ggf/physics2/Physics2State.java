package ggf.physics2;

import ggf.GameObject;
import ggf.GameState;
import ggf.TransformObject;
import ggf.framework.Controls;
import ggf.framework.GameStateManager;
import ggf.framework.GameTime;
import java.awt.Graphics2D;
import java.util.HashSet;
import java.util.Set;

public class Physics2State extends GameState {
    
    protected Set<GameObject> gameObjects;
    protected Set<RigidPolygon> physicsObjects;
    protected TransformObject view;

    public Physics2State() {
        gameObjects = new HashSet();
        physicsObjects = new HashSet();
        view = new TransformObject();
    }
    
    @Override
    public void draw(Graphics2D g) {
        for(GameObject gameObject : gameObjects) {
            gameObject.draw(g);
        }
    }

    @Override
    public void update(GameTime time, GameStateManager stateMgr, Controls controls) {
        
        // UPDATE GAMEOBJECTS
        for(GameObject gameObject : gameObjects) {
            gameObject.update(time, stateMgr, controls);
        }
    
    }

}
