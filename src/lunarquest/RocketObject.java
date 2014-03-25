package lunarquest;

import ggf.GameTime;
import ggf.GameInput;
import ggf.GameStateManager;
import ggf.geom.Vector;
import java.awt.Color;
import java.awt.Graphics2D;

public class RocketObject extends GravityObject {
    
    public static final double THRUST = 0.0001;
    public static final double TORQUE = 0.001;

    private PolygonObject boundingPolygon;
    private TransformObject[] drawObjects;
    
    public RocketObject(FrameOfReference parent, Vector pos, double rotation, Vector vel) {
        super(parent, pos, rotation, vel);
        setMaxRadius(0.3);
        getIcon().setFillColor(LQConstants.COLOR_RED);
        
        PolygonObject boundingPolygon = new PolygonObject(this, Vector.NULL, 0, 4);
        
        PolygonObject rocket = new PolygonObject(this, Vector.NULL, Math.PI/2, 4);
        rocket.setFillColor(Color.WHITE);
        rocket.setPoints(
                new double[]{ 0.10, 0.00,-0.10, 0.00},
                new double[]{ 0.25, 0.15, 0.25,-0.25});
        
        CircleObject window = new CircleObject(this, new Vector(-0.07, 0), 0.04);
        window.setFillColor(LQConstants.COLOR_RED);
        
        drawObjects = new TransformObject[]{rocket, window};
    }
    
    @Override
    public double getMass() {
        return 0.05;
    }

    @Override
    public void update(GameTime clock, GameStateManager gsm, GameInput input) {
        FlyInput flyInput = new FlyInput(input);
        
        if(flyInput.acc()) {
            Vector directionVector = Vector.directionVector(getRotation());
            double deltaVelocity = THRUST*clock.sDeltaTime()/getMass();
            setVel(getVel().add(directionVector.toSize(deltaVelocity)));
        }
        if(flyInput.rotCw()) setRotation(getRotation() + TORQUE*clock.sDeltaTime());
        if(flyInput.rotCcw()) setRotation(getRotation() - TORQUE*clock.sDeltaTime()); 
        
        super.update(clock, gsm, input);
    }

    @Override
    public void drawDetailed(Graphics2D g) {
        for(TransformObject detail : drawObjects) {
            detail.draw(g);
        }
    }
    
}
