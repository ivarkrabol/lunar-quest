package lunarquest.flystate;

import ggf.Parent;
import ggf.ShapeObject;
import ggf.geom.Vector;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import lunarquest.CircleObject;
import lunarquest.LQConstants;
import lunarquest.PolygonObject;

public class HudDisc extends ShapeObject {
    
    private CircleObject disc;
    private PolygonObject marker;

    public HudDisc(Parent parent, double radius, Color markerColor) {
        super(parent);
        disc = new CircleObject(this, radius);
        marker = new PolygonObject(this);
        marker.setPoints(new Vector[]{
                Vector.NULL,
                Vector.directionVector(0.1).mul(2*radius),
                Vector.directionVector(-0.1).mul(2*radius),
        });
        marker.setFill(markerColor);
    }

    public void setMarker(PolygonObject marker) {
        this.marker = marker;
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        disc.setFill(Color.LIGHT_GRAY);
        disc.draw(g);
        
        BufferedImage img = prepareImage(g);
        Graphics2D g2 = setupClip(img);
        disc.setFill(Color.DARK_GRAY);
        disc.draw(g2);
        marker.draw(g2);
        
        marker.setRot(Math.PI);
        Color c = marker.getFill();
        marker.setFill(c.darker().darker());
        marker.draw(g2);
        
        g2.dispose();
        g.drawImage(img, 0, 0, null);
        
        marker.setRot(0);
        marker.setFill(c);
        
    }
    
    private BufferedImage prepareImage(Graphics2D g) {
        int diam = 2*(int)disc.getRadius();
        GraphicsConfiguration gc = g.getDeviceConfiguration();
        return gc.createCompatibleImage(LQConstants.WINDOW_WIDTH, LQConstants.WINDOW_HEIGHT, Transparency.TRANSLUCENT);
    }
    
    private Graphics2D setupClip(BufferedImage img) {
        int diam = 2*(int)disc.getRadius();
        int innerR = (int)disc.getRadius() - 1;
        
        Graphics2D g2 = img.createGraphics();

        g2.setComposite(AlphaComposite.Clear);
        g2.fillRect(0, 0, diam, diam);
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        new CircleObject(this, innerR).draw(g2);
        g2.setComposite(AlphaComposite.SrcAtop);
        
        return g2;
    }
    
}
