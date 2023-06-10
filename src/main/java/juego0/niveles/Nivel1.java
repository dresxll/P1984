package juego0.niveles;

import java.util.Vector;

import juego0.core.Fondo;
import juego0.core.ObjetoGrafico;
import juego0.enemigos.Enemigo1;

public class Nivel1 extends Nivel {
    public Nivel1(Vector<ObjetoGrafico> pendientesGraficos) {
        fondo = new Fondo("images/1984/fondo.png");
        fondo.setY(-fondo.getHeight()+810);
        this.pendientesGraficos=pendientesGraficos;
    }
    @Override
    public void run() {
        super.run();
        try {;
            pendientesGraficos.add(new Enemigo1(pendientesGraficos,150, -20));
            pendientesGraficos.add(new Enemigo1(pendientesGraficos,150, -100));
            pendientesGraficos.add(new Enemigo1(pendientesGraficos,150, -180));
            sleep(5000);
            pendientesGraficos.add(new Enemigo1(pendientesGraficos,450, -60));
            pendientesGraficos.add(new Enemigo1(pendientesGraficos,450, -140));
            pendientesGraficos.add(new Enemigo1(pendientesGraficos,450, -220));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
