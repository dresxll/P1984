package juego0.armas;


import java.util.Vector;

import juego0.armas.disparos.DisparoBase;
import juego0.armas.disparos.DisparoEscopeta;
import juego0.core.ObjetoGrafico;

public class Escopeta extends Arma {
    public Escopeta(Vector<ObjetoGrafico> pendientesGraficos) {
        this.pendientesGraficos = pendientesGraficos;
    }

    @Override
    public void disparar(double x, double y) {
        pendientesGraficos.add(new DisparoEscopeta(x, y+60, 0));
    }


}
