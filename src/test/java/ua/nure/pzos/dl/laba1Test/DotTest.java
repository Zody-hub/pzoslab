package ua.nure.pzos.dl.laba1Test;

import org.junit.Test;
import ua.nure.pzos.dl.laba1.part2.Dot;

import static org.junit.Assert.*;

public class DotTest {
    @Test
    public void distanceTest() {
        Dot dot1 = new Dot(0, 1);
        Dot dot2 = new Dot(2, -2);
        assertEquals(Math.sqrt(13), dot1.getDistance(dot2), 0.1);
    }

    @Test
    public void speedTest() {
        Dot dot = new Dot(0, 0, 1, 0);
        assertEquals(1, dot.getSpeed(), 0.1);
    }
}
