package juego0.principal;

import java.util.Vector;

public class Explosion extends ObjetoGrafico {
    private double estado = 0;
    Vector<Explosion> explosionesLibres;
    public Explosion(Vector<Explosion> explosionesLibres,double x, double y) {
        super("images/1984/explosion0.png");
        this.setPosition(x, y);
        this.explosionesLibres=explosionesLibres;
    }

    @Override
    public void update() {
        estado+=0.2;
        if (estado < 5) {
            this.setImagen("images/1984/explosion" + (int)estado + ".png");
        } else if (!explosionesLibres.contains(this))
            explosionesLibres.add(this);
    }

}
