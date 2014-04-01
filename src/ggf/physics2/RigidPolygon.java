package ggf.physics2;

import ggf.GameObject;
import ggf.ShapeObject;
import ggf.TransformObject;
import ggf.framework.Controls;
import ggf.framework.GameStateManager;
import ggf.framework.GameTime;
import ggf.geom.Vector;
import ggf.geom.Vector;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class RigidPolygon extends TransformObject implements GameObject {
    
    private Set<PointMassTriangle> triangles;
    private Set<PointMass> vertices;
    private ShapeObject shapeObject;
    private TransformObject view;
    
    private Vector velocity;
    
    public RigidPolygon(TransformObject view) {
        this.view = view;
        
        triangles = new HashSet();
        vertices = new HashSet();
        shapeObject = new ShapeObject(this);
        velocity = Vector.NULL;
    }
    
    public void addTriangle(Vector point1, Vector point2, Vector point3) {
        Set<PointMass> newVertices = new HashSet();
        newVertices.add(new PointMass(point1, this, 0));
        newVertices.add(new PointMass(point2, this, 0));
        newVertices.add(new PointMass(point3, this, 0));
        PointMassTriangle newTriangle = new PointMassTriangle(newVertices);
        
        System.out.println("Attempting to add " + newTriangle);
        
        if(triangles.isEmpty()) {
            triangles.add(newTriangle);
            vertices.addAll(newVertices);
        } else if(triangles.contains(newTriangle)) {
            return;
        } else {
            
            
                
            Map<PointMass, PointMass> exchangeMap = new HashMap();
            Set<PointMass> overlappingVertices = new HashSet();
            for(PointMass newVertex : newVertices) {
                for(PointMassTriangle triangle : triangles) {
                    if(triangle.contains(newVertex)) {
                        throw new IllegalStateException(
                                "None of the points can be inside existing triangles"
                        );
                    }
                }
                for(PointMass vertex : vertices) {
                    if(newVertex.hasPosition(vertex)) {
                        exchangeMap.put(newVertex, vertex);
                        overlappingVertices.add(vertex);
                    }
                }
            }
            for(PointMass newVertex : exchangeMap.keySet()) {
                newVertices.remove(newVertex);
                newVertices.add(exchangeMap.get(newVertex));
            }
            if(overlappingVertices.size() == 2) {
                Iterator<PointMass> overlappingVerticeIterator = overlappingVertices.iterator();
                PointMass overlappingVertex1 = overlappingVerticeIterator.next();
                PointMass overlappingVertex2 = overlappingVerticeIterator.next();
                Set<PointMassTriangle> commonTriangles = getTrianglesAtVertex(overlappingVertex1);
                commonTriangles.retainAll(getTrianglesAtVertex(overlappingVertex2));
                if(commonTriangles.size() == 1) {
                    for(PointMass newVertex : newVertices) {
                        if(!overlappingVertices.contains(newVertex)) vertices.add(newVertex);
                    }
                    triangles.add(new PointMassTriangle(newVertices));
                } else {
                    throw new IllegalStateException(
                            "There is no room for a triangle there"
                    );
                }
            } else if(overlappingVertices.size() == 3) {
                Set<PointMassTriangle> commonTriangles;
                Set<PointMass> hasBeenCompared = new HashSet();
                boolean twoVerticesHasNoTrianglesInCommon = false;
                for(PointMass newVertexI : newVertices) {
                    hasBeenCompared.add(newVertexI);
                    for(PointMass newVertexJ : newVertices) {
                        commonTriangles = getTrianglesAtVertex(newVertexI);
                        if(hasBeenCompared.contains(newVertexJ)) continue;
                        commonTriangles.retainAll(getTrianglesAtVertex(newVertexJ));
                        if(commonTriangles.isEmpty()) {
                            System.out.println(newVertexI + " and " + newVertexJ
                                    + " have no common triangles.");
                            if(twoVerticesHasNoTrianglesInCommon) {
                                throw new IllegalArgumentException(
                                        "When adding a triangle where all three "
                                                + "vertices are already present, "
                                                + "two of the walls of the new "
                                                + "triangle must already exist "
                                                + "as walls of two other triangles."
                                );
                            }
                            twoVerticesHasNoTrianglesInCommon = true;
                        }
                    }
                }
                if(!twoVerticesHasNoTrianglesInCommon) {
                    throw new IllegalArgumentException(
                            "When adding a triangle where all three vertices "
                                    + "are already present, one wall of the "
                                    + "new triangle must be new, while the "
                                    + "two other walls must already exist "
                                    + "as walls of two other triangles."
                    );
                }
                triangles.add(new PointMassTriangle(overlappingVertices));
                
            } else {
                throw new IllegalStateException(
                        "All triangles except the first must share two or "
                                + "three vertices with existing triangles."
                );
            }
        }
        updateShapeObject();
        
    }
    
    public void distributeMass(double mass) {
        double areaTotal = getArea();
        for(PointMassTriangle triangle : triangles) {
            System.out.println("Mass distribution: " + triangle + ", area: " + triangle.getArea());
            double triangleMass = mass * triangle.getArea() / areaTotal;
            for(PointMass vertex : triangle.getVertices()) {
                vertex.setMass(vertex.getMass() + triangleMass/3);
                System.out.println("Increasing " + vertex + "'s mass by "
                        + triangleMass/3 + " to a value of " + vertex.getMass() + ".");
            }
        }
    }
    
    private void updateShapeObject() {
        if(triangles.isEmpty()) return;
        if(triangles.size() == 1) {
            System.out.println("Creating simple path from a single triangle.");
            shapeObject.setShape(triangles.iterator().next().getPath2D());
        } else {
            HashSet<PointMass> visitedVertices = new HashSet();
            PointMass currentVertex = null;
            for(PointMass vertex : vertices) {
                if(vertex.isOuter()) {
                    currentVertex = vertex;
                    break;
                }
            }
            if(currentVertex == null) throw new UnknownError(
                    "Something went wrong with the path generation process"
            );
            visitedVertices.add(currentVertex);
            Path2D polygonPath = new Path2D.Double();
            polygonPath.moveTo(currentVertex.getX(), currentVertex.getY());
            System.out.println("Starting path at " + currentVertex + ".");
            while(true) {
                System.out.println("Looking for new vertex.");
                for(PointMass connectedVertex : currentVertex.getConnectedVertices()) {
                    System.out.println("Considering " + connectedVertex + ".");
                    PointMassTriangleEdge connectingEdge = new PointMassTriangleEdge(currentVertex, connectedVertex);
                    if(!visitedVertices.contains(connectedVertex)
                            && connectingEdge.isOuter()) {
                        currentVertex = connectedVertex;
                        break;
                    } else if(visitedVertices.contains(connectedVertex)) {
                        System.out.println(connectedVertex + " has already been visited.");
                    } else {
                        System.out.println(connectingEdge + " is not along an outer edge.");
                    }
                }
                if(visitedVertices.contains(currentVertex)) break;
                visitedVertices.add(currentVertex);
                polygonPath.lineTo(currentVertex.getX(), currentVertex.getY());
                System.out.println("Continuing to " + currentVertex + ".");
            }
            if(!visitedVertices.equals(getOuterVertices())) throw new UnknownError(
                    "Something went wrong with the path generation process"
            );
            polygonPath.closePath();
            System.out.println("And then back to start ");
            shapeObject.setShape(polygonPath);
        }
    }
    
    public Set<PointMassTriangle> getTriangles() {
        return triangles;
    }
    
    public Set<PointMassTriangle> getTrianglesAtVertex(PointMass vertex) {
        HashSet<PointMassTriangle> trianglesAtVertex = new HashSet();
        for(PointMassTriangle triangle : getTriangles()) {
            for(PointMass triangleVertex : triangle.getVertices()) {
                if(vertex.equals(triangleVertex)) trianglesAtVertex.add(triangle);
            }
        }
        return trianglesAtVertex;
    }
    
    public PointMass getVertexAtPosition(Vector position) {
        for(PointMass vertex : vertices) {
            if(vertex.hasPosition(position)) return vertex;
        }
        return null;
    }
    
    public HashSet<PointMass> getOuterVertices() {
        HashSet outerVertices = new HashSet();
        for(PointMass vertex : vertices) {
            if(vertex.isOuter()) outerVertices.add(vertex);
        }
        return outerVertices;
    }
    
    public double getArea() {
        double area = 0;
        for(PointMassTriangle triangle : getTriangles()) area += triangle.getArea();
        return area;
    }
    
    public double getMass() {
        double mass = 0;
        for(PointMass vertex : vertices) mass += vertex.getMass();
        return mass;
    }
    
    public Vector getCenterOfMass() {
        Vector weightedSum = Vector.NULL;
        for(PointMass vertex : vertices) {
            weightedSum = weightedSum.add(vertex.mul(vertex.getMass()));
        }
        return weightedSum.div(getMass());
    }

    public Color getFill() {
        return shapeObject.getFill();
    }

    public void setFill(Color fill) {
        shapeObject.setFill(fill);
    }

    public Color getEdge() {
        return shapeObject.getEdge();
    }

    public void setEdge(Color edge) {
        shapeObject.setEdge(edge);
    }
    
    public void setAlpha(int alpha) {
        Color c = getFill();
        c = new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha);
        setFill(c);
        c = getEdge();
        c = new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha);
        setEdge(c);
    }

    @Override
    public Vector getPosition() {
        return super.getPosition().add(getCenterOfMass());
    }

    @Override
    public void update(GameTime time, GameStateManager stateMgr, Controls controls) { }

    @Override
    public void draw(Graphics2D g) {
        shapeObject.draw(g);
        for(PointMass vertex : vertices) {
            g.setColor(new Color(Math.min(255, 5 + 15*(int)vertex.getMass()), 70, 70));
            Vector absoluteVertex = new Vector();
            getAbsoluteTx().transform(vertex, absoluteVertex);
            Ellipse2D circle = new Ellipse2D.Double(
                    absoluteVertex.getX() - 5,
                    absoluteVertex.getY() - 5,
                    10, 
                    10
            );
            g.fill(circle);
        }
    }

    @Override
    public AffineTransform getAbsoluteTx() {
        AffineTransform absoluteTx = view.getAbsoluteTx();
        absoluteTx.concatenate(super.getAbsoluteTx());
        return absoluteTx;
    }

}