package juego0.niveles;

import java.util.Vector;

import juego0.Jefes.Yamato;
import juego0.core.Fondo;
import juego0.core.ObjetoGrafico;
import juego0.enemigos.Enemigo1;

public class Nivel1 extends Nivel {
    public Nivel1(Vector<ObjetoGrafico> pendientesGraficos, long[] diffSeconds) {
        fondo = new Fondo("images/1984/fondo.png");
        fondo.setY(-fondo.getHeight() + 810);
        this.pendientesGraficos = pendientesGraficos;
        this.diffSeconds = diffSeconds;
    }

    @Override
    public void run() {

        while (this.diffSeconds[0] < 2) {
            try {
                sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        pendientesGraficos.add(new Enemigo1(pendientesGraficos, 150, -20));
        pendientesGraficos.add(new Enemigo1(pendientesGraficos, 150, -100));
        pendientesGraficos.add(new Enemigo1(pendientesGraficos, 150, -180));
        while (this.diffSeconds[0] < 6) {
            try {
                sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        pendientesGraficos.add(new Enemigo1(pendientesGraficos, 450, -60));
        pendientesGraficos.add(new Enemigo1(pendientesGraficos, 450, -140));
        pendientesGraficos.add(new Enemigo1(pendientesGraficos, 450, -220));
        while (this.diffSeconds[0] < 20) {
            try {
                sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.estado = 4;
        // Aquí tendría que haber otro tramo de enemigos chicos
        jefe = new Yamato();
        while (this.diffSeconds[0] < 60) {
            try {
                sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        estado = 6;
    }
}
