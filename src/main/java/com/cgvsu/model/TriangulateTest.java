package com.cgvsu.model;

import com.cgvsu.math.Vector3f;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TriangulateTest {


    @Test
    void triangulateWithSinglePointModel() {
        // Создаем модель с одной вершиной
        Model model = new Model();
        model.vertices.add(new Vector3f(0, 0, 0));

        // Триангулируем модель
        TriangulateModel result = Triangulate.triangulate(model);

        // Проверяем, что результат также содержит одну вершину (без полигонов)
        assertEquals(0, result.getPolygons().size());
        assertEquals(1, result.getVertices().size());
    }

    @Test
    void triangulateWithTwoPointModel() {
        // Создаем модель с двумя вершинами
        Model model = new Model();
        model.vertices.addAll(new ArrayList(Arrays.asList(0, 0, 0, 1, 1, 1)));

        // Триангулируем модель
        TriangulateModel result = Triangulate.triangulate(model);

        // Проверяем, что результат также содержит правильное кол-во вершин (без полигонов)
        assertEquals(0, result.getPolygons().size());
        assertEquals(6, result.getVertices().size());
    }


    @Test
    void triangulateWithEmptyModel() {
        // Создаем пустую модель
        Model model = new Model();

        // Триангулируем модель
        TriangulateModel result = Triangulate.triangulate(model);

        // Проверяем, что результат также пуст
        assertEquals(0, result.getPolygons().size());
    }

    @Test
    void triangulate() {
// Создаем простую модель для тестирования
        Model model = new Model();
        new ArrayList(Arrays.asList(0, 0, 0,
                1, 0, 0,
                1, 1, 0));


        ArrayList<Integer> indices = new ArrayList<>();
        indices.add(0);
        indices.add(1);
        indices.add(2);

        Polygon polygon = new Polygon();
        polygon.setVertexIndices(indices);

        model.polygons.add(polygon);

// Триангулируем модель
        TriangulateModel result = Triangulate.triangulate(model);
        System.out.println(result.getPolygons().size());

// Проверяем, что результат содержит правильное количество треугольников
        assertEquals(1, result.getPolygons().size());


    }

    @Test
    void triangulateWithQuadrilateral() {
// Создаем модель с четырехугольником
        Model model = new Model();
        model.vertices.addAll(new ArrayList(Arrays.asList(0, 0, 0,
                1, 0, 0,
                1, 1, 0,
                0, 1, 0)));

        ArrayList<Integer> indices = new ArrayList<>();
        indices.add(0);
        indices.add(1);
        indices.add(2);
        indices.add(3);

        Polygon polygon = new Polygon();
        polygon.setVertexIndices(indices);

        model.polygons.add(polygon);

// Триангулируем модель
        TriangulateModel result = Triangulate.triangulate(model);

// Проверяем, что результат содержит правильное количество треугольников
        assertEquals(2, result.getPolygons().size());
    }

    //Проверяем, что нельзя установить модель с полигонами с 4 вершинами в TriangulaeModel
    @Test
    void triangulateSetModel() {
        Model model = new Model();
        model.vertices.addAll(new ArrayList(Arrays.asList(0, 0, 0,
                1, 0, 0,
                1, 1, 0,
                0, 1, 0)));

        ArrayList<Integer> indices = new ArrayList<>();
        indices.add(0);
        indices.add(1);
        indices.add(2);
        indices.add(3);

        Polygon polygon = new Polygon();
        polygon.setVertexIndices(indices);

        model.polygons.add(polygon);
        TriangulateModel triangulateModel = new TriangulateModel();
        try {
            triangulateModel.setModel(model);
        } catch (Exception exception) {
            String error = "All polygons must be triangles.";
            Assertions.assertEquals(exception.getMessage(), error);
        }
    }

    @Test
    void triangulateWithMultiplePolygons() {
        // Создаем модель с несколькими полигонами
        Model model = new Model();
        model.vertices.add(new Vector3f(0, 0, 0));
        model.vertices.add(new Vector3f(1, 0, 0));
        model.vertices.add(new Vector3f(1, 1, 0));
        model.vertices.add(new Vector3f(0, 1, 0));
        model.vertices.add(new Vector3f(0.5f, 0.5f, 1));
        model.vertices.add(new Vector3f(1.5f, 0.5f, 1));
        // Первый полигон с тремя вершинами
        ArrayList<Integer> indices1 = new ArrayList<>(Arrays.asList(0, 1, 2));
        Polygon polygon1 = new Polygon();
        polygon1.setVertexIndices(indices1);
        model.polygons.add(polygon1);

        // Второй полигон с тремя вершинами
        ArrayList<Integer> indices2 = new ArrayList<>(Arrays.asList(2, 3, 0));
        Polygon polygon2 = new Polygon();
        polygon2.setVertexIndices(indices2);
        model.polygons.add(polygon2);

        // Триангулируем модель
        TriangulateModel result = Triangulate.triangulate(model);
        // Проверяем, что результат содержит правильное количество треугольников
        assertEquals(2, result.getPolygons().size());
    }


    @Test
    void triangulateWithConvexHexagon() {
        // Создаем модель с выпуклым шестиугольником
        Model model = new Model();
        model.vertices.addAll(new ArrayList(Arrays.asList(
                0, 0, 0,
                1, 0, 0,
                1.5, 0.5, 0,
                1, 1, 0,
                0, 1, 0,
                -0.5, 0.5, 0
        )));

        ArrayList<Integer> indices = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5));
        Polygon polygon = new Polygon();
        polygon.setVertexIndices(indices);
        model.polygons.add(polygon);

        // Триангулируем модель
        TriangulateModel result = Triangulate.triangulate(model);

        // Проверяем, что результат содержит правильное количество треугольников
        assertEquals(4, result.getPolygons().size());
    }

    @Test
    void triangulateWithMultipleHeptagons() {
        // Создаем модель с несколькими семиугольными выпуклыми полигонами
        Model model = new Model();
        model.vertices.addAll(new ArrayList(Arrays.asList(
                // Первый семиугольник
                0, 0, 0,
                1, 0, 0,
                1.5, 0.5, 0,
                1, 1, 0,
                0, 1, 0,
                -0.5, 0.5, 0,
                -0.2, 0, 0,

                // Второй семиугольник
                2, 0, 0,
                3, 0, 0,
                3.5, 0.5, 0,
                3, 1, 0,
                2, 1, 0,
                1.5, 0.5, 0,
                1.8, 0, 0
        )));

        // Первый семиугольник
        ArrayList<Integer> indices1 = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6));
        Polygon polygon1 = new Polygon();
        polygon1.setVertexIndices(indices1);
        model.polygons.add(polygon1);

        // Второй семиугольник
        ArrayList<Integer> indices2 = new ArrayList<>(Arrays.asList(7, 8, 9, 10, 11, 12, 13));
        Polygon polygon2 = new Polygon();
        polygon2.setVertexIndices(indices2);
        model.polygons.add(polygon2);

        // Триангулируем модель
        TriangulateModel result = Triangulate.triangulate(model);

        // Проверяем, что результат содержит правильное количество треугольников
        assertEquals(10, result.getPolygons().size());
    }


}