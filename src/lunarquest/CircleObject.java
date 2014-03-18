package lunarquest;

import ggf.Vector;
import java.awt.Graphics;

public class CircleObject extends TransformObject {
    
    private double radius;

    public CircleObject(FrameOfReference parent, Vector pos, double radius) {
        super(parent, pos, 0);
        setRadius(radius);
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public void draw(Graphics g) {
        if(!hasFill() && !hasEdge()) return;
        double absoluteRadius = radius*getAbsScale();
        if(hasFill()) {
            g.setColor(getFillColor());
            g.fillOval((int)(getAbsPos().x - absoluteRadius), (int)(getAbsPos().y - absoluteRadius),
                    (int)(2*absoluteRadius), (int)(2*absoluteRadius));
        }
        if(hasEdge()) {
            g.setColor(getEdgeColor());
            g.drawOval((int)(getAbsPos().x - absoluteRadius), (int)(getAbsPos().y - absoluteRadius),
                    (int)(2*absoluteRadius), (int)(2*absoluteRadius));
        }
    }

}
