package juego0.niveles;

import java.util.Vector;

import juego0.core.Fondo;
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
