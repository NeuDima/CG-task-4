package com.cgvsu.objreader;

import com.cgvsu.math.Vector3f;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class ObjReaderVertexTest {
    @Test
    public void testParseVertex3f01() {
        ArrayList<String> wordsInLineWithoutToken = new ArrayList<>(Arrays.asList("1.01", "1.02", "1.03"));
        Vector3f result = ObjReader.parseVertex(wordsInLineWithoutToken, 5);
        Vector3f expectedResult = new Vector3f(1.01f, 1.02f, 1.03f);
        Assert.assertTrue(result.equals(expectedResult));
    }

    @Test
    public void testParseVertex3f02() {
        ArrayList<String> wordsInLineWithoutToken = new ArrayList<>(Arrays.asList("1.01", "1.02", "1.03"));
        Vector3f result = ObjReader.parseVertex(wordsInLineWithoutToken, 5);
        Vector3f expectedResult = new Vector3f(1.01f, 1.02f, 1.10f);
        Assert.assertFalse(result.equals(expectedResult));
    }

    @Test
    public void testParseVertex3f03() {
        ArrayList<String> wordsInLineWithoutToken = new ArrayList<>(Arrays.asList("ab", "o", "ba"));
        try {
            ObjReader.parseVertex(wordsInLineWithoutToken, 10);
            Assert.assertTrue(false);
        } catch (ObjReaderException exception) {
            String expectedError = "Error parsing OBJ file on line: 10. Failed to parse float value.";
            Assert.assertEquals(expectedError, exception.getMessage());
        }
    }

    @Test
    public void testParseVertex3f04() {
        ArrayList<String> wordsInLineWithoutToken = new ArrayList<>(Arrays.asList("1.0", "2.0"));
        try {
            ObjReader.parseVertex(wordsInLineWithoutToken, 10);
            Assert.assertTrue(false);
        } catch (ObjReaderException exception) {
            String expectedError = "Error parsing OBJ file on line: 10. Too few vertex arguments.";
            Assert.assertEquals(expectedError, exception.getMessage());
        }
    }

    @Test
    public void testParseVertex3f05() {
        ArrayList<String> wordsInLineWithoutToken = new ArrayList<>(Arrays.asList("1.0", "2.0", "3.0", "4.0"));
        try {
            ObjReader.parseVertex(wordsInLineWithoutToken, 10);
            Assert.assertTrue(false);
        } catch (ObjReaderException exception) {
            String expectedError = "Error parsing OBJ file on line: 10. Too much vertex arguments.";
            Assert.assertEquals(expectedError, exception.getMessage());
        }
    }

}
