package ggf;

import ggf.geom.Vector;
import java.awt.geom.AffineTransform;

public class TransformObject implements Parent {
    
    private Vector position;
    private double rotation;
    private double scale;

    public TransformObject() {
        position = new Vector(0,0);
        rotation = 0;
        scale = 1;
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    @Override
    public AffineTransform getAbsoluteTx() {
        AffineTransform tx = new AffineTransform();
        tx.translate(getPosition().getX(), getPosition().getY());
        tx.rotate(getRotation());
        tx.scale(getScale(), getScale());
        return new AffineTransform(tx);
    }
}
