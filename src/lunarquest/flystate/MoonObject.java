package lunarquest.flystate;

import ggf.ShapeObject;
import ggf.Parent;
import ggf.geom.Vector;
import java.awt.Graphics2D;
import lunarquest.CircleObject;
import lunarquest.LQConstants;

public class MoonObject extends CelestialObject {
    
    private ShapeObject[] visualDetails;
    
    public MoonObject(Parent parent, Vector pos, CelestialObject orbitMajor) {
        super(parent, pos, 17000);
        attemptCircularOrbit(orbitMajor);
        setFill(LQConstants.COLOR_WHITEISH);
        defineVisualDetails();
    }
    
    private void defineVisualDetails() {
        CircleObject crater1 = new CircleObject(this, .3);
        crater1.setFill(LQConstants.COLOR_LIGHT_GRAY);
        crater1.setScale(getRadius());
        
        CircleObject crater2 = new CircleObject(this, 0.15);
        crater2.setFill(LQConstants.COLOR_LIGHT_GRAY);
        crater2.setScale(getRadius());
        
        CircleObject crater3 = new CircleObject(this, 0.4);
        crater3.setFill(LQConstants.COLOR_LIGHT_GRAY);
        crater3.setScale(getRadius());
        
        CircleObject crater4 = new CircleObject(this, 0.2);
        crater4.setFill(LQConstants.COLOR_LIGHT_GRAY);
        crater4.setScale(getRadius());
        
        visualDetails = new ShapeObject[]{
            crater1,
            crater2,
            crater3,
            crater4
        };
    }

    @Override
    protected void drawDetailed(Graphics2D g) {
        super.drawDetailed(g);
        for(ShapeObject visualDetail : visualDetails) {
            visualDetail.draw(g);
        }
    }

}
