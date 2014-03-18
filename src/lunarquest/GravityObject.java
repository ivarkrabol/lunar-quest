package lunarquest;

import ggf.GameClock;
import ggf.GameInput;
import ggf.GameStateManager;
import ggf.Vector;
import java.awt.Graphics;

public class GravityObject extends TransformObject {
    
    public static final double GRAVITATIONAL_CONSTANT = 0.0000000000006;
    
    private Vector vel;

    public GravityObject(FrameOfReference parent, Vector pos, double rotation, Vector vel) {
        super(parent, pos, rotation);
        this.vel = vel;
    }

    public Vector getVel() {
        return vel;
    }

    public void setVel(Vector vel) {
        this.vel = vel;
    }

    @Override
    public void draw(Graphics graphics) {
    }

    @Override
    public void update(GameClock clock, GameStateManager gsm, GameInput input) {
        setPos(getPos().add(vel.multiply(clock.updateTime())));
    }
    
    public void gravitateTowards(CelestialObject body) {
        double distanceSq = Math.pow(getPos().distance(body.getPos()), 2);
        double gravMagnitude = GRAVITATIONAL_CONSTANT*body.getMass()/distanceSq;
        Vector gravForce = body.getPos().subtract(getPos()).normalized().multiply(gravMagnitude);
        System.out.println("gravForce: ["+gravForce.x+","+gravForce.y+"], length: "+gravForce.magnitude());
        setVel(getVel().add(gravForce));
    }

}