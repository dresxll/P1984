package juego0.Jefes;

import java.util.Vector;

import juego0.enemigos.Enemigo;
import juego0.enemigos.Torreta;

public abstract class Jefe extends Enemigo{

    public Jefe(String filename, double positionX, double positionY) {
        super(filename, positionX, positionY);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    public abstract Vector<Torreta> getTorretas();
     public abstract double getDanioAcumulado();
    
}
