package lunarquest.flystate;

import ggf.GameObject;
import ggf.TransformObject;
import ggf.framework.Controls;
import ggf.framework.GameTime;
import ggf.framework.GameStateManager;
import ggf.geom.Vector;
import ggf.geom.Vector;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import lunarquest.LQConstants;

public class Hud extends TransformObject implements GameObject {

    private static final Dimension screen = new Dimension(LQConstants.WINDOW_WIDTH, LQConstants.WINDOW_HEIGHT);
    private static final java.awt.Point center = new java.awt.Point(screen.width/2, screen.height/2);
    
    private RocketObject rocket;
    private MoonObject moon;
    private EarthObject earth;
    private TransformObject discRef;
    private HudDisc velDisc;
    private HudDisc moonDisc;
    private HudDisc earthDisc;
    
    public Hud(Vector pos, RocketObject rocket, MoonObject moon, EarthObject earth) {
        this.rocket = rocket;
        this.moon = moon;
        this.earth = earth;
        
        discRef = new TransformObject();
        discRef.setPosition(new Vector(center.getX(), screen.getHeight()-25));
        
        velDisc = new HudDisc(discRef, 70, LQConstants.COLOR_GREEN);
        moonDisc = new HudDisc(discRef, 55, LQConstants.COLOR_WHITEISH);
        earthDisc = new HudDisc(discRef, 40, LQConstants.COLOR_BLUE);
    }

    @Override
    public void draw(Graphics2D g) {
        drawBackground(g);
        
        velDisc.draw(g);
        moonDisc.draw(g);
        earthDisc.draw(g);
        
        drawForeground(g);
    }

    @Override
    public void update(GameTime time, GameStateManager stateMgr, Controls controls) {
        discRef.setRotation(-rocket.getRotation());
        velDisc.setRotation(rocket.getVel().angle());
        moonDisc.setRotation(moon.getPosition().sub(rocket.getPosition()).angle());
        earthDisc.setRotation(earth.getPosition().sub(rocket.getPosition()).angle());
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
                new int[]{screen.height-39, screen.height-25, screen.height-25},
                3);
    }
    
    

}
