package lunarquest;

import ggf.ShapeObject;
import ggf.Parent;
import ggf.geom.Vector;
import java.awt.geom.Path2D;

public class PolygonObject extends ShapeObject {

    public PolygonObject(Parent parent) {
        super(parent);
    }
    
    public void setPoints(Vector[] points) {
        int num = points.length;
        if(num <= 2) {
            throw new IllegalArgumentException("Polygon needs to have at least 2 points.");
        }
        
        Path2D.Double path = new Path2D.Double();
        path.moveTo(points[0].getX(), points[0].getY());
        for(int i = 1; i < num; i++) {
            path.lineTo(points[i].getX(), points[i].getY());
        }
        path.closePath();
        
        setShape(path);
    }
    
    public void setPoints(double[] pointsX, double[] pointsY) {
        
        Vector[] points = new Vector[pointsX.length];
        for(int i = 0; i < pointsX.length; i++) {
            points[i] = new Vector(pointsX[i], pointsY[i]);
        }
        setPoints(points);
    }
    
}
