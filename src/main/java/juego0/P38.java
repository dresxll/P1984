package juego0;

import java.awt.event.KeyEvent;
import com.entropyinteractive.Keyboard;

public class P38 extends ObjetoGrafico {

    final private double NAVE_DESPLAZAMIENTO = 250.0;
    private Arma arma = new Arma();
    private boolean interrumpirdisparo = false;

    public P38(String filename) {
        super(filename);
    }

    public void update(double delta, Keyboard keyboard) {
        if (keyboard.isKeyPressed(KeyEvent.VK_W) && positionY > 30)
            positionY -= 4;
        if (keyboard.isKeyPressed(KeyEvent.VK_S) && positionY < 556)
            positionY += 4;
        if (keyboard.isKeyPressed(KeyEvent.VK_A) && (positionX > 4))
            positionX -= 4;
        if (keyboard.isKeyPressed(KeyEvent.VK_D) && (positionX < 756))
            positionX += 4;
        if (keyboard.isKeyPressed(KeyEvent.VK_X)) {
            if (!interrumpirdisparo) {
                arma.disparar(positionX, positionY, arma.getTipoarma());
                interrumpirdisparo = true;
            }
        } else
            interrumpirdisparo = false;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}
