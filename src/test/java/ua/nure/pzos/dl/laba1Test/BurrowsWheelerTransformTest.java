package ua.nure.pzos.dl.laba1Test;

import org.junit.Test;
import ua.nure.pzos.dl.laba1.part3.BurrowsWheelerTransform;

import static org.junit.Assert.assertEquals;

public class BurrowsWheelerTransformTest {
    @Test
    public void encodingTest() {
        BurrowsWheelerTransform encode = new BurrowsWheelerTransform();
        String str = "banana";
        String t = encode.bwt(str);
        String r = encode.ibwt(t);
        assertEquals("|annb^aa", encode.makePrintable(t));
        assertEquals(str, r);
    }
}
