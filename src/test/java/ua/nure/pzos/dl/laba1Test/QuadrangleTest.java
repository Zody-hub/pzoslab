package ua.nure.pzos.dl.laba1Test;

import org.junit.Test;
import ua.nure.pzos.dl.laba1.part2.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class QuadrangleTest {
    @Test
    public void perimetrTest() {
        Dot A = new Dot(0, 0);
        Dot B = new Dot(0, 1);
        Dot C = new Dot(1, 1);
        Dot D = new Dot(1, 0);
        Square square = new Square(A, B, C, D);
        assertEquals(4, square.getPerimeter());
    }

    @Test
    public void squareTest() {
        Square square = new Square(new Dot(0, 0), new Dot(0, 1), new Dot(1, 1), new Dot(1, 0));
        assertEquals(1, square.getSqaure(), 1);
        Rhombus rhombus = new Rhombus(new Dot(-2, 0), new Dot(0, 4), new Dot(2, 0), new Dot(0, -4));
        assertEquals(16, rhombus.getSqaure(), 1);
        Rectangle rectangle = new Rectangle(new Dot(0, 0), new Dot(0, 1), new Dot(2, 1), new Dot(2, 0));
        assertEquals(2, rectangle.getSqaure(), 1);
        ArbitraryQuadrangle arbitraryQuadrangle = new ArbitraryQuadrangle(new Dot(0, 0), new Dot(0, 1), new Dot(1, 1), new Dot(1, 0));
        assertEquals(1, arbitraryQuadrangle.getSqaure(), 1);
    }

    @Test
    public void quadrangleListTest() {
        int minSquarePerimeter = 0, maxSquarePerimeter = 0, squareCount = 0;
        int minRectanglePerimeter = 0, maxRectanglePerimeter = 0, rectangleCount = 0;
        int minRhombusPerimeter = 0, maxRhombusPerimeter = 0, rhombusCount = 0;
        int minArbitraryQuadranglePerimeter = 0, maxArbitraryQuadranglePerimeter = 0, arbitraryQuadrangleCount = 0;
        List<Quadrangle> quadrangles = new ArrayList<>();
        quadrangles.add(new Square(new Dot(0, 0), new Dot(0, 1), new Dot(1, 1), new Dot(1, 0)));
        quadrangles.add(new Square(new Dot(0, 0), new Dot(0, 2), new Dot(2, 2), new Dot(2, 0)));
        quadrangles.add(new Rectangle(new Dot(0, 0), new Dot(0, 1), new Dot(2, 1), new Dot(2, 0)));
        for (int i = 0; i < quadrangles.size(); i++) {
            String clas = quadrangles.get(i).getClass().toString().substring(quadrangles.get(i).getClass().toString().lastIndexOf('.') + 1);
            int currrentPerimeter = quadrangles.get(i).getPerimeter();
            switch (clas) {
                case ("Square"): {
                    if (currrentPerimeter > maxSquarePerimeter) {
                        maxSquarePerimeter = currrentPerimeter;
                    }
                    if (minSquarePerimeter == 0) {
                        minSquarePerimeter = currrentPerimeter;
                    }
                    if (currrentPerimeter < minSquarePerimeter) {
                        minSquarePerimeter = currrentPerimeter;
                    }
                    squareCount++;
                    break;
                }
                case ("Rectangle"): {
                    if (currrentPerimeter > maxRectanglePerimeter) {
                        maxRectanglePerimeter = currrentPerimeter;
                    }
                    if (minRectanglePerimeter == 0) {
                        minRectanglePerimeter = currrentPerimeter;
                    }
                    if (currrentPerimeter < minRectanglePerimeter) {
                        minRectanglePerimeter = currrentPerimeter;
                    }
                    rectangleCount++;
                    break;
                }
                case ("Rhombus"): {
                    if (currrentPerimeter > maxRhombusPerimeter) {
                        maxRhombusPerimeter = currrentPerimeter;
                    }
                    if (minRhombusPerimeter == 0) {
                        minRhombusPerimeter = currrentPerimeter;
                    }
                    if (currrentPerimeter < minRhombusPerimeter) {
                        minRhombusPerimeter = currrentPerimeter;
                    }
                    rhombusCount++;
                    break;
                }
                case ("ArbitraryQuadrangle"): {
                    if (currrentPerimeter > maxArbitraryQuadranglePerimeter) {
                        maxArbitraryQuadranglePerimeter = currrentPerimeter;
                    }
                    if (minArbitraryQuadranglePerimeter == 0) {
                        minArbitraryQuadranglePerimeter = currrentPerimeter;
                    }
                    if (currrentPerimeter < minArbitraryQuadranglePerimeter) {
                        minArbitraryQuadranglePerimeter = currrentPerimeter;
                    }
                    arbitraryQuadrangleCount++;
                    break;
                }
            }
        }
        assertEquals(8, maxSquarePerimeter);
        assertEquals(4, minSquarePerimeter);
        assertEquals(2, squareCount);
        assertEquals(1, rectangleCount);
        assertEquals(6, minRectanglePerimeter);
        assertEquals(6, maxRectanglePerimeter);
    }
}
