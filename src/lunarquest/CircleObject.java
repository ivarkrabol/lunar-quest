package lunarquest;

import ggf.ShapeObject;
import ggf.Parent;
import ggf.geom.Vector;
import java.awt.geom.Ellipse2D;

public class CircleObject extends ShapeObject {
    
    private double radius;

    public CircleObject(Parent parent, double radius) {
        super(parent);
        setRadius(radius);
        setShape(new Ellipse2D.Double(-radius, -radius, 2*radius, 2*radius));
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

}
