package juego0.enemigos;

import java.util.Vector;

public class Torreta extends Enemigo{
    
    public Torreta(String filename, double positionX, double positionY) {
        super(filename, positionX, positionY);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
     public int getEnergia(){
        return energia;
    }
    public int getEnergiaInicial(){
        return 1;
    }
    
}
