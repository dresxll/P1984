package juego0.niveles;

import java.util.Vector;

import juego0.core.Fondo;
public abstract class Nivel {
    protected Fondo fondo;
    public abstract void iniciar();
    protected Vector<Oleada> vectorOleadas;
    public Fondo getFondo(){
        return fondo;
    }
}
