package ua.nure.pzos.dl.laba1.part1;

public class LengthBellowAverage {
    public static double FindAvgLenght(int[] mas, int n) {
        double avgLen = 0;
        for (int i = 0; i < n; i++) {
            avgLen += String.valueOf(mas[i]).length();
        }
        avgLen /= n;
        return avgLen;
    }

    public static int[] GetBellowAvgLen(int[] mas, int n, char symbol) {
        int j = 0;
        double avgLen = FindAvgLenght(mas, n);
        int size = 0;
        switch (symbol) {
            case ('<') -> {
                for (int i = 0; i < n; i++) {
                    if ((double) String.valueOf(mas[i]).length() < avgLen) {
                        size++;
                    }
                }
                int[] res = new int[size];
                for (int i = 0; i < n; i++) {
                    if ((double) String.valueOf(mas[i]).length() < avgLen) {
                        res[j] = mas[i];
                        j++;
                    }
                }
                return res;
            }
            case ('>') -> {
                for (int i = 0; i < n; i++) {
                    if ((double) String.valueOf(mas[i]).length() > avgLen) {
                        size++;
                    }
                }
                int[] res = new int[size];
                for (int i = 0; i < n; i++) {
                    if ((double) String.valueOf(mas[i]).length() > avgLen) {
                        res[j] = mas[i];
                        j++;
                    }
                }
                return res;
            }
            default -> {
                return new int[0];
            }
        }
    }
}
