package juego0.niveles;

import java.util.Vector;

import juego0.bonus.CambioArma;
import juego0.core.ObjetoGrafico;
import juego0.enemigos.Enemigo1;

public class Nivel1 extends Nivel {
    public Nivel1(Vector<ObjetoGrafico> objetoGraficos) {
        fondo = new Fondo("images/1984/fondo.png");
        fondo.setY(-fondo.getHeight()+810);
        this.objetoGraficos=objetoGraficos;
    }
    @Override
    public void run() {
        super.run();
        try {;
            objetoGraficos.add(new Enemigo1(objetoGraficos,150, -20));
            objetoGraficos.add(new Enemigo1(objetoGraficos,150, -100));
            objetoGraficos.add(new Enemigo1(objetoGraficos,150, -180));
            sleep(1000);
            objetoGraficos.add(new Enemigo1(objetoGraficos,450, -60));
            objetoGraficos.add(new Enemigo1(objetoGraficos,450, -140));
            objetoGraficos.add(new Enemigo1(objetoGraficos,450, -220));
            sleep(1000);
            objetoGraficos.add(new CambioArma(200, 0));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
