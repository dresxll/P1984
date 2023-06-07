package juego0.principal;

import java.awt.geom.Point2D;

import juego0.core.Bucle;

public class Disparo extends ObjetoGrafico {
    public Boolean fueraderango = false;
    private double alcance;
    private int danio;
    private Point2D.Double delta = new Point2D.Double();
    private Point2D.Double origen = new Point2D.Double();
    public Disparo(String rutaImagen, double x, double y, double deltax, double deltay, double alcance , int danio) {
        super(rutaImagen);
        positionX=x-(this.getWidth()/2);
        positionY=y;
        this.origen.setLocation(x,y);
        this.delta.setLocation(deltax, deltay);
        this.alcance = alcance;
        this.danio=danio;
    }

    @Override
    public void update() {
        this.positionX+=delta.x;
        this.positionY+=delta.y;
        if(this.origen.distance(positionX, positionY)>alcance) {
            Bucle.disparosLibres.add(this);
        }
    }

    public int getDanio() {
        return danio;
    }
}
