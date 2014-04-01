package lunarquest;

import ggf.geom.Vector;
import ggf.geom.Vector;
import ggf.physics2.Physics2State;
import ggf.physics2.RigidPolygon;
import java.awt.Color;

public class PointMassState extends Physics2State {

    public PointMassState() {
        RigidPolygon star = new RigidPolygon(view);
        star.addTriangle(new Vector(0,0), new Vector(-1,-1), new Vector(1,-1));
        star.addTriangle(new Vector(0,0), new Vector(1,-1), new Vector(1,1));
        star.addTriangle(new Vector(0,0), new Vector(1,1), new Vector(-1,1));
        star.addTriangle(new Vector(0,0), new Vector(-1,1), new Vector(-1,-1));
        star.addTriangle(new Vector(0,-3), new Vector(-1,-1), new Vector(1,-1));
        star.addTriangle(new Vector(3,0), new Vector(1,-1), new Vector(1,1));
        star.addTriangle(new Vector(0,3), new Vector(1,1), new Vector(-1,1));
        star.addTriangle(new Vector(-3,0), new Vector(-1,1), new Vector(-1,-1));
        System.out.println("Totalt areal: " + star.getArea());
        star.distributeMass(100);
        System.out.println("Total masse: " + star.getMass());
        star.setFill(Color.PINK);
        gameObjects.add(star);
        
        view.setPosition(new Vector(new Vector(400, 300).sub(star.getPosition())));
        view.setScale(50);
    }

}
