package juego0.core.niveles;

import java.util.Vector;

import juego0.principal.Fondo;
public abstract class Nivel extends Thread {
    protected Fondo fondo;
    public void run(){
        super.run();
    }
    protected Vector<Oleada> vectorOleadas;
    public Fondo getFondo(){
        return fondo;
    }
}
