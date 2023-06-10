package juego0.core;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.Vector;

import javax.imageio.ImageIO;

import com.entropyinteractive.Keyboard;

import juego0.armas.Arma;
import juego0.armas.ArmaBase;

public class P38 extends ObjetoGrafico {
    private int energia = 100;
    private Keyboard keyboard;
    private boolean interrumpirdisparo = false;
    private Arma arma;
    private Date dAhora, dDanio;
    private BufferedImage p38Invulnerable = null;

    public P38(Keyboard keyboard, Vector<ObjetoGrafico> pendientesGraficos) {
        super("images/1984/p38.png", 275, 700);
        this.keyboard = keyboard;
        dAhora = new Date();
        dDanio = new Date();
        arma = new ArmaBase(pendientesGraficos);
        try {
            p38Invulnerable = ImageIO
                    .read(getClass().getClassLoader().getResourceAsStream("images/1984/Invulnerable.png"));

        } catch (IOException e) {
            System.out.println(e);
        }
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
                arma.disparar(positionX + 23, positionY - (this.getHeight()));
                interrumpirdisparo = true;
            }
        } else
            interrumpirdisparo = false;
    }

    public int getEnergia() {
        return energia;
    }

    public void recibirDanio(int danio) {
        dDanio = new Date();
        energia -= danio;
    }

    public void recargarEnergia() {
        energia = 100;
    }

    public boolean invulnerable() {
        return (dAhora.getTime() - dDanio.getTime()) / 1000 % 60 < 1;
    }

    public void setDate() {
        dAhora = new Date();
    }

    @Override
    public void display(Graphics2D g2) {
        if (invulnerable())
            g2.drawImage(p38Invulnerable, (int) this.positionX, (int) this.positionY, null);
        else
            g2.drawImage(imagen, (int) this.positionX, (int) this.positionY, null);

    }

}
