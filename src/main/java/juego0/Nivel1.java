package juego0;

public class Nivel1 extends Nivel {
    public Nivel1() {
    }
    public void iniciar(){
        fondo = new Fondo("images/1984/fondo.jpg");
        fondo.setPosition(9.5, -fondo.getHeight() + 610);
    }

    
}
