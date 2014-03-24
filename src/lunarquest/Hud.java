package lunarquest;

import ggf.GameClock;
import ggf.GameInput;
import ggf.GameObject;
import ggf.GameStateManager;
import ggf.geom.Vector;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Hud extends GameObject {

    private static final Dimension screen = new Dimension(LQConstants.WINDOW_WIDTH, LQConstants.WINDOW_HEIGHT);
    private static final Point center = new Point(screen.width/2, screen.height/2);
    
    private RocketObject rocket;
    private TransformObject discRef;
    private HudDisc velDisc;
    private HudDisc moonDisc;
    private HudDisc earthDisc;
    
    public Hud(Vector pos, RocketObject rocket) {
        super(pos);
        this.rocket = rocket;
        discRef = new TransformObject(null, new Vector(center.x, screen.height-50), -Math.PI/2) {
            @Override public double getAbsScale() { return 1; }
            @Override public double getAbsRotation() { return getRotation(); }
            @Override public Vector getAbsPos() { return getPos(); }
        };
        velDisc = new HudDisc(discRef, Vector.NULL, 45, LQConstants.COLOR_GREEN);
        moonDisc = new HudDisc(discRef, Vector.NULL, 35, LQConstants.COLOR_WHITEISH);
        earthDisc = new HudDisc(discRef, Vector.NULL, 25, LQConstants.COLOR_BLUE);
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
    public void update(GameClock clock, GameStateManager gsm, GameInput input) {
        super.update(clock, gsm, input);
    }
    
    private void drawBackground(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, screen.width, 40);
        g.fillRect(0, screen.height-60, screen.width, 60);
        g.fillOval(center.x-50, screen.height-100, 100, 100);
    }
    
    private void drawForeground(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(center.x-15, screen.height-65, 30, 30);
        g.setColor(Color.DARK_GRAY);
        g.fillPolygon(
                new int[]{center.x ,center.x+10, center.x-10}, 
                new int[]{screen.height-64, screen.height-44, screen.height-44},
                3);
    }
    
    

}
