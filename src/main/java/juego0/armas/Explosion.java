package juego0.armas;

import java.util.Vector;

import juego0.core.ObjetoGrafico;

public class Explosion extends ObjetoGrafico {
    private double estado = 0;
    Vector<ObjetoGrafico> objetoGraficos;

    public Explosion(Vector<ObjetoGrafico> objetoGraficos, double x, double y) {
        super("images/1984/explosion0.png",x,y);
        this.setPosition(x, y);
        this.objetoGraficos = objetoGraficos;
    }

    @Override
    public void update() {
        estado += 0.2;
        if (estado < 5) {
            this.setImagen("images/1984/explosion" + (int) estado + ".png");
        } else this.borrar=true;
    }

}