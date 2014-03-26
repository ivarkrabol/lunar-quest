package lunarquest;

import ggf.framework.GameTime;
import ggf.framework.InputHandler;
import ggf.UpdateObject;
import ggf.framework.GameStateManager;
import ggf.geom.Vector;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Hud extends UpdateObject {

    private static final Dimension screen = new Dimension(LQConstants.WINDOW_WIDTH, LQConstants.WINDOW_HEIGHT);
    private static final Point center = new Point(screen.width/2, screen.height/2);
    
    private RocketObject rocket;
    private MoonObject moon;
    private EarthObject earth;
    private TransformObject discRef;
    private HudDisc velDisc;
    private HudDisc moonDisc;
    private HudDisc earthDisc;
    
    public Hud(Vector pos, RocketObject rocket, MoonObject moon, EarthObject earth) {
        super(pos);
        
        this.rocket = rocket;
        this.moon = moon;
        this.earth = earth;
        
        discRef = new TransformObject(null, new Vector(center.x, screen.height-25), -Math.PI/2) {
            @Override public double getAbsScale() { return 1; }
            @Override public double getAbsRotation() { return getRotation()-Math.PI/2; }
            @Override public Vector getAbsPos() { return getPos(); }
        };
        
        velDisc = new HudDisc(discRef, Vector.NULL, 70, LQConstants.COLOR_GREEN);
        moonDisc = new HudDisc(discRef, Vector.NULL, 55, LQConstants.COLOR_WHITEISH);
        earthDisc = new HudDisc(discRef, Vector.NULL, 40, LQConstants.COLOR_BLUE);
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        drawBackground(g);
        
        velDisc.draw(g);
        moonDisc.draw(g);
        earthDisc.draw(g);
        
        drawForeground(g);
    }

    @Override
    public void update(GameTime clock, GameStateManager gsm, InputHandler input) {
        super.update(clock, gsm, input);
        
        discRef.setRotation(-rocket.getRotation());
        velDisc.setRotation(rocket.getVel().angle());
        moonDisc.setRotation(moon.getPos().sub(rocket.getPos()).angle());
        earthDisc.setRotation(earth.getPos().sub(rocket.getPos()).angle());
    }
    
    private void drawBackground(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, screen.width, 40);
        g.fillRect(0, screen.height-60, screen.width, 60);
        g.fillOval(center.x-100, screen.height-100, 200, 200);
    }
    
    private void drawForeground(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, screen.height-25, screen.width, 25);
        g.fillOval(center.x-15, screen.height-40, 30, 30);
        g.setColor(LQConstants.COLOR_RED);
        g.fillPolygon(
                new int[]{center.x ,center.x+10, center.x-10}, 
                new int[]{screen.height-39, screen.height-24, screen.height-24},
                3);
    }
    
    

}
