package juego0.armas.disparos;

public class DisparoBase extends Disparo {

    public DisparoBase(double positionX, double positionY) {
        super("images/1984/bala_simple.png", positionX, positionY);
        this.delta.setLocation(0, -25);
        this.origen.setLocation(positionX, positionY);
        this.alcance=750;
        this.danio=1;
    }
    public DisparoBase(Boolean aliado,double positionX, double positionY,int danio) {
        super("images/1984/DisparoAliado.png", positionX, positionY);
        this.delta.setLocation(0, -25);
        this.origen.setLocation(positionX, positionY);
        this.alcance=750;
        this.danio=2;
    }
    
}
