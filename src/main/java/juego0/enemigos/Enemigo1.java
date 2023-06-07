package juego0.enemigos;

public class Enemigo1 extends Enemigo {
    public Enemigo1(double x, double y) {
        super("images/1984/enemigo1.png");
        setPosition(x, y);
        delta.setLocation(-1, 0);
        energia = 100;
    }

    @Override
    public void update() {
        this.setPosition(positionX + delta.x, positionY + delta.y);

    }

    public void recibirDanio(int danio) {
        energia-=danio;
    }
    public int getEnergia() {
        return energia;
    }
}
