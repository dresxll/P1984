package juego0.bonus;

import java.util.Random;

public class CambioArma extends Bonus {
    private int arma;

    public CambioArma() {
        super("images/1984/CambioArma.png");
        Random random = new Random();
        arma = random.nextInt(2)+1;
    }

}
