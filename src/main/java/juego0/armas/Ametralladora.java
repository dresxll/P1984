package juego0.armas;

import juego0.armas.disparos.DisaparoAmetralladora;
public class Ametralladora extends Arma {
   int danio = 1;
    @Override
    public void disparar(double x, double y) {
        pendientesGraficos.add(new DisaparoAmetralladora(danio,x+12, y+60, 0));
        pendientesGraficos.add(new DisaparoAmetralladora(danio,x-8, y+60, -7));
        pendientesGraficos.add(new DisaparoAmetralladora(danio,x+32, y+60, 7));
    }
    @Override
    public void mejorar() {
        this.danio=2;
    }


}
