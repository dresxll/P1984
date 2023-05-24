package juego0;

public class Camara {

    private double x, y;
    private double resX, resY;

    public Camara(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setRegionVisible(double x, double y) {
        resX -= resX;
        resX -= resY;
        resX = x;
        resY = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }
}