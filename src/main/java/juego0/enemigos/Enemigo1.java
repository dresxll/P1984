package juego0.enemigos;


public class Enemigo1 extends Enemigo{
    public Enemigo1(double x, double y) {
        super("images/1984/enemigo1.png");
        this.setPosition(x, y);
        this.delta.setLocation(-1,0);
    }

    @Override
    public void update() {
        this.setPosition(positionX + delta.x, positionY);
        
    }

}
