package juego0.niveles;

import java.util.Vector;

import juego0.core.Fondo;
import juego0.core.ObjetoGrafico;

public abstract class Nivel extends Thread {
    protected int estado = 1;
    protected Vector<ObjetoGrafico> pendientesGraficos;
    protected Fondo fondo;
    public long[] diffSeconds;

    public Fondo getFondo() {
        return fondo;
    }

    public int getestado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public void setFinalizado() {
        estado = 0;
    }
}