package lunarquest;

import ggf.Vector;

public class CelestialObject extends CircleObject {
    
    public static final double DENSITY_CONSTANT = 0.00004;

    public CelestialObject(FrameOfReference parent, Vector pos, double radius) {
        super(parent, pos, radius);
        
    }
    
    public double getMass() {
        return Math.pow(getRadius(),3)*DENSITY_CONSTANT;
    }

}