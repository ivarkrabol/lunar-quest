package lunarquest;

import ggf.GameTime;
import ggf.GameInput;
import ggf.GameStateManager;
import ggf.geom.Vector;
import java.awt.Color;
import java.awt.Graphics2D;

public class GravityObject extends TransformObject {
    
    public static final double G = 0.0000000000667;
    
    private Vector vel;
    private double maxRadius;
    private TransformObject icon;

    public GravityObject(FrameOfReference parent, Vector pos, double rotation, Vector vel) {
        super(parent, pos, rotation);
        this.vel = vel;
        maxRadius = 1;
        icon = new CircleObject(this, Vector.NULL, 6);
        icon.setFillColor(Color.WHITE);
    }

    public Vector getVel() {
        return vel;
    }

    public void setVel(Vector vel) {
        this.vel = vel;
    }

    public double getMaxRadius() {
        return maxRadius;
    }

    public void setMaxRadius(double maxRadius) {
        this.maxRadius = maxRadius;
    }

    public TransformObject getIcon() {
        return icon;
    }

    public void setIcon(TransformObject icon) {
        this.icon = icon;
    }
    
    public double getMass() {
        return 1;
    }

    @Override
    public void draw(Graphics2D g) {
        
        if(getMaxRadius()*getAbsScale() < 6) {
            icon.setFillAlpha(255);
            drawIcon(g);
        } else if(getMaxRadius()*getAbsScale() < 10) {
            drawDetailed(g);
            
            int iconAlpha = (int)(255*(10 - getMaxRadius()*getAbsScale())/4);
            icon.setFillAlpha(iconAlpha);
            drawIcon(g);
        } else {
            drawDetailed(g);
        }
        
    }
    
    protected void drawIcon(Graphics2D g) {
        icon.setScale(1/getAbsScale());
        icon.draw(g);
    }
    
    protected void drawDetailed(Graphics2D g) { }

    @Override
    public void update(GameTime clock, GameStateManager gsm, GameInput input) {
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