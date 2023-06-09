package juego0.enemigos;

import juego0.core.ObjetoGrafico;

public abstract class Enemigo extends ObjetoGrafico {
    
    protected int energia;
    public Enemigo(String filename, double positionX, double positionY) {
        super(filename, positionX, positionY);
    }
    public void recibirDanio(int danio) {
        energia -= danio;
        if (energia<=0){
            borrar=true;
        }
        
        
    }
}
