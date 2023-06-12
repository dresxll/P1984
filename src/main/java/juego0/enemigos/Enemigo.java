package juego0.enemigos;

import juego0.armas.disparos.Disparo;
import juego0.core.Hiteable;
import juego0.core.ObjetoGrafico;

public abstract class Enemigo extends ObjetoGrafico implements Hiteable {

    protected int energia =1 ;
    protected boolean chico = false;
    protected boolean buque = false;

    public Enemigo(String filename, double positionX, double positionY) {
        super(filename, positionX, positionY);
    }

    public void recibirDisparo(Disparo disparo) {
        energia -= disparo.getDanio();
        if (energia <= 0) {
            borrar = true;
        }
        disparo.setBorrar(true);
    }
    public void recibirDanio(int danio) {
        energia -= danio;
        if (energia <= 0) {
            borrar = true;
        }
    }
    public boolean getChico(){
        return chico;
    }
}
