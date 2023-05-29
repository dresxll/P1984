package juego0.core;

import java.awt.event.KeyEvent;
import com.entropyinteractive.Keyboard;

import juego0.armas.Arma;
import juego0.armas.ArmaBase;

public class P38 extends ObjetoGrafico implements ObjetoMovible {

    private Arma arma = new ArmaBase();
    private boolean interrumpirdisparo = false;

    public P38() {
        super("images/1984/p38.png");
    }
 
    public void update(Keyboard keyboard) {
        if (keyboard.isKeyPressed(KeyEvent.VK_W) && positionY > 30)
            positionY -= 6;
        if (keyboard.isKeyPressed(KeyEvent.VK_S) && positionY < 556)
            positionY += 6;
        if (keyboard.isKeyPressed(KeyEvent.VK_A) && (positionX > 6))
            positionX -= 6;
        if (keyboard.isKeyPressed(KeyEvent.VK_D) && (positionX < 756))
            positionX += 6;
        if (keyboard.isKeyPressed(KeyEvent.VK_X)) {
            if (!interrumpirdisparo) {
                arma.disparar(positionX+(this.getWidth()/2), positionY-(this.getHeight()));
                interrumpirdisparo = true;
            }
        } else
            interrumpirdisparo = false;
    }

@Override
    public void update() {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    } 
}
