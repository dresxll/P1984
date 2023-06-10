package juego0.bonus;

import java.util.Random;
import juego0.core.ObjetoGrafico;

public abstract class Bonus extends ObjetoGrafico {
    public Bonus(String filename) {
        super(filename,0,0);
        Random random = new Random();
        this.moverX(random.nextInt(400)+100);

    }

    public void update() {
        this.setPosition(positionX, positionY+2);
    }

}
