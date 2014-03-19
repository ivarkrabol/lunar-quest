package lunarquest;

import ggf.geom.Vector;

public interface FrameOfReference {
    public Vector getAbsPos();
    public double getAbsRotation();
    public double getAbsScale();
}
