package juego0.enemigos;

import java.awt.geom.Point2D;

import juego0.principal.ObjetoGrafico;

public abstract class Enemigo extends ObjetoGrafico {
    int energia;
    protected Point2D.Double delta = new Point2D.Double();
    public Enemigo(String filename) {
        super(filename);
    }
    public abstract void recibirDanio(int danio);
    public abstract int getEnergia();
}
