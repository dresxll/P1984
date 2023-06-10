package juego0.enemigos;

import java.util.Vector;

import juego0.core.ObjetoGrafico;

public class Enemigo1 extends Enemigo {
    public Enemigo1(Vector<ObjetoGrafico> objetoGraficos, double x, double y) {
        super("images/1984/enemigo1.png", x, y);
        setPosition(x, y);
        energia = 2;
        objetoGraficos.add(this);
    }

    @Override
    public void update() {
        this.setPosition(positionX + (Math.sin(Math.toRadians(positionY))*2), positionY + 2);

    }

    
}
