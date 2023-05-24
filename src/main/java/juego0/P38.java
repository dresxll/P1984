package juego0;

import java.awt.event.KeyEvent;
import java.util.Vector;
import com.entropyinteractive.Keyboard;

public class P38 extends ObjetoGrafico implements ObjetoMovible {

    final private double NAVE_DESPLAZAMIENTO = 150.0;
    private Arma arma = new Arma();
    private Vector<DisparoBasico> disparos_p38;
    private boolean interrumpirdisparo = false;

    public P38(String filename) {
        super(filename);
    }

    public void update(double delta, Keyboard keyboard) {
        positionY-=0.5;
        if (keyboard.isKeyPressed(KeyEvent.VK_W))
            this.setPosition(this.getX(), (this.getY() - NAVE_DESPLAZAMIENTO * delta));
        if (keyboard.isKeyPressed(KeyEvent.VK_S))
            this.setPosition(this.getX(), (this.getY() + NAVE_DESPLAZAMIENTO * delta));
        if (keyboard.isKeyPressed(KeyEvent.VK_A) && (positionX - NAVE_DESPLAZAMIENTO * delta) > 0)
            this.setPosition((this.getX() - NAVE_DESPLAZAMIENTO * delta), this.getY());
        if (keyboard.isKeyPressed(KeyEvent.VK_D) && ((positionX + this.getWidth()) < 800))
            this.setPosition((this.getX() + NAVE_DESPLAZAMIENTO * delta), this.getY());
        if (keyboard.isKeyPressed(KeyEvent.VK_X)) {
            if (!interrumpirdisparo) {
                arma.disparar(this.getX(), this.getY(), arma.getTipoarma(), disparos_p38);
                interrumpirdisparo = false;
            }
        } else
            interrumpirdisparo = false;
    }

    public void setVector(Vector<DisparoBasico> disparos_p38) {
        this.disparos_p38 = disparos_p38;
    }
}
