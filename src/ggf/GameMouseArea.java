package ggf;

public abstract class GameMouseArea {
    
    protected Rectangle area;

    public GameMouseArea(Rectangle area) {
        this.area = area;
    }
    
    public GameMouseArea(Vector pos, Vector size) {
        this(new Rectangle(pos, size));
    }
    
    public GameMouseArea(double x, double y, double width, double height) {
        this(new Rectangle(x, y, width, height));
    }
    
    public Rectangle getArea() {
        return area;
    }
    
    public abstract void press();
    public abstract void release();
    public abstract void drag(Vector move);
    
}
