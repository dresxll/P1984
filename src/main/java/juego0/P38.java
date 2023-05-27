package juego0;

import java.awt.event.KeyEvent;
import com.entropyinteractive.Keyboard;

public class P38 extends ObjetoGrafico implements ObjetoMovible {

    final private double NAVE_DESPLAZAMIENTO = 250.0;
    private Arma arma = new Arma();
    private boolean interrumpirdisparo = false;

    public P38(String filename) {
        super(filename);
    }

    public void update(double delta, Keyboard keyboard) {
        if (keyboard.isKeyPressed(KeyEvent.VK_W))
            this.setPosition(this.getX(), (this.getY() - NAVE_DESPLAZAMIENTO * delta));
        if (keyboard.isKeyPressed(KeyEvent.VK_S))
            this.setPosition(this.getX(), (this.getY() + NAVE_DESPLAZAMIENTO * delta));
        if (keyboard.isKeyPressed(KeyEvent.VK_A) && (getX() - NAVE_DESPLAZAMIENTO * delta) > 0)
            this.setPosition((this.getX() - NAVE_DESPLAZAMIENTO * delta), this.getY());
        if (keyboard.isKeyPressed(KeyEvent.VK_D) && ((getX() + this.getWidth()) < 800))
            this.setPosition((this.getX() + NAVE_DESPLAZAMIENTO * delta), this.getY());
        if (keyboard.isKeyPressed(KeyEvent.VK_X)) {
            if (!interrumpirdisparo) {
                arma.disparar(this.getX()+(5), this.getY()-40, arma.getTipoarma());
                interrumpirdisparo = true;
            }
        } else
            interrumpirdisparo = false;
    }
}
