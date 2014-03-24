package lunarquest;

import ggf.geom.Vector;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class RectangleObject extends TransformObject {
    
    private PolygonObject polygon;
    private Vector diag;

    public RectangleObject(FrameOfReference parent, Rectangle2D rect, double rotation) {
        super(parent, Vector.rectPos(rect), rotation);
        polygon = new PolygonObject(this, Vector.rectPos(rect), rotation, 4);
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
        return Vector.rect(getPos(), getDiag());
    }

    public void setRect(Rectangle2D rect) {
        setPos(Vector.rectPos(rect));
        setDiag(Vector.rectDiag(rect));
    }

    @Override
    public void setX(double x) {
        super.setX(x);
        updatePolygon();
        
    }

    @Override
    public void setY(double y) {
        super.setY(y);
        updatePolygon();
    }
    
    private void updatePolygon() {
        polygon.setPoints(new Vector[]{
             Vector.NULL,
             getDiag().getXComponent(),
             getDiag(),
             getDiag().getYComponent()
        });
    }
    
    
    
    
    
    

    @Override
    public void setFill(boolean fill) {
        super.setFill(fill);
        polygon.setFill(fill);
    }

    @Override
    public void setFillColor(Color fillColor) {
        super.setFillColor(fillColor);
        polygon.setFillColor(fillColor);
    }

    @Override
    public void setEdge(boolean edge) {
        super.setEdge(edge);
        polygon.setEdge(edge);
    }

    @Override
    public void setEdgeColor(Color edgeColor) {
        super.setEdgeColor(edgeColor);
        polygon.setEdgeColor(edgeColor);
    }

    @Override
    public void draw(Graphics2D graphics) {
        super.draw(graphics);
        polygon.draw(graphics);
    }
    
    

}
