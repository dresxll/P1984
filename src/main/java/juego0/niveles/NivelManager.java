package juego0.niveles;

import java.util.Vector;

import juego0.core.ObjetoGrafico;

public class NivelManager {
    private Nivel nivel;
    private GeneradorBonus generadorBonus;
    //private Vector<ObjetoGrafico> pendientesGraficos;

    public NivelManager(Vector<ObjetoGrafico> pendientesGraficos) {
    //    this.pendientesGraficos = pendientesGraficos;
        generadorBonus = new GeneradorBonus(pendientesGraficos);
        nivel = new Nivel1(pendientesGraficos);
        nivel.start();
        generadorBonus.start();
    }

    public Nivel getNivel() {
        return nivel;
    }
}
