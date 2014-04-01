package ggf.physics2;

import java.awt.geom.Path2D;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class PointMassTriangle {
    
    private Set<PointMass> vertices;
    private PointMass vertex1;
    private PointMass vertex2;
    private PointMass vertex3;

    public PointMassTriangle(
            PointMass vertex1,
            PointMass vertex2,
            PointMass vertex3
    ) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.vertex3 = vertex3;
        vertices = new HashSet();
        vertices.add(vertex1);
        vertices.add(vertex2);
        vertices.add(vertex3);
    }
    
    public PointMassTriangle(Set<PointMass> vertices) {
        this.vertices = vertices;
        Iterator<PointMass> iterator = vertices.iterator();
        vertex1 = iterator.next();
        vertex2 = iterator.next();
        vertex3 = iterator.next();
    }

    public RigidPolygon getBody() {
        return vertex1.getBody();
    }
    
    public Set<PointMass> getVertices() {
        return vertices;
    }
    
    public double getArea() {
        return Math.abs(
                0.5*(vertex1.getX()*(vertex3.getY() - vertex2.getY())
                + vertex2.getX()*(vertex1.getY() - vertex3.getY())
                + vertex3.getX()*(vertex2.getY() - vertex1.getY())
        ));
    }
    
    public Path2D getPath2D() {
        Path2D trianglePath = new Path2D.Double();
        trianglePath.moveTo(vertex1.getX(), vertex1.getY());
        trianglePath.lineTo(vertex2.getX(), vertex2.getY());
        trianglePath.lineTo(vertex3.getX(), vertex3.getY());
        trianglePath.closePath();
        return trianglePath;
    }
    
    public boolean contains(PointMass otherVertex) {
        for(PointMass vertex : vertices) {
            if(vertex.hasPosition(otherVertex)) return false;
        }
        Path2D trianglePath = getPath2D();
        return trianglePath.contains(otherVertex);
    }
    
    public boolean isOuter() {
        int outerVertices = 0;
        for(PointMass vertex : vertices) {
            if(vertex.isOuter()) outerVertices++;
        }
        return outerVertices >= 2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PointMassTriangle other = (PointMassTriangle) obj;
        if (!vertices.equals(other.vertices)) {
            return  false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PointMassTriangle{[" + vertex1.getX() + ", " + vertex1.getY()
                + "]~[" + vertex2.getX() + ", " + vertex2.getY()
                + "]~[" + vertex3.getX() + ", " + vertex3.getY() + "]}";
    }

}
