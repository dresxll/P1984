package juego0.core;

import com.entropyinteractive.*;

import juego0.armas.Explosion;
import juego0.armas.disparos.*;
import juego0.ataquesEspeciales.AtaqueEspecial;
import juego0.ataquesEspeciales.Relampago;
import juego0.ataquesEspeciales.Tsunami;
import juego0.bonus.*;
import juego0.bonus.powerUps.*;
import juego0.enemigos.*;
import juego0.niveles.GameOver;
import juego0.niveles.GeneradorBonus;
import juego0.niveles.Nivel;
import juego0.niveles.Nivel0;
import juego0.niveles.Nivel1;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class J1943 extends JGame {
    private Date dInit = new Date(), dAhora = new Date(), dPausado;
    private long[] diffSeconds = { 0 };
    private long dateDiff, diffMinutes, tiempoPausado = 0, acumPause = 0;
    private Vector<ObjetoGrafico> objetosGraficos = new Vector<>(), pendientesGraficos = new Vector<>(),
            limpiezGraficos = new Vector<>();;
    private Vector<Explosion> explosiones = new Vector<>();
    private Keyboard keyboard = this.getKeyboard();
    private P38 p38;
    private Nivel nivelactual;
    private GeneradorBonus generadorBonus;
    private int indexNivel = 0;
    private boolean pause = false, flag = false, hayTsunami = false;
    //private Jugador jugador = new Jugador();

    public static void main(String[] args) {
        J1943 juego = new J1943();
        juego.run(1.0 / 60.0);
        System.exit(0);
    }

    public J1943() {
        super("App", 600, 800);
        System.out.println(appProperties.stringPropertyNames());
    }

    public void gameStartup() {
        try {
            p38 = new P38(keyboard, pendientesGraficos);
            nivelactual = new Nivel0(keyboard, pendientesGraficos);
            nivelactual.start();
            generadorBonus = new GeneradorBonus(pendientesGraficos, diffSeconds);
            generadorBonus.start();
            System.out.println("gameStartup");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void gameShutdown() {
        Log.info(getClass().getSimpleName(), "Shutting down game");
    }

    public void gameUpdate(double delta) {
        updatenivel();
        borrarycargar();
        if ((nivelactual instanceof Nivel0) || (nivelactual instanceof GameOver))
            return;
        actualizarHora(pause);
        if (keyboard.isKeyPressed(KeyEvent.VK_SPACE) && !flag) {
            pause = !pause;
            flag = true;
        } else if (!keyboard.isKeyPressed(KeyEvent.VK_SPACE))
            flag = false;
        if (!pause) {
            verificarObjetos();
            updateGeneral();
        } else {
        }
    }

    public void gameDraw(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (nivelactual.getFondo() != null)
            nivelactual.getFondo().display(g);
        dibujarObjetosGraficos(g);
        if ((nivelactual instanceof Nivel0) || (nivelactual instanceof GameOver))
            return;
        dibujarHUD(g);
    }

    private void dibujarHUD(Graphics2D g) {
        g.setColor(Color.white);
        g.drawString("Tiempo de Juego: " + diffMinutes + ":" + diffSeconds[0], 12, 42);
        g.drawString("Tecla ESC = Fin del Juego ", 460, 42);
        g.drawString("Energia: " + p38.getEnergia(), 520, 780);
        g.setColor(Color.white);
    }

    private void actualizarHora(boolean pause) {
        if (pause) {
            if (dPausado == null)
                dPausado = new Date();
            dAhora = new Date();
            tiempoPausado = dAhora.getTime() - dPausado.getTime();
        } else {
            dPausado = null;
            dAhora = new Date();
            acumPause += tiempoPausado;
            tiempoPausado = 0;
            dateDiff = dAhora.getTime() - dInit.getTime() - acumPause;
            diffSeconds[0] = dateDiff / 1000 % 60;
            diffMinutes = dateDiff / (60 * 1000) % 60;
            p38.setDate();
        }

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
        if ((nivelactual instanceof Nivel0) || (nivelactual instanceof GameOver))
            return;
        p38.display(g);
    }

    private void updateGeneral() {
        hayTsunami = false;
        p38.update();
        if (p38.getEnergia() <= 0)
            nivelactual.setEstado(0);// Gamer Over
        for (ObjetoGrafico objeto : objetosGraficos) {
            objeto.update();
            revisarInstersecciones(objeto);
        }
        if (!hayTsunami && nivelactual.getFondo().movible())
            nivelactual.getFondo().update();
        for (Explosion explosion : explosiones) {
            explosion.update();
        }
    }

    private void revisarInstersecciones(ObjetoGrafico objeto) {
        if ((objeto instanceof AtaqueEspecial)) {
            aplicarAtaque((AtaqueEspecial) objeto);
        } else {
            if (objeto instanceof Enemigo) {
                for (Refuerzo refuerzo : p38.getRefuerzos()) {
                    if ((intersección(refuerzo, objeto)))
                        colisionar(refuerzo, (Enemigo) objeto);
                }
            }
            if ((objeto instanceof Hiteable) && intersección(objeto, p38))
                colisionar(p38, objeto);
            else {
                if (objeto instanceof Disparo) {
                    for (ObjetoGrafico objeto2 : objetosGraficos) {
                        if ((intersección(objeto, objeto2)) && (objeto2 instanceof Hiteable))
                            colisionar((Disparo) objeto, (Hiteable) objeto2);
                    }
                }

            }

        }
    }

    private void aplicarAtaque(AtaqueEspecial ataqueEspecial) {
        if (ataqueEspecial instanceof Relampago) {
            Relampago relampago = (Relampago) ataqueEspecial;
            relampago.aplicar(objetosGraficos);
        } else if (ataqueEspecial instanceof Tsunami) {
            hayTsunami = true;
            Tsunami tsunami = (Tsunami) ataqueEspecial;
            for (ObjetoGrafico objeto2 : objetosGraficos) {
                if (objeto2 instanceof Enemigo) {
                    Enemigo enemigo = (Enemigo) objeto2;
                    if (enemigo.getChico() && !tsunami.getAfectados().contains(enemigo)
                            && intersección(tsunami, enemigo)) {
                        tsunami.getAfectados().add(enemigo);
                        enemigo.borrar = true;
                    }
                }
            }
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

    public static boolean intersección(Tsunami tsunami, ObjetoGrafico b) {
        return ((b.getX() > tsunami.getX() + tsunami.desplazamiento() + 600)
                && (b.getY() > 1000 + tsunami.desplazamiento()));
    }

    public void colisionar(Disparo disparo, Hiteable hiteable) {
        limpiezGraficos.add(disparo);
        hiteable.recibirDisparo(disparo);
        if (hiteable instanceof Bonus) {
            Bonus bonus = (Bonus) hiteable;
            if ((bonus.getCount() >= 3))
                pendientesGraficos.add(bonusRandom(bonus.getX(), bonus.getY() - 40));

        }
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
        enemigo.recibirDanio(2);
    }

    public Vector<ObjetoGrafico> getPendientesGraficos() {
        return pendientesGraficos;
    }

    private Bonus bonusRandom(double x, double y) {
        Random random = new Random();
        int numeroRandom1;
        numeroRandom1 = random.nextInt(7) + 1;
        Bonus bonus = new Auto();
        switch (numeroRandom1) {
            case 1:
                bonus = new Auto();
            case 2:
                bonus = new EstrellaNinja();
                break;
            case 3:
                bonus = new POW();
                break;
            case 4:
                bonus = new SuperShell();
                break;
            case 5:
                bonus = new CambioArma();
                break;
            case 6:
                bonus = new ObtenerRefuerzos();
                break;
        }
        bonus.setPosition(x, y);
        return bonus;
    }

    public Boolean getPause() {
        return pause;
    }

    public void clear() {
        dInit = new Date();
        objetosGraficos.clear();
        pendientesGraficos.clear();
        p38 = new P38(keyboard, pendientesGraficos);
    }

    private void updatenivel() {
        if (keyboard.isKeyPressed(KeyEvent.VK_ESCAPE))
            stop();
        int estado = nivelactual.getestado();
        switch (estado) {
            case 1:
                break;// caso normal, en ejecución
            case -1:// cerrar el juego
                stop();
                break;
            case 0:// Game over
                clear();
                nivelactual = new GameOver(keyboard, pendientesGraficos);
                nivelactual.start();
                break;
            case 2:// Avanzó de nivel
                clear();
                indexNivel++;
                nivelactual = inicializarNivel(indexNivel);
                nivelactual.start();
                break;
            case 3:// Retomó el nivel
                clear();
                nivelactual = inicializarNivel(indexNivel);
                nivelactual.start();
                break;

        }
    }

    private Nivel inicializarNivel(int indexNivel) {
        Nivel nivel = new Nivel1(pendientesGraficos, diffSeconds);
        switch (indexNivel) {
            case 1:
                break;
        }
        return nivel;
    }
}
