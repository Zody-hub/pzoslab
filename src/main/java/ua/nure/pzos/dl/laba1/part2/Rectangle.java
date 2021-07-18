package ua.nure.pzos.dl.laba1.part2;


public class Rectangle extends Quadrangle {
    public Rectangle(Dot a, Dot b, Dot c, Dot d) {
        double line = a.getDistance(c);
        if (line == b.getDistance(d) && a.getDistance(b) != b.getDistance(c)) {
            this.A = a;
            this.B = b;
            this.C = c;
            this.D = d;
        } else {
            throw new IllegalArgumentException("Rectangle with this vertexes does not exists");
        }
    }

    @Override
    public double getSqaure() {
        return A.getDistance(B) * B.getDistance(C);
    }
}
