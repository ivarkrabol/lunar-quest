package ggf.physics2;

import ggf.GameObject;
import ggf.framework.Controls;
import ggf.framework.GameStateManager;
import ggf.framework.GameTime;
import ggf.geom.Vector;
import ggf.geom.Vector;
import java.awt.Graphics2D;
import java.util.Map;
import java.util.Set;

public class PhysicsEngine implements GameObject {
    
    public static final double GRAVITATIONAL_CONSTANT = 0.0000000000667;
    
    private Set<RigidPolygon> rigidPolygons;
    private Set<PointMass> rigidVertices;
    private Map<PointMass, Vector> pointForces;
    private Map<PointMass, Vector> nextPosition;

    public PhysicsEngine() {
    }
    
    private void addForce(PointMass rigidVertex, Vector force) {
        Vector oldForce = pointForces.get(rigidVertex);
        Vector newForce = oldForce.add(force);
        pointForces.put(rigidVertex, newForce);
    }
    
    

    @Override
    public void update(GameTime time, GameStateManager stateMgr, Controls controls) {
        
        // ADD VARIOUS FORCES
        
        // ADD GRAVITY
        for(RigidPolygon rigidPolygon : rigidPolygons) {
            for(PointMass rigidVertex : rigidVertices) {
                if(rigidPolygon == rigidVertex.getBody()) continue;
                Vector forceDirectionVector = rigidPolygon.getPosition().sub()
            }
        }
        
        // ADJUST VELOCITY
        
        // MOVE
        
        // TEST FOR COLLISIONS
        
        // CALCULATE COLLISION FORCES
        
        // APPLY COLLISION FORCES
        
        // EXECUTE MOVEMENT
    }

    @Override
    public void draw(Graphics2D g) { }

}
