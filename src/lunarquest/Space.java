package lunarquest;

import ggf.geom.Vector;

public class Space extends TransformObject implements FrameOfReference {

    public Space(FrameOfReference parent, Vector pos) {
        super(parent, pos, 0);
    }

    @Override
    public Vector getAbsPos() { return getPos(); }
    @Override
    public double getAbsRotation() { return getRotation(); }
    @Override
    public double getAbsScale() { return getScale(); }

}
