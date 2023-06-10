package juego0.bonus.powerUps;

import juego0.core.P38;

public class POW extends PowerUp{

    public POW() {
        super("images/1984/POW.png");
    }

    @Override
    public void aplicar(P38 p38) {
                this.borrar=true;

    }
    
    
}
