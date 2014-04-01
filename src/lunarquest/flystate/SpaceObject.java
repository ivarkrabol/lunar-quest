package lunarquest.flystate;

import ggf.ShapeObject;
import ggf.Parent;
import ggf.geom.Vector;
import ggf.geom.Vector;
import ggf.physics.RigidBody;
import static ggf.physics.RigidBody.G;
import java.awt.Color;
import java.awt.Graphics2D;
import lunarquest.CircleObject;

public class SpaceObject extends RigidBody {
    
    private ShapeObject icon;

    public SpaceObject(Parent parent, Vector pos) {
        super(parent, pos);
        icon = new CircleObject(this, 6);
        icon.setFill(Color.WHITE);
    }

    @Override
    public final void draw(Graphics2D g) {
        
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
    
    public void gravitateTowards(SpaceObject body, double deltaTime) {
        Vector thisToBody = body.getPosition().sub(getPosition());
        double distanceCubed = Math.pow(thisToBody.size(), 3);
        Vector acc = thisToBody.mul(G * body.getMass() / distanceCubed);
        move(acc.mul(deltaTime));
    }
    
    public void attemptCircularOrbit(SpaceObject body) {
        Vector thisToBody = body.getPosition().sub(getPosition());
        double distance = thisToBody.size();
        double velocity = Math.sqrt(G*(body.getMass() + getMass()) / distance);
        Vector direction = new Vector(thisToBody.y, -thisToBody.x).unit();
        setVel(direction.mul(velocity).add(body.getVel()));
    }
    
    
}
