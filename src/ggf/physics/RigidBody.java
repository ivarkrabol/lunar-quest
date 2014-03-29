package ggf.physics;

import ggf.framework.GameTime;
import ggf.framework.GameStateManager;
import ggf.ShapeObject;
import ggf.Parent;
import ggf.framework.Controls;
import ggf.geom.Vector;
import java.awt.Shape;

public class RigidBody extends ShapeObject {
    
    public static final double G = 0.0000000000667;
    
    private Vector vel;
    private double mass;
    private CollisionPolygon collisionPolygon;

    public RigidBody(Parent parent, Vector pos) {
        super(parent);
        setPos(pos);
        setVel(Vector.NULL);
        setMass(1);
    }

    public Vector getVel() {
        return vel;
    }

    public void setVel(Vector vel) {
        this.vel = vel;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    private CollisionPolygon getCollisionPolygon() {
        return collisionPolygon;
    }

    private void setCollisionPolygon(CollisionPolygon collisionPolygon) {
        this.collisionPolygon = collisionPolygon;
    }
    
    public double getBigRadius() {
        return getCollisionPolygon().getBigRadius();
    }

    @Override
    public void setShape(Shape shape) {
        super.setShape(shape);
        setCollisionPolygon(collisionPolygon);
    }

    @Override
    public void update(GameTime time, GameStateManager stateMgr, Controls controls) {
        setPos(getPos().add(getVel().mul(time.sDeltaTime())));
    }
    
    public double[][] requestCollisionPoints(RigidBody initiator) {
        return getCollisionPolygon().getPoints();
    }
    
    public boolean collision(RigidBody otherBody) {
        double[][] otherCollisionPolygon = otherBody.requestCollisionPoints(this);
        for(double[] point : otherCollisionPolygon) {
            if(getShape().contains(point[0], point[1])) return true;
        }
        return false;
    }

}