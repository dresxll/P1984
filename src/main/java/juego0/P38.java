package juego0;

import java.awt.event.KeyEvent;
import com.entropyinteractive.Keyboard;

public class P38 extends ObjetoGrafico {

    private Arma arma = new ArmaBase();
    private boolean interrumpirdisparo = false;

    public P38(String filename) {
        super(filename);
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
                arma.disparar(positionX, positionY);
                interrumpirdisparo = true;
            }
        } else
            interrumpirdisparo = false;
    }

@Override
    public void update() {
        //Método no implementado.
    } 
}
