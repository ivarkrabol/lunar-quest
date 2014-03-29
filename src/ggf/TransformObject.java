package ggf;

import ggf.geom.Vector;
import java.awt.geom.AffineTransform;

public class TransformObject implements Parent {
    
    private Vector pos;
    private double rot;
    private double scale;

    public TransformObject() {
        pos = new Vector(0,0);
        rot = 0;
        scale = 1;
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
        AffineTransform tx = new AffineTransform();
        tx.translate(getPos().getX(), getPos().getY());
        tx.rotate(getRot());
        tx.scale(getScale(), getScale());
        return new AffineTransform(tx);
    }
}
