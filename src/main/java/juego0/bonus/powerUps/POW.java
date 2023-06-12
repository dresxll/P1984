package juego0.bonus.powerUps;

import java.util.Random;

import juego0.core.P38;

public class POW extends PowerUp {

    public POW() {
        super("images/1984/POW.png");
    }

    @Override
    public void aplicar(P38 p38) {
 super.aplicar(p38);
        Random random = new Random();
        if (p38.getEnergia() < 100)
            p38.recargarEnergia(random.nextInt(100 - p38.getEnergia()+1));
       
    }

}
