package juego0;

import com.entropyinteractive.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.*;

public class Bucle extends JGame {
    private Date dInit = new Date(), dAhora;
    private Fondo fondo;
    static public Vector<ObjetoGrafico> ObjetoGraficos = new Vector<>();
    static public Vector<ObjetoGrafico> ObjetosLibres = new Vector<>();
    private P38 p38 = new P38("images/1984/p38.png");
    public LinkedList<KeyEvent> keyEvents;
    public Keyboard keyboard = this.getKeyboard();

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
            ObjetoGraficos.add(new Enemigo("images/1984/enemigoPrueba.png"));
            ObjetoGraficos.get(0).setPosition(p38.getX(), p38.getY() - 400);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void gameShutdown() {
        Log.info(getClass().getSimpleName(), "Shutting down game");
    }

    public void gameUpdate(double delta) {
        keyEvents = keyboard.getEvents();
        verificarCierre();
        moverDisparos();
        limpieza();
        fondo.update();
        p38.update(delta, keyboard);
    }

    public void gameDraw(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        fondo.display(g);
        p38.display(g);
        dibujarObjetosGraficos(g);
        dibujarHUD(g);
    }

    private void moverDisparos() {
        for (ObjetoGrafico objetoGrafico : ObjetoGraficos) {
            objetoGrafico.update();
        }
    }

    private void limpieza() {
        for (ObjetoGrafico objetoGrafico : ObjetosLibres) {
            ObjetoGraficos.remove(objetoGrafico);
        }
        ObjetosLibres.clear();

    }

    private void verificarCierre() {
        for (KeyEvent event : keyEvents) {
            if ((event.getID() == KeyEvent.KEY_PRESSED) &&
                    (event.getKeyCode() == KeyEvent.VK_ESCAPE)) {
                stop();
            }
        }

    }

    private void dibujarObjetosGraficos(Graphics2D g) {
        for (ObjetoGrafico objetoGrafico : ObjetoGraficos) {
            if (!ObjetosLibres.contains(objetoGrafico))
                objetoGrafico.display(g);
        }
    }

    private void dibujarHUD(Graphics2D g) {
        dAhora = new Date();
        long dateDiff = dAhora.getTime() - dInit.getTime();
        long diffSeconds = dateDiff / 1000 % 60;
        long diffMinutes = dateDiff / (60 * 1000) % 60;
        g.setColor(Color.white);
        g.drawString("Tiempo de Juego: " + diffMinutes + ":" + diffSeconds, 12, 42);
        g.drawString("Tecla ESC = Fin del Juego ", 660, 42);
    }
}
