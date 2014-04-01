package ggf.physics2;

import java.util.HashSet;
import java.util.Set;

public class PointMassTriangleEdge {
    
    private PointMass vertex1;
    private PointMass vertex2;

    public PointMassTriangleEdge(PointMass vertex1, PointMass vertex2) {
        if(!vertex1.getConnectedVertices().contains(vertex2)) {
            throw new IllegalArgumentException("Vertices in edge must be connected");
        }
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
    }
    
    public Set<PointMassTriangle> getTriangles() {
        Set<PointMassTriangle> commonTriangles = new HashSet(vertex1.getTriangles());
        commonTriangles.retainAll(vertex2.getTriangles());
//        for(PointMassTriangle triangle : commonTriangles) System.out.println(triangle);
        return commonTriangles;
    } 
    
    public boolean isOuter() {
        return getTriangles().size() == 1;
    }
    
    public Boolean movesClockwise() {
        if(!isOuter()) return null;
        Set<PointMass> triangleVertices = new HashSet(getTriangles().iterator().next().getVertices());
        triangleVertices.remove(vertex1);
        triangleVertices.remove(vertex2);
        PointMass vertex3 = triangleVertices.iterator().next();
        
        double angleV2toV1 = vertex1.sub(vertex2).angle();
        double angleV2toV3 = vertex3.sub(vertex2).angle();
        return (angleV2toV1 - angleV2toV3) % 2*Math.PI < Math.PI;
    }

    @Override
    public String toString() {
        return "PointMassTriangleEdge[" + vertex1.getX() + ", " + vertex1.getY()
                + "]<->[" + vertex2.getX() + ", " + vertex2.getY() + "]";
    }

}
