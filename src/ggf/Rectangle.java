package ggf;

public class Rectangle {
    
    public Vector pos;
    public Vector diag;
    
    public Rectangle(Vector pos, Vector diag) {
        this.pos = pos;
        this.diag = diag;
    }
    
    public Rectangle(double x, double y, double width, double height) {
        this(new Vector(x, y), new Vector(width, height));
    }
    
    public Rectangle(Rectangle rectangle) {
        this(rectangle.pos, rectangle.diag);
    }
    
    public double size() {
        return diag.x*diag.y;
    }
    
}
