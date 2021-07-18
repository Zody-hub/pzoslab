package ua.nure.pzos.dl.laba1.part2;

public class Dot {
    protected int x;
    protected int y;
    protected int dx;
    protected int dy;

    public Dot(int x, int y) {
        this.x = x;
        this.y = y;
        this.dx = 0;
        this.dy = 0;
    }

    public Dot(int x, int y, int dx, int dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
    }

    public double getSpeed() {
        return getDistance(x + dx, y + dy);
    }

    private void move(int t) {
        this.x = x + dx * t;
        this.y = y + dy * t;
    }

    public double getDistance(Dot dot) {
        return Math.sqrt(Math.pow(dot.getX() - this.x, 2) + Math.pow(dot.getY() - this.y, 2));
    }

    private double getDistance(int x, int y) {
        return Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
