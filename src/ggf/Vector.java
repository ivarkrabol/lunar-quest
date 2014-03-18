package ggf;

import java.awt.Point;
import java.awt.geom.Point2D;

public class Vector extends Point2D.Double {
    
    public static final Vector NULL = new Vector(0, 0);
    public static final Vector UNIT_X = new Vector(1, 0);
    public static final Vector UNIT_Y = new Vector(0, 1);

    public Vector(double x, double y) {
        super(x, y);
    }
    
    public Vector(Vector vector) {
        this(vector.x, vector.y);
    }
    
    public Vector(Point point) {
        this(point.x, point.y);
    }

    public double magnitude() {
        return distance(0, 0);
    }
    
    public double angle() {
        return Math.atan2(y, x);
    }
    
    public Vector add(Vector other) {
        Vector vector = new Vector(this);
        vector.x += other.x;
        vector.y += other.y;
        return vector;
    }
    
    public Vector multiply(double multiplier) {
        Vector vector = new Vector(this);
        vector.x *= multiplier;
        vector.y *= multiplier;
        return vector;
    }
    
    public Vector negative() {
        return multiply(-1);
    }
    
    public Vector subtract(Vector other) {
        return add(other.negative());
    }
    
    public Vector divide(double divisor) {
        return multiply(1/divisor);
    }
    
    public Vector normalized() {
        return divide(magnitude());
    }
    
    public Vector rotate(double radians) {
        return new Vector(Math.cos(angle() + radians), Math.sin(angle() + radians)).multiply(magnitude());
    }
    
    public double distance(Vector other) {
        return other.subtract(this).magnitude();
    }
    
    public boolean isInside(Rectangle rect) {
        return rect.pos.x <= x && rect.pos.x + rect.diag.x >= x
                && rect.pos.y <= y && rect.pos.y + rect.diag.y >= y;
    }
}
