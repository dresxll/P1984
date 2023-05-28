package juego0;

public abstract class Enemigo extends ObjetoGrafico {
    boolean dir = false;

    public Enemigo(String filename) {
        super(filename);
    }
}
