package juego0.core;

import com.entropyinteractive.*;

import juego0.armas.Explosion;
import juego0.armas.disparos.Disparo;
import juego0.armas.disparos.DisparoLaser;
import juego0.bonus.Bonus;
import juego0.bonus.Refuerzo;
import juego0.enemigos.Enemigo;
import juego0.enemigos.Enemigo1;

import juego0.niveles.NivelManager;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Juego extends JGame {
    private Date dInit = new Date(), dAhora = new Date();
    private long dateDiff;
    private long diffSeconds;
    private long diffMinutes;
    private LinkedList<KeyEvent> keyEvents;
    private Vector<ObjetoGrafico> objetosGraficos = new Vector<>();
    private Vector<Explosion> explosiones = new Vector<>();
    private Vector<ObjetoGrafico> pendientesGraficos = new Vector<>();
    private Vector<ObjetoGrafico> limpiezGraficos = new Vector<>();
    private Keyboard keyboard = this.getKeyboard();
    private P38 p38;
    private NivelManager nivelManager;
    private boolean pause = false, flag = false;

    public static void main(String[] args) {
        Juego juego = new Juego();
        juego.run(1.0 / 60.0);
        System.exit(0);
    }

    public Juego() {
        super("App", 600, 800);
        System.out.println(appProperties.stringPropertyNames());
    }

    public void gameStartup() {
        try {
            nivelManager = new NivelManager(pendientesGraficos);
            p38 = new P38(keyboard, pendientesGraficos);
            System.out.println("gameStartup");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void gameShutdown() {
        Log.info(getClass().getSimpleName(), "Shutting down game");
    }

    public void gameUpdate(double delta) {
        if (keyboard.isKeyPressed(KeyEvent.VK_SPACE) && !flag) {
            pause = !pause;
            flag = true;
        } else if (!keyboard.isKeyPressed(KeyEvent.VK_SPACE))
            flag = false;
        if (!pause) {
            verificarObjetos();
            borrarycargar();
            keyEvents = keyboard.getEvents();
            actualizarHora();
            verificarCierre();
            updateGeneral();
        }

    }

    public void gameDraw(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        nivelManager.getNivel().getFondo().display(g);
        dibujarObjetosGraficos(g);
        dibujarHUD(g);
    }

    private void verificarCierre() {
        for (KeyEvent event : keyEvents) {
            if ((event.getID() == KeyEvent.KEY_PRESSED) &&
                    (event.getKeyCode() == KeyEvent.VK_ESCAPE)) {
                stop();
            }
        }

    }

    private void dibujarHUD(Graphics2D g) {
        g.setColor(Color.white);
        g.drawString("Tiempo de Juego: " + diffMinutes + ":" + diffSeconds, 12, 42);
        g.drawString("Tecla ESC = Fin del Juego ", 460, 42);
        g.drawString("Energia: " + p38.getEnergia(), 520, 780);
    }

    private void actualizarHora() {
        dAhora = new Date();
        dateDiff = dAhora.getTime() - dInit.getTime();
        diffSeconds = dateDiff / 1000 % 60;
        diffMinutes = dateDiff / (60 * 1000) % 60;
        p38.setDate();
    }

    private void dibujarObjetosGraficos(Graphics2D g) {
        for (ObjetoGrafico objeto : objetosGraficos) {
            objeto.display(g);
        }
        for (Explosion explosion : explosiones) {
            explosion.display(g);
        }
        for (Refuerzo refuerzo : p38.getRefuerzos())
            refuerzo.display(g);
        p38.display(g);
    }

    private void updateGeneral() {
        nivelManager.getNivel().getFondo().update();

        p38.update();
        for (ObjetoGrafico objeto : objetosGraficos) {
            objeto.update();
            for (Refuerzo refuerzo : p38.getRefuerzos()) {
                if (objeto instanceof Enemigo && (intersección(refuerzo, objeto)))
                    colisionar(refuerzo, (Enemigo) objeto);
            }
            if (intersección(objeto, p38))
                colisionar(p38, objeto);
            for (ObjetoGrafico objeto2 : objetosGraficos) {
                if ((objeto.getClass() != objeto2.getClass()) && (intersección(objeto, objeto2))) {
                    colisionar(objeto, objeto2);
                }
            }
        }

        for (Explosion explosion : explosiones) {
            explosion.update();
        }
    }

    private void verificarObjetos() {
        p38.update();
        for (ObjetoGrafico objeto : objetosGraficos) {
            if (objeto.getBorrar())
                limpiezGraficos.add(objeto);
        }
        for (Explosion explosion : explosiones) {
            if (explosion.getBorrar())
                limpiezGraficos.add(explosion);
        }
        for (Refuerzo refuerzo : p38.getRefuerzos()) {
            if (refuerzo.getBorrar())
                limpiezGraficos.add(refuerzo);
        }
    }

    private void borrarycargar() {
        for (ObjetoGrafico objeto : limpiezGraficos) {
            if (objeto instanceof Explosion) {
                explosiones.remove(objeto);
            } else {
                if (objeto instanceof Enemigo)
                    explosiones.add(new Explosion(objeto.getX(), objeto.getY()));
                objetosGraficos.remove(objeto);
            }
            if (objeto instanceof Refuerzo) {
                explosiones.add(new Explosion(objeto.getX(), objeto.getY()));
                p38.getRefuerzos().remove(objeto);
            }

        }
        limpiezGraficos.clear();
        for (ObjetoGrafico objeto : pendientesGraficos) {
            objetosGraficos.add(objeto);
        }
        pendientesGraficos.clear();
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

    public void colisionar(ObjetoGrafico objeto, ObjetoGrafico objeto2) {
        String nombreClase = objeto.getClass().getSuperclass().getSimpleName();
        switch (nombreClase) {
            case "Disparo":
                colisionar((Disparo) objeto, objeto2);
                break;
        }
    }

    public void colisionar(Disparo disparo, ObjetoGrafico objeto2) {
        if (objeto2 instanceof Enemigo) {
            Enemigo1 enemigo;
            enemigo = (Enemigo1) objeto2;
            enemigo.recibirDanio(disparo.getDanio());
        } else if (objeto2 instanceof Bonus) {
            Bonus bonus;
            bonus = (Bonus) objeto2;
            bonus.moverY(-20);

        }
        if (!(disparo instanceof DisparoLaser))
            disparo.setBorrar(true);
    }

    public void colisionar(P38 p38, ObjetoGrafico objeto2) {
        if ((objeto2 instanceof Enemigo) && (!p38.invulnerable())) {
            Enemigo enemigo = (Enemigo) objeto2;
            enemigo.recibirDanio(1);
            p38.recibirDanio(10);
        }
        if ((objeto2 instanceof Disparo)) {
            // objeto2.setBorrar(true);
            // p38.recibirDanio(5);
        }
        if ((objeto2 instanceof Bonus)) {
            Bonus bonus = (Bonus) objeto2;
            bonus.aplicar(p38);
        }
    }

    public void colisionar(Refuerzo refuerzo, Enemigo enemigo) {

        refuerzo.setBorrar(true);
        enemigo.recibirDanio(1);
    }

    public Vector<ObjetoGrafico> getPendientesGraficos() {
        return pendientesGraficos;
    }
}
