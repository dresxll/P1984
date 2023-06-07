package juego0.core.niveles;

import juego0.core.Bucle;
import juego0.enemigos.Enemigo1;
import juego0.principal.Fondo;

public class Nivel1 extends Nivel {
    public Nivel1() {
        fondo = new Fondo("images/1984/fondo.png");
        fondo.setPosition(9.5, -fondo.getHeight() + 610);
    }

    @Override
    public void run() {
        super.run();
        try {
            Bucle.enemigosEnCola.add(new Enemigo1(120, 20));
            Bucle.enemigosEnCola.add(new Enemigo1(120, 100));
            Bucle.enemigosEnCola.add(new Enemigo1(120, 180));
            Bucle.enemigosEnCola.add(new Enemigo1(650, 60));
            Bucle.enemigosEnCola.add(new Enemigo1(650, 140));
            Bucle.enemigosEnCola.add(new Enemigo1(650, 220));
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
