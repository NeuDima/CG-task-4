package com.cgvsu.model;
import com.cgvsu.math.Vector2f;
import com.cgvsu.math.Vector3f;

import java.util.*;

public class Model {

    public ArrayList<Vector3f> vertices = new ArrayList<Vector3f>();
    public ArrayList<Vector2f> textureVertices = new ArrayList<Vector2f>();
    public ArrayList<Vector3f> normals = new ArrayList<Vector3f>();
    public ArrayList<Polygon> polygons = new ArrayList<Polygon>();

    public void setModel(Model model) {
        this.vertices = new ArrayList<>(model.vertices);
        this.textureVertices = new ArrayList<>(model.textureVertices);
        this.normals = new ArrayList<>(model.normals);
        this.polygons = new ArrayList<>(model.polygons.size());

        for (Polygon polygon : model.polygons) {
            Polygon newPolygon = new Polygon();
            newPolygon.setVertexIndices(new ArrayList<>(polygon.getVertexIndices()));
            this.polygons.add(newPolygon);
        }
    }



}
