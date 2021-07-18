package ua.nure.pzos.dl.laba1.part2;


public abstract class Quadrangle {
    protected Dot A, B, C, D;

    public int getPerimeter() {
        return (int) (A.getDistance(B) + B.getDistance(C) + C.getDistance(D) + D.getDistance(A));
    }

    abstract public double getSqaure();
}
