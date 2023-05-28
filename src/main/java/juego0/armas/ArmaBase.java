package juego0.armas;

import juego0.core.Bucle;
import juego0.core.Disparo;

public class ArmaBase extends Arma {
    public ArmaBase() {
        ImagenDisparo = "images/1984/bala_simple.png";
    }
    public void disparar(double x, double y) {
        Disparo disparo = new Disparo(ImagenDisparo,x,y,0,-25,550);
        Bucle.ObjetoGraficos.add(disparo);
    }
}
 