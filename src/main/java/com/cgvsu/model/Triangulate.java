package com.cgvsu.model;

import java.util.ArrayList;


public class Triangulate {
    public static TriangulateModel triangulate(Model model) {
        TriangulateModel result = new TriangulateModel();
        result.setVertices(model.vertices);
        result.setNormals(model.normals);
        result.setTextureVertices(model.textureVertices);
        ArrayList<Polygon> triangles = new ArrayList<>();
        for (Polygon polygon : model.polygons) {
            ArrayList<Integer> vertexIndices = polygon.getVertexIndices();
            if (vertexIndices.size() > 3) {
                for (int i = 2; i < vertexIndices.size(); i++) {
                    ArrayList<Integer> newVertexIndices = new ArrayList<>();
                    newVertexIndices.add(vertexIndices.get(0));
                    newVertexIndices.add(vertexIndices.get(i - 1));
                    newVertexIndices.add(vertexIndices.get(i));

                    Polygon newPolygon = new Polygon();
                    newPolygon.setVertexIndices(newVertexIndices);

                    triangles.add(newPolygon);
                }
            } else if (vertexIndices.size() == 3) triangles.add(polygon);
        }

        result.setPolygons(triangles);
        return result;
    }
}
