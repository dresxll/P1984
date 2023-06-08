package juego0.core.niveles;

import java.util.Vector;

import juego0.enemigos.Enemigo;
import juego0.principal.Fondo;
public abstract class Nivel extends Thread {
    Vector<Enemigo> enemigosEnCola;
    protected Fondo fondo;
    public void run(){
        super.run();
    }
    protected Vector<Oleada> vectorOleadas;
    public Fondo getFondo(){
        return fondo;
    }
}
