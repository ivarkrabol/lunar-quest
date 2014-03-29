package lunarquest.flystate;

import ggf.Parent;
import ggf.physics.RigidBody;
import ggf.geom.Vector;
import ggf.physics.CollisionPolygon;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import lunarquest.CircleObject;

public class CelestialObject extends SpaceObject {
    
    public static final double DENSITY_CONSTANT = 19000.0;
    
    private CircleObject circle;

    public CelestialObject(Parent parent, Vector pos, double radius) {
        super(parent, pos);
        circle = new CircleObject(this, radius);
    }
    
    @Override
    public double getMass() {
        return Math.pow(getRadius(),3)*DENSITY_CONSTANT;
    }
    
    public double getRadius() {
        return circle.getRadius();
    }
    
    public void setRadius(double radius) {
        circle.setRadius(radius);
    }
    
    @Override
    public Color getFill() {
        return circle.getFill();
    }
    
    @Override
    public void setFill(Color color) {
        circle.setFill(color);
    }

    @Override
    public double[][] requestCollisionPoints(RigidBody initiator) {
        Vector flatMiddle = initiator.getPos().sub(getPos()).toSize(getRadius());
        Vector lowMiddle = flatMiddle.toSize(getRadius() - 1);
        Vector flatRight = flatMiddle.add(flatMiddle.rot(Math.PI/2).toSize(initiator.getBigRadius()));
        Vector flatLeft = flatMiddle.mul(2).sub(flatRight);
        
        Path2D.Double collisionPath = new Path2D.Double();
        collisionPath.moveTo(lowMiddle.getX(), lowMiddle.getY());
        collisionPath.lineTo(flatRight.getX(), flatRight.getY());
        collisionPath.lineTo(flatLeft.getX(), flatLeft.getY());
        collisionPath.closePath();
        
        CollisionPolygon collisionPolygon = new CollisionPolygon(collisionPath);
        
        return collisionPolygon.getPoints();
    }

    @Override
    public boolean collision(RigidBody otherBody) {
        
        if(otherBody instanceof CelestialObject) {
            CelestialObject otherCB = (CelestialObject)otherBody;
            return getPos().distance(otherCB.getPos()) < getRadius()+otherCB.getRadius();
        }
        
        return super.collision(otherBody);
    }

    @Override
    protected void drawDetailed(Graphics2D g) {
        circle.draw(g);
    }
    
}