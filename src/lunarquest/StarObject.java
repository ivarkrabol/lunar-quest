package lunarquest;

import ggf.geom.Vector;

public class StarObject extends CircleObject {

    public StarObject(FrameOfReference parent, Vector pos, double scale) {
        super(parent, pos, 10);
        setScale(scale);
    }

}
