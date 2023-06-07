package juego0.enemigos;

import java.awt.geom.Point2D;

import juego0.principal.ObjetoGrafico;

public abstract class Enemigo extends ObjetoGrafico {
    protected int energia;
    protected Point2D.Double delta;
    public Enemigo(String filename) {
        super(filename);
    }
    public abstract void recibirDanio();
    public abstract int getEnergia();
}
