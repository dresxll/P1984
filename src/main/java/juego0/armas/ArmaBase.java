package juego0.armas;


import java.util.Vector;

import juego0.armas.disparos.DisparoBase;
import juego0.core.ObjetoGrafico;

public class ArmaBase extends Arma {
    public ArmaBase(Vector<ObjetoGrafico> objetosGraficos) {
        this.objetosGraficos = objetosGraficos;
    }

    @Override
    public void disparar(double x, double y) {
        objetosGraficos.add(new DisparoBase(x, y));
    }


}
