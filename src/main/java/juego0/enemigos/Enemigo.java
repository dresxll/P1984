package juego0.enemigos;

import java.awt.geom.Point2D;

import juego0.core.ObjetoGrafico;

public abstract class Enemigo extends ObjetoGrafico {
    protected Point2D.Double delta = new Point2D.Double();
    public Enemigo(String filename) {
        super(filename);
    }
}
