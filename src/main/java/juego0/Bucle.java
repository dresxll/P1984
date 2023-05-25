package juego0;

import com.entropyinteractive.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.*;

public class Bucle extends JGame {
    private Date dInit = new Date(), dAhora;
    private Fondo fondo;
    private Vector<DisparoBasico> vector_DisparosBasicos = new Vector<>();
    private Vector<DisparoBasico> vector_DisparosBasicosLibres = new Vector<>();
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
            fondo.setPosition(9.5, -fondo.getHeight() + 610);
            p38.setPosition((fondo.getWidth() / 2) - 19.5, 500);
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
        limpieza();
        dAhora = new Date();
        p38.setVector(vector_DisparosBasicos);
        fondo.setPosition(fondo.getX(), fondo.getY() + 0.5);
        p38.update(delta, keyboard);
    }

    public void gameDraw(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        fondo.display(g);
        p38.display(g);
        for (DisparoBasico disparo : vector_DisparosBasicos) {
            if (!disparo.fueraderango) {
                disparo.display(g);
            } else
                vector_DisparosBasicosLibres.add(disparo);
        }
        long dateDiff = dAhora.getTime() - dInit.getTime();
        long diffSeconds = dateDiff / 1000 % 60;
        long diffMinutes = dateDiff / (60 * 1000) % 60;
        g.setColor(Color.white);
        g.drawString("Tiempo de Juego: " + diffMinutes + ":" + diffSeconds, 12, 42);
        g.drawString("Tecla ESC = Fin del Juego ", 660, 42);
    }

    public void gameShutdown() {
        Log.info(getClass().getSimpleName(), "Shutting down game");
    }

    private void moverDisparos() {
        for (DisparoBasico disparo : vector_DisparosBasicos) {

            disparo.setPosition(disparo.getX() + disparo.getDeltaX(), disparo.getY() + disparo.getDeltaY());
            if (disparo.GetOrigen().distance(disparo.getX(), disparo.getY()) > disparo.alcance) {
                disparo.fueraderango = true;
            }
        }
    }

    private void limpieza() {
        for (DisparoBasico disparo : vector_DisparosBasicosLibres) {
            vector_DisparosBasicos.remove(disparo);
        }
        vector_DisparosBasicosLibres.clear();

    }
}
