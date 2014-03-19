package lunarquest;

import ggf.geom.Vector;
import java.awt.Graphics;
import java.util.Iterator;

public class PolygonObject extends TransformObject implements Iterator<Vector> {
    
    private Vector[] points;
    private int pointsAdded;
    private int pointsIterated;

    public PolygonObject(FrameOfReference parent, Vector pos, double rotation, int num) {
        super(parent, pos, rotation);
        points = new Vector[num];
        pointsAdded = 0;
        pointsIterated = 0;
    }

    public int[] getIntPointsX() {
        int[] intPointsX = new int[num()];
        for(int i = 0; i < num(); i++) {
            intPointsX[i] = (int)points[i].x;
        }
        return intPointsX;
    }

    public int[] getIntPointsY() {
        int[] intPointsY = new int[num()];
        for(int i = 0; i < num(); i++) {
            intPointsY[i] = (int)points[i].y;
        }
        return intPointsY;
    }
    
    public Vector getPoint(int i) {
        return points[i];
    }
    
    public void addPoint(Vector point) {
        if(pointsAdded >= num()) throw new IndexOutOfBoundsException("Polygon is already full.");
        points[pointsAdded++] = point;
    }
    
    public void setPoints(Vector[] points) {
        if(points.length != num()) {
            throw new IllegalArgumentException("Array needs to have exactly "+num()+" elements.");
        }
        this.points = points;
        pointsAdded = num();
    }
    
    public void setPoints(double[] pointsX, double[] pointsY) {
        if(pointsX.length != num() || pointsY.length !=num()) {
            throw new IllegalArgumentException("Both arrays need to have exactly "+num()+" elements.");
        }
        for(int i = 0; i < num(); i++) {
            points[i] = new Vector(pointsX[i], pointsY[i]);
        }
        pointsAdded = num();
    }
    
    public int num() {
        return points.length;
    }

    @Override
    public void draw(Graphics g) {
        if(!hasFill() && !hasEdge()) return;
        Vector[] absolutePoints = transformAbsolutePoints();
        int[] absoluteIntArrayX = convertToAbsoluteIntArrayX(absolutePoints);
        int[] absoluteIntArrayY = convertToAbsoluteIntArrayY(absolutePoints);
        if(hasFill()) {
            g.setColor(getFillColor());
            g.fillPolygon(absoluteIntArrayX, absoluteIntArrayY, num());
        }
        if(hasEdge()) {
            g.setColor(getEdgeColor());
            g.drawPolygon(absoluteIntArrayX, absoluteIntArrayY, num());
        }
    }
    
    private Vector[] transformAbsolutePoints() {
        Vector[] absolutePoints = new Vector[num()];
        for(int i = 0; i < num(); i++) {
            absolutePoints[i] = getAbsPos().add(points[i].rot(getAbsRotation()).mul(getAbsScale()));
        }
        return absolutePoints;
    }
    
    private int[] convertToAbsoluteIntArrayX(Vector[] absolutePoints) {
        int[] absoluteIntArrayX = new int[num()];
        for(int i = 0; i < num(); i++) {
            absoluteIntArrayX[i] = (int)absolutePoints[i].x;
        }
        return absoluteIntArrayX;
    }
    
    private int[] convertToAbsoluteIntArrayY(Vector[] absolutePoints) {
        int[] absoluteIntArrayY = new int[num()];
        for(int i = 0; i < num(); i++) {
            absoluteIntArrayY[i] = (int)absolutePoints[i].y;
        }
        return absoluteIntArrayY;
    }

    @Override
    public boolean hasNext() {
        return pointsIterated < num();
    }

    @Override
    public Vector next() {
        if(!hasNext()) throw new IndexOutOfBoundsException("No more coordinates.");
        return getPoint(pointsIterated++);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported.");
    }
    
}
