package juego0.armas;

import juego0.armas.disparos.DisparoBase;

public class ArmaBase extends Arma {

    @Override
    public void disparar(double x, double y) {
        switch(rafaga){
            case 1:
            pendientesGraficos.add(new DisparoBase(x, y+60));
            break;
            case 2:
            pendientesGraficos.add(new DisparoBase(x, y+60));
            pendientesGraficos.add(new DisparoBase(x, y));
            break;
            case 3:
            pendientesGraficos.add(new DisparoBase(x, y+60));
            pendientesGraficos.add(new DisparoBase(x, y));
            pendientesGraficos.add(new DisparoBase(x, y-60));
            break;
        }
    }

    @Override
    public void mejorar() {
        throw new UnsupportedOperationException("Unimplemented method 'mejorar'");
    }
}
