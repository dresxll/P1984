package juego0.bonus.powerUps;

import juego0.core.P38;

public class SuperShell extends PowerUp{

    public SuperShell() {
        super("images/1984/SuperShell.png");
    }

    @Override
    public void aplicar(P38 p38) {
                this.borrar=true;

    }
    
}
