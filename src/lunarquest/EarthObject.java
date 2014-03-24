package lunarquest;

import ggf.geom.Vector;
import java.awt.Color;
import java.awt.Graphics2D;

public class EarthObject extends CelestialObject {
    
    private TransformObject[] visualDetails;

    public EarthObject(FrameOfReference parent, Vector pos) {
        super(parent, pos, Vector.NULL, 60000);
        setFillColor(LQConstants.COLOR_BLUE);
        defineVisualDetails();
        getIcon().setFillColor(LQConstants.COLOR_BLUE);
    }
    
    private void defineVisualDetails() {
        PolygonObject greenland = new PolygonObject(this, Vector.NULL, 0, 6);
        greenland.setFillColor(Color.WHITE);
        greenland.setScale(getRadius());
        greenland.setPoints(
                new double[]{-0.13, 0.10, 0.13, 0.00,-0.07,-0.10},
                new double[]{ 0.20, 0.15, 0.40, 0.50, 0.63, 0.53});
        
        PolygonObject northAmerica = new PolygonObject(this, Vector.NULL, 0, 13);
        northAmerica.setFillColor(LQConstants.COLOR_GREEN);
        northAmerica.setScale(getRadius());
        northAmerica.setPoints(
                new double[]{-0.46,-0.35,-0.30,-0.44,-0.42,-0.25,-0.13,-0.68,-0.78,-0.78,-0.72,-0.52,-0.54},
                new double[]{-0.30,-0.23, 0.36, 0.37, 0.52, 0.43, 0.67, 0.68, 0.46, 0.10, 0.08,-0.23,-0.25});
        
        PolygonObject eurasiaGreen = new PolygonObject(this, Vector.NULL, 0, 17);
        eurasiaGreen.setFillColor(LQConstants.COLOR_GREEN);
        eurasiaGreen.setScale(getRadius());
        eurasiaGreen.setPoints(
                new double[]{-0.38,-0.30,-0.02, 0.00, 0.60, 0.80, 0.80, 0.62, 0.44, 0.57, 0.55, 0.51, 0.35, 0.45, 0.40, 0.10, 0.00},
                new double[]{-0.30,-0.50,-0.60,-0.80,-0.70, 0.00, 0.38, 0.60, 0.50, 0.50, 0.38, 0.37, 0.10, 0.02,-0.10,-0.20,-0.35});
        
        PolygonObject eurasiaBeige = new PolygonObject(this, Vector.NULL, 0, 7);
        eurasiaBeige.setFillColor(LQConstants.COLOR_BEIGE);
        eurasiaBeige.setScale(getRadius());
        eurasiaBeige.setPoints(
                new double[]{ 0.10, 0.63, 0.75, 0.80, 0.90, 0.70, 0.55},
                new double[]{-0.70,-0.73,-0.50,-0.50,-0.20,-0.10,-0.55});
        
        visualDetails = new TransformObject[]{
            greenland,
            northAmerica,
            eurasiaGreen,
            eurasiaBeige,
        };
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        for(TransformObject detail : visualDetails) {
            detail.draw(g);
        }
    }
    
}
