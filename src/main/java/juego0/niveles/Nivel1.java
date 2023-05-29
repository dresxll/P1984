package juego0.niveles;

import juego0.core.Bucle;
import juego0.core.Fondo;
import juego0.enemigos.Enemigo1;

public class Nivel1 extends Nivel {
    public Nivel1() {
        fondo = new Fondo("images/1984/fondo.png");
        fondo.setPosition(9.5, -fondo.getHeight() + 610);
    }
    @Override
    public void run() {
        super.run();
           try {
            Bucle.ObjetoGraficos.add(new Enemigo1(700, 100));
            sleep(1000);
            Bucle.ObjetoGraficos.add(new Enemigo1(100, 100));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
      
    }

    
}
