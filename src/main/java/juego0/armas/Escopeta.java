package juego0.armas;


import juego0.armas.disparos.DisparoEscopeta;

public class Escopeta extends Arma {
    int danio = 2;
    @Override
    public void disparar(double x, double y) {
        pendientesGraficos.add(new DisparoEscopeta(danio,x-10, y+60, 0));
        pendientesGraficos.add(new DisparoEscopeta(danio,x-10, y+60, -6));
        pendientesGraficos.add(new DisparoEscopeta(danio,x-10, y+60, -20));
        pendientesGraficos.add(new DisparoEscopeta(danio,x+10, y+60, 0));
        pendientesGraficos.add(new DisparoEscopeta(danio,x+10, y+60, +6));
        pendientesGraficos.add(new DisparoEscopeta(danio,x+10, y+60, +20));
    }
    @Override
    public void mejorar() {
        this.danio=4;
    }


}
