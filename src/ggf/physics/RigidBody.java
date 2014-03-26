package ggf.physics;

import ggf.framework.GameTime;
import ggf.framework.GameStateManager;
import ggf.ShapeObject;
import ggf.TransformObject;
import ggf.framework.Controls;
import ggf.geom.Vector;
import java.awt.Shape;
import java.awt.geom.Path2D;

public class RigidBody extends ShapeObject {
    
    public static final double G = 0.0000000000667;
    
    private Vector vel;
    private double mass;
    private CollisionPolygon collisionPolygon;

    public RigidBody(TransformObject parent, Vector pos) {
        super(parent);
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

    public CollisionPolygon getCollisionPolygon() {
        return collisionPolygon;
    }

    @Override
    public void setShape(Shape shape) {
        super.setShape(shape);
        collisionPolygon = new CollisionPolygon(shape);
    }

    @Override
    public void update(GameTime time, GameStateManager stateMgr, Controls controls) {
        setPos(getPos().add(getVel().mul(time.sDeltaTime())));
    }
    
    public boolean collision(RigidBody otherBody) {
        
    }

}