package ggf;

import java.awt.Shape;
import java.awt.geom.Point2D;

public interface GameDraggable {
    public Shape getShape();
    public void drag(Point2D deltaPos);
}
