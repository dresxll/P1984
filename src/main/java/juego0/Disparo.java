package juego0;

import java.awt.geom.Point2D;

public class Disparo extends ObjetoGrafico {
    public Boolean fueraderango = false;
    private double alcance;
    private Point2D.Double delta = new Point2D.Double();
    private Point2D.Double origen = new Point2D.Double();
    public Disparo(String rutaImagen, double x, double y, double deltax, double deltay, double alcance) {
        super(rutaImagen);
        positionX=x;
        positionY=y;
        this.origen.setLocation(x,y);
        this.delta.setLocation(deltax, deltay);
        this.alcance = alcance;
    }

    @Override
    public void update() {
        this.positionX+=delta.x;
        this.positionY+=delta.y;
        if(this.origen.distance(positionX, positionY)>this.alcance) {
            Bucle.ObjetosLibres.add(this);
        }
    }
}
