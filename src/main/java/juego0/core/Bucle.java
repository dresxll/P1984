package juego0.core;

import com.entropyinteractive.*;
import juego0.core.niveles.*;
import juego0.enemigos.*;
import juego0.principal.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Stack;

public class Bucle extends JGame {
    private Nivel nivel;
    private Date dInit = new Date(), dAhora;
    static public Vector<Disparo> disparosLibres = new Vector<>();
    static public Vector<Explosion> explosiones = new Vector<>();
    static public Vector<Explosion> explosionesLibres = new Vector<>();
    static public Vector<Disparo> disparos = new Vector<>();
    static public Vector<Enemigo> enemigos = new Vector<>();
    static public Vector<Enemigo> enemigosLibres = new Vector<>();
    static public Stack<ObjetoGrafico> objetosPorAgregar = new Stack<>();
    private P38 p38 = new P38();
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
        try {
            nivel = new Nivel1();
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
        keyEvents = keyboard.getEvents();
        verificarCierre();
        moverObjetos();
        p38.update(keyboard);
        nivel.getFondo().update();
    }

    public void gameDraw(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        nivel.getFondo().display(g);
        p38.display(g);
        dibujarObjetosGraficos(g);
        dibujarHUD(g);
    }

    private void moverObjetos() {
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
        dAhora = new Date();
        long dateDiff = dAhora.getTime() - dInit.getTime();
        long diffSeconds = dateDiff / 1000 % 60;
        long diffMinutes = dateDiff / (60 * 1000) % 60;
        g.setColor(Color.white);
        g.drawString("Tiempo de Juego: " + diffMinutes + ":" + diffSeconds, 12, 42);
        g.drawString("Tecla ESC = Fin del Juego ", 660, 42);
    }

    private boolean intersección(ObjetoGrafico a, ObjetoGrafico b) {
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
        
        enemigo.recibirDanio(disparo.getDanio());
        if(enemigo.getEnergia()<=0) {
            explosiones.add(new Explosion(enemigo.getX(),enemigo.getY()));
            enemigosLibres.add(enemigo);
        }
        disparosLibres.add(disparo);
        
    }
}
