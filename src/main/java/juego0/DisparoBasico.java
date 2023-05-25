package juego0;

public class DisparoBasico extends Disparo {
    public DisparoBasico(double x, double y,double deltax ,double deltay) {
        super("images/1984/bala_simple.png", x, y,deltax ,deltay);
        this.alcance=550;
    }
    
}
