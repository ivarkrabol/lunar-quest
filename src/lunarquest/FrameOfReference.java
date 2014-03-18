package lunarquest;

import ggf.Vector;

public interface FrameOfReference {
    public Vector getAbsPos();
    public double getAbsRotation();
    public double getAbsScale();
}
