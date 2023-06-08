package juego0.bonus;

import java.util.Vector;

import juego0.core.Bucle;
import juego0.principal.Disparo;
import juego0.principal.ObjetoGrafico;

public abstract class Bonus extends ObjetoGrafico{
    Vector<Disparo> disparos;
    public Bonus(String filename,Vector<Disparo> disparos) {
        super(filename);
        this.disparos=disparos;
    }

    public void update(){
        for (Disparo disparo : disparos) {
            if (Bucle.intersección(disparo, this)) {
                Bucle.disparosLibres.add(disparo);
                
            }
        }
    }

}
