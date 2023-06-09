package juego0.armas;


import java.util.Vector;

import juego0.armas.disparos.DisparoBase;
import juego0.core.ObjetoGrafico;

public class ArmaBase extends Arma {
    public ArmaBase(Vector<ObjetoGrafico> pendientesGraficos) {
        this.pendientesGraficos = pendientesGraficos;
    }

    @Override
    public void disparar(double x, double y) {
        pendientesGraficos.add(new DisparoBase(x, y));
    }


}
