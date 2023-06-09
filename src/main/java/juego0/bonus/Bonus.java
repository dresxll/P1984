package juego0.bonus;

import java.util.Vector;

import juego0.core.ObjetoGrafico;

public abstract class Bonus extends ObjetoGrafico {
    public Bonus(String filename, double x, double y) {
        super(filename,x,y);
    }

    public void update() {
        this.setPosition(positionX, positionY+2);
    }

}
