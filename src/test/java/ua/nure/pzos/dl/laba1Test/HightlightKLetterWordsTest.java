package ua.nure.pzos.dl.laba1Test;

import org.junit.Test;
import ua.nure.pzos.dl.laba1.part3.HightlightKLetterWords;

import static org.junit.Assert.assertEquals;

public class HightlightKLetterWordsTest {
    @Test
    public void hightlightKLetterWordsTest() {
        HightlightKLetterWords o = new HightlightKLetterWords();
        String str = "Kell kiil, kil coo";
        assertEquals("Kell kiil kil ", o.hightlight(str));
    }
}
