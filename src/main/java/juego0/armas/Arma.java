package juego0.armas;

import java.util.Vector;

import juego0.core.ObjetoGrafico;

public abstract class Arma {
    protected Vector<ObjetoGrafico> pendientesGraficos;
    public abstract void disparar(double x, double y);
}
