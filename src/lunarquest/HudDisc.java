package lunarquest;

import ggf.geom.Vector;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

public class HudDisc extends TransformObject {
    
    private CircleObject disc;
    private PolygonObject marker;

    public HudDisc(FrameOfReference parent, Vector pos, double radius, Color markerColor) {
        super(parent, pos, 0);
        disc = new CircleObject(this, Vector.NULL, radius);
        marker = new PolygonObject(this, Vector.NULL, 0, 3);
        marker.setPoints(new Vector[]{
                Vector.NULL,
                Vector.directionVector(0.1).mul(2*radius),
                Vector.directionVector(-0.1).mul(2*radius),
        });
        marker.setFillColor(markerColor);
    }

    public void setMarker(PolygonObject marker) {
        this.marker = marker;
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        disc.setFillColor(Color.LIGHT_GRAY);
        disc.draw(g);
        
        BufferedImage img = prepareImage(g);
        Graphics2D g2 = setupClip(img);
        disc.setFillColor(Color.DARK_GRAY);
        disc.draw(g2);
        marker.draw(g2);
        
        marker.setRotation(Math.PI);
        Color c = marker.getFillColor();
        marker.setFillColor(c.darker().darker());
        marker.draw(g2);
        
        g2.dispose();
        g.drawImage(img, 0, 0, null);
        
        marker.setRotation(0);
        marker.setFillColor(c);

        // Copy our intermediate image 
        
    }
    
    private BufferedImage prepareImage(Graphics2D g) {
        int side = 2*(int)disc.getRadius();
        GraphicsConfiguration gc = g.getDeviceConfiguration();
        return gc.createCompatibleImage(LQConstants.WINDOW_WIDTH, LQConstants.WINDOW_HEIGHT, Transparency.TRANSLUCENT);
    }
    
    private Graphics2D setupClip(BufferedImage img) {
        int side = 2*(int)disc.getRadius();
        int innerR = (int)disc.getRadius() - 1;
        
        Graphics2D g2 = img.createGraphics();

        g2.setComposite(AlphaComposite.Clear);
        g2.fillRect(0, 0, side, side);
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        new CircleObject(this, Vector.NULL, innerR).draw(g2);
        g2.setComposite(AlphaComposite.SrcAtop);
        
        return g2;
    }
    
}
