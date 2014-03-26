package ggf;

import ggf.framework.GameStateManager;
import ggf.framework.GameTime;
import ggf.framework.Controls;
import ggf.geom.Vector;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class ShapeObject implements GameObject, TransformObject {
    
    private Shape shape;
    private Color fill;
    private Color edge;
    private TransformObject parent;
    private Vector pos;
    private double rot;
    private double scale;

    public ShapeObject(TransformObject parent) {
        this.parent = parent;
        
        shape = new Rectangle2D.Double();
        fill = new Color(0, 0, 0, 0);
        edge = new Color(0 ,0, 0, 0);
        pos = new Vector(0,0);
        rot = 0;
        scale = 1;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public Color getFill() {
        return fill;
    }

    public void setFill(Color fill) {
        this.fill = fill;
    }

    public Color getEdge() {
        return edge;
    }

    public void setEdge(Color edge) {
        this.edge = edge;
    }

    public TransformObject getParent() {
        return parent;
    }

    public void setParent(TransformObject parent) {
        this.parent = parent;
    }

    public Vector getPos() {
        return pos;
    }

    public void setPos(Vector pos) {
        this.pos = pos;
    }

    public double getRot() {
        return rot;
    }

    public void setRot(double rot) {
        this.rot = rot;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    @Override
    public AffineTransform getTransform() {
        AffineTransform parentTx = parent.getTransform();
        parentTx.translate(getPos().getX(), getPos().getY());
        parentTx.rotate(getRot());
        parentTx.scale(getScale(), getScale());
        return new AffineTransform(parentTx);
    }

    @Override
    public void update(GameTime time, GameStateManager stateMgr, Controls controls) {
    }

    @Override
    public void draw(Graphics2D g) {
        Shape txShape = getTransform().createTransformedShape(getShape());
        g.setColor(getFill());
        g.fill(txShape);
        g.setColor(getEdge());
        g.draw(txShape);
    }

}
