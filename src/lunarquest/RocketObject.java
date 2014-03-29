package lunarquest;

import ggf.ShapeObject;
import ggf.Parent;
import ggf.framework.Controls;
import ggf.physics.RigidBody;
import ggf.framework.GameTime;
import ggf.framework.GameStateManager;
import ggf.geom.Vector;
import java.awt.Color;

public class RocketObject extends SpaceObject {
    
    public static final double THRUST = 0.0001;
    public static final double TORQUE = 0.001;

    private ShapeObject[] drawObjects;
    
    public RocketObject(Parent parent, Vector pos) {
        super(parent, pos);
        
        PolygonObject rocket = new PolygonObject(this);
        rocket.setFill(Color.WHITE);
        rocket.setPoints(
                new double[]{ 0.10, 0.00,-0.10, 0.00},
                new double[]{ 0.25, 0.15, 0.25,-0.25});
        
        CircleObject window = new CircleObject(this, 0.04);
        window.setFill(LQConstants.COLOR_RED);
        
        drawObjects = new ShapeObject[]{rocket, window};
        setShape(rocket.getShape());
    }
    
    @Override
    public double getMass() {
        return 0.05;
    }

    @Override
    public void update(GameTime time, GameStateManager stateMgr, Controls controls) {
        if(controls.ok("acc")) {
            Vector directionVector = Vector.directionVector(getRot());
            double deltaVelocity = THRUST*time.sDeltaTime()/getMass();
            setVel(getVel().add(directionVector.toSize(deltaVelocity)));
        }
        if(controls.ok("rot_cw")) setRot(getRot() + TORQUE);
        if(controls.ok("rot_cw")) setRot(getRot() + TORQUE);
        if(controls.ok("rot_ccw")) setRot(getRot() - TORQUE); 
        
        super.update(time, stateMgr, controls);
    }
    
}
