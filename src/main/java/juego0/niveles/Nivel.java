package juego0.niveles;

import java.util.Vector;

import juego0.core.ObjetoGrafico;

public abstract class Nivel extends Thread {
    public Vector<ObjetoGrafico> pendientesGraficos;
    public Fondo fondo;
    public void run(){
        super.run();
    }
    public Fondo getFondo(){
        return fondo;
    }
}