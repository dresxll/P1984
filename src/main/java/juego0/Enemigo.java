package juego0;

import java.awt.geom.Point2D;

public class Enemigo extends ObjetoGrafico {
    private Point2D.Double delta = new Point2D.Double();
    public Enemigo(String filename) {
        super(filename);
    }


    @Override
    public void update() {

    }
}
