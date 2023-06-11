package juego0.armas;


import juego0.armas.disparos.DisparoLaser;

public class Laser extends Arma {
    int danio = 3;
    public Laser() {
    }

    @Override
    public void disparar(double x, double y) {
        pendientesGraficos.add(new DisparoLaser(danio,x, y));
    }

    @Override
    public void mejorar() {
        this.danio=6;
    }


}
