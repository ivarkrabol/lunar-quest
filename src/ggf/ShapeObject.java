package ggf;

import ggf.framework.GameStateManager;
import ggf.framework.GameTime;
import ggf.framework.Controls;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class ShapeObject extends TransformObject implements GameObject {
    
    private Shape shape;
    private Color fill;
    private Color edge;
    private Parent parent;

    public ShapeObject(Parent parent) {
        this.parent = parent;
        
        shape = new Rectangle2D.Double();
        fill = new Color(0, 0, 0, 0);
        edge = new Color(0 ,0, 0, 0);
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
    
    public void setAlpha(int alpha) {
        Color c = getFill();
        c = new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha);
        setFill(c);
        c = getEdge();
        c = new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha);
        setEdge(c);
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
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

    @Override
    public AffineTransform getTransform() {
        AffineTransform parentTx = getParent().getTransform();
        parentTx.translate(getPos().getX(), getPos().getY());
        parentTx.rotate(getRot());
        parentTx.scale(getScale(), getScale());
        return new AffineTransform(parentTx);
    }

}
