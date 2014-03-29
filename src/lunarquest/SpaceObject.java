package lunarquest;

import ggf.ShapeObject;
import ggf.Parent;
import ggf.geom.Vector;
import ggf.physics.RigidBody;
import static ggf.physics.RigidBody.G;
import java.awt.Color;
import java.awt.Graphics2D;

public class SpaceObject extends RigidBody {
    
    private ShapeObject icon;

    public SpaceObject(Parent parent, Vector pos) {
        super(parent, pos);
        icon = new CircleObject(this, 6);
        icon.setFill(Color.WHITE);
    }

    @Override
    public void draw(Graphics2D g) {
        
        if(false) { //TODO: Draw icon condition
            icon.setAlpha(255);
            drawIcon(g);
        } else if(false) { //TODO: Draw fade condition
            drawDetailed(g);
            
            // finne riktig alpha
            icon.setAlpha(127);
            drawIcon(g);
        } else {
            drawDetailed(g);
        }
        
    }
    
    protected void drawIcon(Graphics2D g) {
        icon.setScale(1); //TODO: Scale icon
        icon.draw(g);
    }
    
    protected void drawDetailed(Graphics2D g) { }
    
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
