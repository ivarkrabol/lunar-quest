package lunarquest;

import ggf.GameObject;
import ggf.Vector;
import java.awt.Color;

public class TransformObject extends GameObject implements FrameOfReference{
    
    public final Color DEFAULT_FILL_COLOR = Color.DARK_GRAY;
    public final Color DEFAULT_EDGE_COLOR = Color.BLACK;
    
    private FrameOfReference parent;
    private double rotation;
    private double scale;
    private boolean fill;
    private Color fillColor;
    private boolean edge;
    private Color edgeColor;

    public TransformObject(FrameOfReference parent, Vector pos, double rotation) {
        super(pos);
        this.parent = parent;
        this.rotation = rotation;
        scale = 1;
        fill = true;
        fillColor = DEFAULT_FILL_COLOR;
        edge = false;
        edgeColor = DEFAULT_EDGE_COLOR;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public boolean hasFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public boolean hasEdge() {
        return edge;
    }

    public void setEdge(boolean edge) {
        this.edge = edge;
    }

    public Color getEdgeColor() {
        return edgeColor;
    }

    public void setEdgeColor(Color edgeColor) {
        this.edgeColor = edgeColor;
    }

    @Override
    public Vector getAbsPos() {
        Vector rotatedPos = getPos().rotate(parent.getAbsRotation());
        Vector scaledRotatedPos = rotatedPos.multiply(parent.getAbsScale());
        return parent.getAbsPos().add(scaledRotatedPos);
    }

    @Override
    public double getAbsRotation() {
        return parent.getAbsRotation() + getRotation();
    }

    @Override
    public double getAbsScale() {
        return parent.getAbsScale() * getScale();
    }

}
