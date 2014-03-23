package ggf.geom;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Vector extends Point2D.Double {
    
    public static final Vector NULL = new Vector();
    public static final Vector UNIT_X = new Vector(1, 0);
    public static final Vector UNIT_Y = new Vector(0, 1);
    
    

    public Vector(double x, double y) {
        super(x, y);
    }
    
    public Vector(Point2D point) {
        this(point.getX(), point.getY());
    }
    
    public Vector() {
        this(0, 0);
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
    
    public Vector getXComponent() {
        return new Vector(x, 0);
    }
    
    public Vector getYComponent() {
        return new Vector(x, 0);
    }

    public double size() {
        return distance(0, 0);
    }
    
    public double angle() {
        return Math.atan2(y, x);
    }
    
    public Vector add(Vector other) {
        return new Vector(x + other.x, y + other.y);
    }
    
    public Vector mul(double multiplier) {
        return new Vector(x * multiplier, y * multiplier);
    }
    
    public Vector neg() {
        return mul(-1);
    }
    
    public Vector sub(Vector other) {
        return add(other.neg());
    }
    
    public Vector div(double divisor) {
        return mul(1/divisor);
    }
    
    public Vector unit() {
        return div(size());
    }
    
    public Vector rot(double radians) {
        return new Vector(Math.cos(angle() + radians), Math.sin(angle() + radians)).mul(size());
    }
    
    public Vector toSize(double size) {
        return new Vector(this).unit().mul(size);
    }
    
    
    
    public static Vector directionVector(double direction) {
        return new Vector(Math.cos(direction), Math.sin(direction));
    }
    
    public static Vector rectPos(Rectangle2D rect) {
        return new Vector(rect.getX(), rect.getY());
    }
    
    public static Vector rectDiag(Rectangle2D rect) {
        return new Vector(rect.getWidth(), rect.getHeight());
    }
    
    public static Rectangle2D.Double rect(Point2D.Double pos, Point2D.Double diag) {
        return new Rectangle2D.Double(pos.getX(), pos.getY(), diag.getX(), diag.getY());
    }
}
