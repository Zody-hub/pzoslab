package ua.nure.pzos.dl.laba1.part2;


public class Rhombus extends Quadrangle {
    public Rhombus(Dot a, Dot b, Dot c, Dot d) {
        double line = a.getDistance(b);
        if (b.getDistance(c) == line && c.getDistance(d) == line && d.getDistance(a) == line && a.getDistance(c) != b.getDistance(d)) {
            this.A = a;
            this.B = b;
            this.C = c;
            this.D = d;
        } else {
            throw new IllegalArgumentException("Rhombus with this vertexes does not exists");
        }
    }

    @Override
    public double getSqaure() {
        return 0.5 * A.getDistance(C) * D.getDistance(B);
    }
}
