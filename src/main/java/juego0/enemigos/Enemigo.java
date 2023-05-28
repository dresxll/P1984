package juego0.enemigos;

import juego0.core.ObjetoGrafico;

public abstract class Enemigo extends ObjetoGrafico {
    boolean dir = false;

    public Enemigo(String filename) {
        super(filename);
    }
}
