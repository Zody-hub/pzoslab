package ua.nure.pzos.dl.laba1.part2;

public class ArbitraryQuadrangle extends Quadrangle {
    public ArbitraryQuadrangle(Dot a, Dot b, Dot c, Dot d) {
        double line = a.getDistance(b);
        if (line < b.getDistance(c) + c.getDistance(d) + d.getDistance(a)) {
            this.A = a;
            this.B = b;
            this.C = c;
            this.D = d;
        } else {
            throw new IllegalArgumentException("ArbitraryQuadrangle with this vertexes does not exists");
        }
    }

    @Override
    public double getSqaure() {
        double p = (double) getPerimeter() / 2;
        return Math.sqrt((p - A.getDistance(B)) * (p - B.getDistance(C)) * (p - C.getDistance(D)) * (p - D.getDistance(A)));
    }
}
