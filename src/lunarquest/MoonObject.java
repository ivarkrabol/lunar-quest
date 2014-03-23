package lunarquest;

import ggf.geom.Vector;
import java.awt.Graphics;

public class MoonObject extends CelestialObject {
    
    private TransformObject[] visualDetails;
    
    public MoonObject(FrameOfReference parent, Vector pos, CelestialObject orbits) {
        super(parent, pos, Vector.NULL, 17000);
        attemptCircularOrbit(orbits);
        setFillColor(LQConstants.COLOR_WHITEISH);
        defineVisualDetails();
    }
    
    private void defineVisualDetails() {
        CircleObject crater1 = new CircleObject(this, new Vector(-0.2, 0.4).mul(getRadius()), 0.3);
        crater1.setFillColor(LQConstants.COLOR_LIGHT_GRAY);
        crater1.setScale(getRadius());
        
        CircleObject crater2 = new CircleObject(this, new Vector(-0.5, -0.45).mul(getRadius()), 0.15);
        crater2.setFillColor(LQConstants.COLOR_LIGHT_GRAY);
        crater2.setScale(getRadius());
        
        CircleObject crater3 = new CircleObject(this, new Vector(-0.55, 0.0).mul(getRadius()), 0.4);
        crater3.setFillColor(LQConstants.COLOR_LIGHT_GRAY);
        crater3.setScale(getRadius());
        
        CircleObject crater4 = new CircleObject(this, new Vector(0.2, -0.5).mul(getRadius()), 0.2);
        crater4.setFillColor(LQConstants.COLOR_LIGHT_GRAY);
        crater4.setScale(getRadius());
        
        visualDetails = new TransformObject[]{
            crater1,
            crater2,
            crater3,
            crater4
        };
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        for(TransformObject detail : visualDetails) {
            detail.draw(g);
        }
    }

}
