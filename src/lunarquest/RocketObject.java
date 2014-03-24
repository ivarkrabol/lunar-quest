package lunarquest;

import ggf.GameClock;
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
        
        PolygonObject boundingPolygon = new PolygonObject(this, Vector.NULL, 0, 4);
        
        PolygonObject rocket = new PolygonObject(this, Vector.NULL, Math.PI/2, 4);
        rocket.setFillColor(Color.WHITE);
        rocket.setPoints(
                new double[]{ 0.10, 0.00,-0.10, 0.00},
                new double[]{ 0.25, 0.15, 0.25,-0.25});
        
        CircleObject window = new CircleObject(this, new Vector(-0.07, 0), 0.04);
        window.setFillColor(Color.DARK_GRAY);
        
        CircleObject icon = new CircleObject(this, Vector.NULL, 10);
        icon.setFillColor(Color.LIGHT_GRAY);
        PolygonObject rocketIcon = new PolygonObject(icon, Vector.NULL, 0, 3);
        rocketIcon.setPoints(new Vector[]{
                new Vector(1, 0),
                new Vector(Math.cos(3*Math.PI/4), Math.sin(3*Math.PI/4)),
                new Vector(Math.cos(5*Math.PI/4), Math.sin(5*Math.PI/4)),});
        rocketIcon.setScale(icon.getRadius());
        rocketIcon.setFillColor(Color.DARK_GRAY);
        setIcon(icon);
        
        drawObjects = new TransformObject[]{rocketIcon, rocket, window};
    }
    
    @Override
    public double getMass() {
        return 0.05;
    }

    @Override
    public void update(GameClock clock, GameStateManager gsm, GameInput input) {
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
    public void draw(Graphics2D g) {
        super.draw(g);
        for(TransformObject detail : drawObjects) {
            detail.draw(g);
        }
    }
    
}
