package juego0;

import java.awt.geom.Point2D;

public abstract class Disparo extends ObjetoGrafico {

    private Point2D.Double delta = new Point2D.Double();
    private Point2D.Double origen = new Point2D.Double();
    public Boolean fueraderango = false, libre = false;

    public Disparo(String filename, double x, double y, double deltax, double deltay) {
        super(filename);
        this.setOrigen(x, y);
        this.setPosition(x, y);
        this.setDelta(deltax, deltay);
    }

    private void setDelta(double deltax, double deltay) {
        delta.setLocation(deltax, deltay);
    }

    public double getDeltaX() {
        return delta.x;
    }

    public double getDeltaY() {
        return delta.y;
    }

    private void setOrigen(double x, double y) {
        this.origen.setLocation(x, y);
        ;
    }

    public Point2D.Double GetOrigen() {
        return this.origen;
    }

}
