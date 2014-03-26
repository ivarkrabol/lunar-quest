package ggf.physics;

import java.awt.Shape;
import java.awt.geom.PathIterator;
import java.util.ArrayList;

public class CollisionPolygon {
    
    private double[][] points; 
    
    public CollisionPolygon(Shape shape) {
        PathIterator iterator = shape.getPathIterator(null);
        ArrayList<double[]> pointsList = new ArrayList();
        double[] currentCoords = new double[2];
        while(!iterator.isDone()) {
            iterator.currentSegment(currentCoords);
            pointsList.add(currentCoords);
        }
        points = pointsList.toArray(points);
    }
}
