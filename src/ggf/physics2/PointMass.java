package ggf.physics2;

import ggf.geom.Vector;
import java.util.HashSet;
import java.util.Set;

public class PointMass extends Vector {
    
    private RigidPolygon body;
    private double mass;

    public PointMass(Vector position, RigidPolygon body, double mass) {
        super(position);
        this.body = body;
        this.mass = mass;
    }

    public RigidPolygon getBody() {
        return body;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }
    
    public Set<PointMassTriangle> getTriangles() {
        return getBody().getTrianglesAtVertex(this);
    }
    
    public Set<PointMass> getConnectedVertices() {
        HashSet<PointMass> connectedVertices = new HashSet();
        for(PointMassTriangle triangle : getTriangles()) {
            connectedVertices.addAll(triangle.getVertices());
        }
        connectedVertices.remove(this);
        return connectedVertices;
    }
    
    public boolean hasPosition(Vector position) {
        return ((Vector)this).equals(position);
    }
    
    public boolean isOuter() {
        return getConnectedVertices().size() == getTriangles().size() + 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PointMass other = (PointMass) obj;
        if (getX() != other.getX()) {
            return false;
        }
        if (getY() != other.getY()) {
            return false;
        }
        if (!body.equals(other.body)) {
            return false;
        }
        
        
        return true;
    }

    @Override
    public String toString() {
        return "PointMass[" + getX() + ", " + getY() + "]";
    }

}
