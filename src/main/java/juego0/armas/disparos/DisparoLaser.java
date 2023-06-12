package juego0.armas.disparos;

public class DisparoLaser extends Disparo {
    int count = 0;

    public DisparoLaser(int danio, double positionX, double positionY) {
        super("images/1984/Laser.png", positionX-4, positionY);
        this.moverY(-this.getHeight()+60);
        this.alcance = 750;
        this.danio = danio;
    }

    @Override
    public void update() {
        if (count >= 5)
            borrar = true;
        else
            count++;
    }
}
