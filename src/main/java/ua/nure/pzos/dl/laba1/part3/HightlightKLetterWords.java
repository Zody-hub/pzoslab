package ua.nure.pzos.dl.laba1.part3;

import java.util.Arrays;
import java.util.List;

public class HightlightKLetterWords {
    public String hightlight(String str) {
        StringBuilder res = new StringBuilder();
        List<String> words = Arrays.asList(str.split("[\\p{Punct}\\s]+"));
        for (String word : words) {
            if (word.charAt(0) == 'K' || word.charAt(0) == 'k') {
                res.append(word);
                res.append(" ");
            }
        }
        return res.toString();
    }
}
