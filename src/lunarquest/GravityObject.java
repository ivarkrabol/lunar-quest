package lunarquest;

import ggf.GameClock;
import ggf.GameInput;
import ggf.GameStateManager;
import ggf.Vector;
import java.awt.Graphics;

public class GravityObject extends TransformObject {
    
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
        double gravMagnitude = body.getMass()/distanceSq;
        Vector gravForce = body.getPos().subtract(getPos()).normalized().multiply(gravMagnitude);
        setVel(getVel().add(gravForce));
    }

}