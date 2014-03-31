package ggf.physics;

import ggf.framework.GameTime;
import ggf.framework.GameStateManager;
import ggf.ShapeObject;
import ggf.Parent;
import ggf.framework.Controls;
import ggf.geom.Vector;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class RigidBody extends ShapeObject {
    
    public static final double G = 0.0000000000667;
    
    private Vector vel;
    private double mass;
    private CollisionPolygon collisionPolygon;
    private RigidBody otherCollisionBody;

    public RigidBody(Parent parent, Vector pos) {
        super(parent);
        setPosition(pos);
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

    protected CollisionPolygon getCollisionPolygon() {
        return collisionPolygon;
    }

    protected void generateCollisionPolygon() {
        this.collisionPolygon = new CollisionPolygon(getShape());
    }
    
    public double getBigRadius() {
        return getCollisionPolygon().getBigRadius();
    }

    @Override
    public void setShape(Shape shape) {
        super.setShape(shape);
        generateCollisionPolygon();
    }

    @Override
    public void update(GameTime time, GameStateManager stateMgr, Controls controls) {
        move(getVel().mul(time.sDeltaTime()));
    }
    
    public void move(Vector deltaPos) {
        setPosition(getPosition().add(deltaPos));
    }
    
    public double[][] requestCollisionPoints(RigidBody initiator) {
        return getCollisionPolygon().getPoints();
    }
    
    public boolean collision(RigidBody otherBody, double deltaTime) {
        if(getPosition().distance(otherBody.getPosition()) > getBigRadius() + otherBody.getBigRadius()) {
            return false;
        }
        otherCollisionBody = otherBody;
        
        Shape worldShape = getLocalTX().createTransformedShape(getShape());
        
        double[][] otherCollisionPolygon = otherBody.requestCollisionPoints(this);
        for(double[] point : otherCollisionPolygon) {
            
            Point2D.Double worldPoint = new Point2D.Double();
            otherBody.getLocalTX().transform(new Point2D.Double(point[0], point[1]), worldPoint);
            
            if(worldShape.contains(worldPoint)) {
                moveFree(otherBody, worldShape, worldPoint, deltaTime);
                return true;
            }
        }
        return false;
    }
    
    public void moveFree(RigidBody otherBody, Shape txShape, Point2D txPoint, double deltaTime) {
        Vector relVel = otherBody.getVel().sub(getVel());
        
        Vector testPoint = new Vector(txPoint);
        double testDistance = relVel.size() * deltaTime;
        double collisionDistance = 0;
        while(testDistance > 0.00001) {
            boolean out = txShape.contains(testPoint);
            testPoint = testPoint.add(relVel.toSize(testDistance).mul(out ? -1 : 1));
            collisionDistance += testDistance*(out ? 1 : -1);
            testDistance *= 0.5;
        }
        collisionDistance = txPoint.distance(testPoint);
        System.out.println("Collision: " + otherBody + " has moved a distance of " + collisionDistance + " into " + this);
        double collisionTime = collisionDistance/relVel.size();
        move(getVel().mul(-collisionTime));
        System.out.println("Moved: " + this + " by " + getVel().mul(-collisionTime));
        otherBody.move(otherBody.getVel().mul(-collisionTime));
        System.out.println("Moved: " + otherBody + " by " + otherBody.getVel().mul(-collisionTime));
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    public void drawCollision(Graphics2D g, AffineTransform viewTx) {
        if(otherCollisionBody == null) return;
        if(getPosition().distance(otherCollisionBody.getPosition()) > getBigRadius() + otherCollisionBody.getBigRadius()) {
            otherCollisionBody = null;
            return;
        }
        
        Shape worldShape = getLocalTX().createTransformedShape(getShape());
        Shape viewShape = viewTx.createTransformedShape(worldShape);
        g.setColor(Color.BLUE);
        g.fill(viewShape);
        
        double[][] otherCollisionPolygon = otherCollisionBody.requestCollisionPoints(this);
        for(double[] point : otherCollisionPolygon) {
            Point2D.Double worldPoint = new Point2D.Double();
            otherCollisionBody.getLocalTX().transform(new Point2D.Double(point[0], point[1]), worldPoint);
            Point2D.Double viewPoint = new Point2D.Double();
            viewTx.transform(worldPoint, viewPoint);
            
            int r = 3;
            g.setColor(Color.GREEN);
            if(worldShape.contains(worldPoint)){
                r = 5;
                g.setColor(Color.RED);
            }
            g.fill(new Ellipse2D.Double(viewPoint.x - r, viewPoint.y - r, 2*r, 2*r));
        }
    }

}