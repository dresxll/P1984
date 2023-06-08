package juego0.core;

import com.entropyinteractive.*;
import juego0.core.niveles.*;
import juego0.enemigos.*;
import juego0.principal.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Bucle extends JGame {
    private Nivel nivel;
    private Date dInit = new Date(), dAhora;
    private long dateDiff;
    private long diffSeconds;
    private long diffMinutes;
    private Vector<Disparo> disparosLibres = new Vector<>();
    private Vector<Explosion> explosiones = new Vector<>();
    private Vector<Explosion> explosionesLibres = new Vector<>();
    private Vector<Disparo> disparos = new Vector<>();
    private Vector<Enemigo> enemigos = new Vector<>();
    private Vector<Enemigo> enemigosLibres = new Vector<>();
    private Vector<Enemigo> enemigosEnCola = new Vector<>();
    private P38 p38 = new P38(disparos,disparosLibres);
    private LinkedList<KeyEvent> keyEvents;
    private Keyboard keyboard = this.getKeyboard();

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
        try {
            nivel = new Nivel1(enemigosEnCola);
            nivel.start();
            System.out.println("gameStartup");
            p38.setPosition(400 - p38.getWidth() / 2, 500);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void gameShutdown() {
        Log.info(getClass().getSimpleName(), "Shutting down game");
    }

    public void gameUpdate(double delta) {
        limpieza();
        actualizarHora();
        cargarCola();
        keyEvents = keyboard.getEvents();
        verificarCierre();
        moverObjetos();
        p38.update(keyboard);
    }

    public void gameDraw(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        nivel.getFondo().display(g);
        p38.display(g);
        dibujarObjetosGraficos(g);
        dibujarHUD(g);
    }

    private void moverObjetos() {
        nivel.getFondo().update();
        for (Enemigo enemigo : enemigos) {
            enemigo.update();
        }
        for (Explosion explosion : explosiones) {
            explosion.update();
        }
        for (Disparo disparo : disparos) {
            disparo.update();
            for (Enemigo enemigo : enemigos) {
                if (intersección(disparo, enemigo)) {
                    colisionar(disparo, enemigo);
                }

            }

        }

    }

    private void limpieza() {

        for (Disparo disparo : disparosLibres) {
            disparos.remove(disparo);
        }
        disparosLibres.clear();
        for (Explosion explosion : explosionesLibres) {
            explosiones.remove(explosion);
        }
        explosionesLibres.clear();
        for (Enemigo enemigo : enemigosLibres) {
            enemigos.remove(enemigo);
        }
        enemigosLibres.clear();
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
        for (Enemigo enemigo : enemigos) {
            enemigo.display(g);
        }
        for (Explosion explosion : explosiones) {
            explosion.display(g);
        }
        for (Disparo disparo : disparos) {
            disparo.display(g);
        }
    }

    private void dibujarHUD(Graphics2D g) {
        g.setColor(Color.white);
        g.drawString("Tiempo de Juego: " + diffMinutes + ":" + diffSeconds, 12, 42);
        g.drawString("Tecla ESC = Fin del Juego ", 660, 42);
    }

    private void actualizarHora() {
        dAhora = new Date();
        dateDiff = dAhora.getTime() - dInit.getTime();
        diffSeconds = dateDiff / 1000 % 60;
        diffMinutes = dateDiff / (60 * 1000) % 60;
    }

    public static boolean intersección(ObjetoGrafico a, ObjetoGrafico b) {
        double ax = a.getX();
        double ay = a.getY();
        double aw = a.getWidth();
        double ah = a.getHeight();
        double bx = b.getX();
        double by = b.getY();
        double bw = b.getWidth();
        double bh = b.getHeight();
        aw += ax;
        ah += ay;
        bw += bx;
        bh += by;
        return ((aw < ax || aw > bx) &&
                (ah < ay || ah > by) &&
                (bw < bx || bw > ax) &&
                (bh < by || bh > ay));
    }

    private void colisionar(Disparo disparo, Enemigo enemigo) {

        enemigo.recibirDanio();
        if (enemigo.getEnergia() <= 0) {
            explosiones.add(new Explosion(explosionesLibres,enemigo.getX(), enemigo.getY()));
            enemigosLibres.add(enemigo);
        }
        disparosLibres.add(disparo);

    }

    private void cargarCola() {
        for (Enemigo enemigo : enemigosEnCola) {
            enemigos.add(enemigo);
        }
        enemigosEnCola.clear();
    }
}
