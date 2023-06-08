package juego0.armas;

import java.util.Vector;

import juego0.principal.Disparo;

public abstract class Arma {
    protected String ImagenDisparo;
    protected Vector<Disparo> disparos;
    public abstract void disparar(double x, double y);
}
