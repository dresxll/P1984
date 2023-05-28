package juego0.niveles;

import juego0.core.Fondo;

public class Nivel1 extends Nivel {
    public Nivel1() {
    }
    public void iniciar(){
        fondo = new Fondo("images/1984/fondo.png");
        fondo.setPosition(9.5, -fondo.getHeight() + 610);
    }

    
}
