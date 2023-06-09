package juego0.core;

import java.awt.event.KeyEvent;
import java.util.Vector;

import com.entropyinteractive.Keyboard;

import juego0.armas.Arma;
import juego0.armas.ArmaBase;

public class P38 extends ObjetoGrafico {
    private Vector<ObjetoGrafico> objetosGraficos;
    private Keyboard keyboard;
    private boolean interrumpirdisparo = false;
    private Arma arma = new ArmaBase(objetosGraficos);
    public P38(Keyboard keyboard, Vector<ObjetoGrafico> objetosGraficos) {
        super("images/1984/p38.png", 350, 500);
        this.keyboard=keyboard;
        this.objetosGraficos=objetosGraficos;
        arma = new ArmaBase(objetosGraficos);
    }

    @Override
    public void update() {
        if (keyboard.isKeyPressed(KeyEvent.VK_W) && positionY > 30)
            positionY -= 3;
        if (keyboard.isKeyPressed(KeyEvent.VK_S) && positionY < 756)
            positionY += 3;
        if (keyboard.isKeyPressed(KeyEvent.VK_A) && (positionX > 6))
            positionX -= 3;
        if (keyboard.isKeyPressed(KeyEvent.VK_D) && (positionX < 536))
            positionX += 3;
        if (keyboard.isKeyPressed(KeyEvent.VK_X)) {
            if (!interrumpirdisparo) {
                arma.disparar(positionX+23, positionY-(this.getHeight()));
                interrumpirdisparo = true;
            }
        } else
            interrumpirdisparo = false;
    }
}
