package juego0.armas.disparos;

import java.awt.geom.Point2D;

import juego0.core.ObjetoGrafico;

public abstract class Disparo extends ObjetoGrafico{

    protected double alcance;
    protected Point2D.Double origen = new Point2D.Double();
    protected Point2D.Double delta = new Point2D.Double();
    protected int danio;

    public Disparo(String filename, double positionX, double positionY) {
        super(filename, positionX, positionY);
    }

    @Override
    public void update() {
        this.positionX+=delta.x;
        this.positionY+=delta.y;
        if(this.origen.distance(positionX, positionY)>alcance) {
            borrar = true;
        }
    }
    public int getDanio(){
        return danio;
    }

    
}
