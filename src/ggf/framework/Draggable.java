package ggf.framework;

import java.awt.Shape;
import java.awt.geom.Point2D;

public interface Draggable {
    public Shape getShape();
    public void drag(Point2D deltaPos);
}
