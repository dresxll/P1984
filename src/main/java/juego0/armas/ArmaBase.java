package juego0.armas;

import java.util.Vector;

import juego0.principal.Disparo;

public class ArmaBase extends Arma {

    public ArmaBase(Vector<Disparo> disparos) {
        ImagenDisparo = "images/1984/bala_simple.png";
        this.disparos=disparos;
    }
    public void disparar(double x, double y) {
        Disparo disparo = new Disparo(ImagenDisparo,x,y,0,-15,550,1);
        disparos.add(disparo);
    }
}
 