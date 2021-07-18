package ua.nure.pzos.dl.laba1Test;

import org.junit.Test;
import ua.nure.pzos.dl.laba1.part1.LengthBellowAverage;

import static org.junit.Assert.*;

public class LenghtBellowAverageTest {
    @Test
    public void FindAvgLength() {
        int n = 5;
        int[] mas = {1, 3, 5, 3, 4};
        assertEquals(LengthBellowAverage.FindAvgLenght(mas, n), 1.d, 1);
    }

    @Test
    public void ReturnBellowAvgLenMas() {
        int n = 5;
        int[] mas = {1, 3, 5, 4, 10};
        assertArrayEquals(LengthBellowAverage.GetBellowAvgLen(mas, n, '<'), new int[]{1, 3, 5, 4});
        assertArrayEquals(LengthBellowAverage.GetBellowAvgLen(mas, n, '>'), new int[]{10});
        assertArrayEquals(LengthBellowAverage.GetBellowAvgLen(mas, n, 'h'), new int[]{});
    }
}
