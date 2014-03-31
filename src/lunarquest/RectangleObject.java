package lunarquest;

import ggf.Parent;
import ggf.geom.Vector;
import java.awt.geom.Rectangle2D;

public class RectangleObject extends PolygonObject {
    
    private Vector diag;

    public RectangleObject(Parent parent, Rectangle2D rect) {
        super(parent);
        setDiag(Vector.rectDiag(rect));
    }

    public Vector getDiag() {
        return diag;
    }

    public void setDiag(Vector diag) {
        this.diag = diag;
        updatePolygon();
    }
    
    public double getWidth() {
        return diag.getX();
    }

    public void setWidth(double width) {
        diag.setX(width);
        updatePolygon();
    }

    public double getHeight() {
        return diag.getY();
    }

    public void setHeight(double height) {
        diag.setY(height);
        updatePolygon();
    }

    public Rectangle2D getRect() {
        return Vector.rect(getPosition(), getDiag());
    }

    public void setRect(Rectangle2D rect) {
        setPosition(Vector.rectPos(rect));
        setDiag(Vector.rectDiag(rect));
    }
    
    private void updatePolygon() {
        setPoints(new Vector[]{
             Vector.NULL,
             getDiag().getXComponent(),
             getDiag(),
             getDiag().getYComponent()
        });
    }
    
}
