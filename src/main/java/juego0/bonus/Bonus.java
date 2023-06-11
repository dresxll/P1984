package juego0.bonus;

import java.util.Date;
import java.util.Random;
import juego0.core.ObjetoGrafico;
import juego0.core.P38;

public abstract class Bonus extends ObjetoGrafico {
    public Bonus(String filename) {
        super(filename, 0, 0);
        Random random = new Random();
        this.moverX(random.nextInt(400) + 100);

    }

    public void update() {
        this.setPosition(positionX, positionY + 2);
    }

    public void aplicar(P38 p38) {
        p38.eliminarBonus();
        this.borrar = true;
        p38.setdUltimoBonus(new Date());
    }

}
