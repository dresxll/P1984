package juego0;

import java.awt.geom.Point2D;

public class Disparo extends ObjetoGrafico {
    private Point2D.Double delta = new Point2D.Double();
    private Point2D.Double origen = new Point2D.Double();
    public Boolean fueraderango = false;
    protected double alcance;

    public Disparo(String rutaImagen, double x, double y, double deltax, double deltay, double alcance) {
        super(rutaImagen);
        this.setOrigen(x, y);
        this.setPosition(x, y);
        this.setDelta(deltax, deltay);
        this.alcance=alcance;
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
    public double getAlcance() {
        return this.alcance;
    }

}
