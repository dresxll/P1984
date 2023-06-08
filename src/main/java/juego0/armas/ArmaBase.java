package juego0.armas;

import java.util.Vector;

import juego0.principal.Disparo;

public class ArmaBase extends Arma {
    Vector<Disparo> disparosLibres;
    public ArmaBase(Vector<Disparo> disparos,Vector<Disparo> disparosLibres) {
        ImagenDisparo = "images/1984/bala_simple.png";
        this.disparos=disparos;
        this.disparosLibres=disparosLibres;
        
    }
    public void disparar(double x, double y) {
        Disparo disparo = new Disparo(disparosLibres,ImagenDisparo,x,y,0,-15,550,1);
        disparos.add(disparo);
    }
}
 