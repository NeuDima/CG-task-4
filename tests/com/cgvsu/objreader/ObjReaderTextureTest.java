package com.cgvsu.objreader;

import com.cgvsu.math.Vector2f;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class ObjReaderTextureTest {
    @Test
    public void testParseVertex2f01() {
        ArrayList<String> wordsInLineWithoutToken = new ArrayList<>(Arrays.asList("1.01", "1.02"));
        Vector2f result = ObjReader.parseTextureVertex(wordsInLineWithoutToken, 5);
        Vector2f expectedResult = new Vector2f(1.01f, 1.02f);
        Assert.assertTrue(result.equals(expectedResult));
    }

    @Test
    public void testParseVertex2f02() {
        ArrayList<String> wordsInLineWithoutToken = new ArrayList<>(Arrays.asList("1.01", "1.03"));
        Vector2f result = ObjReader.parseTextureVertex(wordsInLineWithoutToken, 5);
        Vector2f expectedResult = new Vector2f(1.01f, 1.10f);
        Assert.assertFalse(result.equals(expectedResult));
    }

    @Test
    public void testParseVertex2f03() {
        ArrayList<String> wordsInLineWithoutToken = new ArrayList<>(Arrays.asList("ab", "o"));
        try {
            ObjReader.parseTextureVertex(wordsInLineWithoutToken, 10);
            Assert.assertTrue(false);
        } catch (ObjReaderException exception) {
            String expectedError = "Error parsing OBJ file on line: 10. Failed to parse float value.";
            Assert.assertEquals(expectedError, exception.getMessage());
        }
    }

    @Test
    public void testParseVertex2f04() {
        ArrayList<String> wordsInLineWithoutToken = new ArrayList<>(Arrays.asList("1.0"));
        try {
            ObjReader.parseTextureVertex(wordsInLineWithoutToken, 10);
            Assert.assertTrue(false);
        } catch (ObjReaderException exception) {
            String expectedError = "Error parsing OBJ file on line: 10. Too few texture vertex arguments.";
            Assert.assertEquals(expectedError, exception.getMessage());
        }
    }

    @Test
    public void testParseVertex2f05() {
        ArrayList<String> wordsInLineWithoutToken = new ArrayList<>(Arrays.asList("1.0", "1.1", "1.2"));
        try {
            ObjReader.parseTextureVertex(wordsInLineWithoutToken, 10);
            Assert.assertTrue(false);
        } catch (ObjReaderException exception) {
            String expectedError = "Error parsing OBJ file on line: 10. Too much texture vertex arguments.";
            Assert.assertEquals(expectedError, exception.getMessage());
        }
    }

}
