package lunarquest;

import ggf.Parent;
import ggf.geom.Vector;
import ggf.geom.Vector;
import java.awt.geom.Rectangle2D;

public class RectangleObject extends PolygonObject {
    
    private Vector diagonal;

    public RectangleObject(Parent parent, Rectangle2D rect) {
        super(parent);
        setDiagonal(Vector.rectangleDiagonal(rect));
    }

    public Vector getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(Vector diagonal) {
        this.diagonal = diagonal;
        updatePolygon();
    }
    
    public double getWidth() {
        return diagonal.getX();
    }

    public void setWidth(double width) {
        diagonal.setX(width);
        updatePolygon();
    }

    public double getHeight() {
        return diagonal.getY();
    }

    public void setHeight(double height) {
        diagonal.setY(height);
        updatePolygon();
    }

    public Rectangle2D getRectangle() {
        return new Rectangle2D.Double(
                getPosition().getX(), getPosition().getY(),
                getDiagonal().getX(), getDiagonal().getY());
    }

    public void setRectangle(Rectangle2D rectangle) {
        setPosition(new Vector(Vector.rectanglePosition(rectangle)));
        setDiagonal(Vector.rectangleDiagonal(rectangle));
    }
    
    private void updatePolygon() {
        setPoints(new Vector[]{
             Vector.NULL,
             getDiagonal().getXComponent(),
             getDiagonal(),
             getDiagonal().getYComponent()
        });
    }
    
}
