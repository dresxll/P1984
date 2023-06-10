package juego0.bonus.powerUps;

import juego0.core.P38;

public class Auto extends PowerUp{

    public Auto() {
        super("images/1984/Auto.png");
    }

    @Override
    public void aplicar(P38 p38) {
                p38.getArma().setRafaga(true);
                super.aplicar(p38);
    }
    
}
