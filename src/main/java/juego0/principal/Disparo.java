package juego0.principal;

import java.awt.geom.Point2D;
import java.util.Vector;

public class Disparo extends ObjetoGrafico {
    public Boolean fueraderango = false;
    Vector<Disparo> disparosLibres;
    private double alcance;
    private Point2D.Double delta = new Point2D.Double();
    private Point2D.Double origen = new Point2D.Double();
    public Disparo(Vector<Disparo> disparosLibres,String rutaImagen, double x, double y, double deltax, double deltay, double alcance , int danio) {
        super(rutaImagen);
        positionX=x-(this.getWidth()/2);
        positionY=y;
        this.origen.setLocation(x,y);
        this.delta.setLocation(deltax, deltay);
        this.alcance = alcance;
        this.disparosLibres=disparosLibres;
    }

    @Override
    public void update() {
        this.positionX+=delta.x;
        this.positionY+=delta.y;
        if(this.origen.distance(positionX, positionY)>alcance) {
            disparosLibres.add(this);
        }
    }

}
