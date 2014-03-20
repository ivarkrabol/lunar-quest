package lunarquest;

import ggf.GameClock;
import ggf.GameInput;
import ggf.GameStateManager;
import ggf.geom.Vector;
import java.awt.Graphics;

public class GravityObject extends TransformObject {
    
    public static final double G = 0.0000000000667;
    
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
    
    public double getMass() {
        return 1;
    }

    @Override
    public void draw(Graphics graphics) {
    }

    @Override
    public void update(GameClock clock, GameStateManager gsm, GameInput input) {
        setPos(getPos().add(vel.mul(clock.sDeltaTime())));
    }
    
    public void gravitateTowards(CelestialObject body, double deltaTime) {
        
        // Se serviett for utregning;
        
        Vector thisToBody = body.getPos().sub(getPos());
        double distanceCubed = Math.pow(thisToBody.size(), 3);
        Vector acc = thisToBody.mul(G * body.getMass() / distanceCubed);
        setVel(getVel().add(acc.mul(deltaTime)));
    }
    
    public void attemptCircularOrbit(CelestialObject body) {
        Vector thisToBody = body.getPos().sub(getPos());
        double distance = thisToBody.size();
        double velocity = Math.sqrt(G*(body.getMass() + getMass()) / distance);
        Vector direction = new Vector(thisToBody.y, -thisToBody.x).unit();
        setVel(direction.mul(velocity).add(body.getVel()));
    }

}