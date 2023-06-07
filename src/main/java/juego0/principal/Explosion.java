package juego0.principal;

import juego0.core.Bucle;

public class Explosion extends ObjetoGrafico {
    private double estado = 0;

    public Explosion(double x, double y) {
        super("images/1984/explosion0.png");
        this.setPosition(x, y);
    }

    @Override
    public void update() {
        estado+=0.2;
        if (estado < 5) {
            this.setImagen("images/1984/explosion" + (int)estado + ".png");
        } else if (!Bucle.explosionesLibres.contains(this))
            Bucle.explosionesLibres.add(this);
    }

}
