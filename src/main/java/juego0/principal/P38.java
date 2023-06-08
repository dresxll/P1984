package juego0.principal;

import java.awt.event.KeyEvent;
import java.util.Vector;

import com.entropyinteractive.Keyboard;

import juego0.armas.Arma;
import juego0.armas.ArmaBase;

public class P38 extends ObjetoGrafico implements ObjetoMovible {
    Vector<Disparo> disparos;
    private Arma arma;
    private boolean interrumpirdisparo = false;
    public P38(Vector<Disparo> disparos) {
        super("images/1984/p38.png");
        this.disparos=disparos;
        arma= new ArmaBase(disparos);
    }
 
    public void update(Keyboard keyboard) {
        if (keyboard.isKeyPressed(KeyEvent.VK_W) && positionY > 30)
            positionY -= 6;
        if (keyboard.isKeyPressed(KeyEvent.VK_S) && positionY < 556)
            positionY += 6;
        if (keyboard.isKeyPressed(KeyEvent.VK_A) && (positionX > 6))
            positionX -= 6;
        if (keyboard.isKeyPressed(KeyEvent.VK_D) && (positionX < 736))
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
    }

}
