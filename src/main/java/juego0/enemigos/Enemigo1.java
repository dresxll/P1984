package juego0.enemigos;

import java.awt.geom.Point2D;

public class Enemigo1 extends Enemigo {
    public Enemigo1(double x, double y) {
        super("images/1984/enemigo1.png");
        setPosition(x, y);
        delta = new Point2D.Double();
        delta.setLocation(0, 1);
        energia = 1;

    }

    @Override
    public void update() {
        this.setPosition(positionX + (Math.sin(Math.toRadians(positionY) * 5)) * 6, positionY + delta.y);

    }

    public void recibirDanio() {
        energia -= 1;
    }

    public int getEnergia() {
        return energia;
    }
}
