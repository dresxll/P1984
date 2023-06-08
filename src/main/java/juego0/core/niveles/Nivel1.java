package juego0.core.niveles;

import java.util.Vector;
import juego0.enemigos.Enemigo;
import juego0.enemigos.Enemigo1;
import juego0.principal.Fondo;

public class Nivel1 extends Nivel {
    public Nivel1(Vector<Enemigo> enemigosEnCola) {
        fondo = new Fondo("images/1984/fondo.png");
        fondo.setPosition(9.5, -fondo.getHeight() + 610);
        this.enemigosEnCola=enemigosEnCola;
    }

    @Override
    public void run() {
        super.run();
        try {
            enemigosEnCola.add(new Enemigo1(120, 20));
            enemigosEnCola.add(new Enemigo1(120, 100));
            enemigosEnCola.add(new Enemigo1(120, 180));
            enemigosEnCola.add(new Enemigo1(650, 60));
            enemigosEnCola.add(new Enemigo1(650, 140));
            enemigosEnCola.add(new Enemigo1(650, 220));
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
