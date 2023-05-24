package juego0;

import com.entropyinteractive.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.*;

public class Bucle extends JGame {

    private Date dInit = new Date(), dAhora;
    private Camara camara;
    private Fondo fondo;
    private Vector<DisparoBasico> vector_DisparosBasicos = new Vector<>();
    private P38 p38 = new P38("images/1984/p38.png");

    public static void main(String[] args) {
        Bucle game = new Bucle();
        game.run(1.0 / 60.0);
        System.exit(0);
    }

    public Bucle() {
        super("App", 800, 600);
        System.out.println(appProperties.stringPropertyNames());

    }

    public void gameStartup() {
        System.out.println("gameStartup");
        try {
            fondo = new Fondo("images/1984/fondo.jpg");
            p38.setPosition(400, 500);
            camara = new Camara(0, 0);
            camara.setRegionVisible(800, 600);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void gameUpdate(double delta) {
        Keyboard keyboard = this.getKeyboard();
        LinkedList<KeyEvent> keyEvents = keyboard.getEvents();
        for (KeyEvent event : keyEvents) {
            if ((event.getID() == KeyEvent.KEY_PRESSED) &&
                    (event.getKeyCode() == KeyEvent.VK_ESCAPE)) {
                stop();
            }
        }
        moverDisparos();
        dAhora = new Date();
        p38.setVector(vector_DisparosBasicos);
        p38.update(delta, keyboard);
        camara.setY(camara.getY()+0.5);
    }

    public void gameDraw(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.translate(camara.getX(), camara.getY());
        fondo.display(g);
        p38.display(g);
        for (Disparo disparo : vector_DisparosBasicos) {
            // disparo.libre = true;
            if (!disparo.fueraderango) {
                // disparo.libre = false;
                disparo.display(g);
            }
        }
        g.translate(-camara.getX(), -camara.getY());
        long dateDiff = dAhora.getTime() - dInit.getTime();
        long diffSeconds = dateDiff / 1000 % 60;
        long diffMinutes = dateDiff / (60 * 1000) % 60;
        g.setColor(Color.red);
        g.drawString("Tiempo de Juego: " + diffMinutes + ":" + diffSeconds, 12, 42);
        g.drawString("Tecla ESC = Fin del Juego ", 592, 42);
    }

    public void gameShutdown() {
        Log.info(getClass().getSimpleName(), "Shutting down game");
    }

    private void moverDisparos() {
        for (DisparoBasico disparo : vector_DisparosBasicos) {
            // if (disparo.fueraderango&&disparo.libre)
            // Vector_Disparos.removeElement(disparo);
            disparo.setPosition(disparo.getX() + disparo.getDeltaX(), disparo.getY() + disparo.getDeltaY());
            if (disparo.GetOrigen().distance(disparo.getX(), disparo.getY()) > 600) {
                disparo.fueraderango = true;
            }
        }
    }
}
