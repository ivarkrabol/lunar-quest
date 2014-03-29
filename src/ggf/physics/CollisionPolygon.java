package ggf.physics;

import ggf.geom.Vector;
import java.awt.Shape;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class CollisionPolygon {
    
    public static final double DEFAULT_SEGMENT_LENGTH = 0.005;
    
    private double[][] points; 
    private double bigRadius;
    
    public CollisionPolygon(Shape shape, double segmentLength) {
        bigRadius = 0;
        PathIterator iterator = shape.getPathIterator(null);
        ArrayList<double[]> pointsList = new ArrayList();
        double[] currentCoords = new double[2];
        while(!iterator.isDone()) {
            double[] nextCoords = new double[2];
            iterator.currentSegment(nextCoords);
            bigRadius = Math.max(bigRadius, new Vector(nextCoords[0], nextCoords[1]).size());
            if(Point2D.distance(nextCoords[0], nextCoords[1],
                    currentCoords[0], currentCoords[1]) >= segmentLength) {
                currentCoords = nextCoords;
                pointsList.add(currentCoords);
            }
        }
        points = pointsList.toArray(points);
    }

    public double[][] getPoints() {
        return points;
    }

    public double getBigRadius() {
        return bigRadius;
    }
    
    public CollisionPolygon(Shape shape) {
        this(shape, DEFAULT_SEGMENT_LENGTH);
    }
}
