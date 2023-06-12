package juego0.niveles;

import java.awt.event.KeyEvent;
import java.util.Vector;

import juego0.core.Juego;

public class NivelManager {
    private Nivel nivelactual;
    private Juego juego;
    private GeneradorBonus generadorBonus;
    private int indexNivel = 0;

    public NivelManager(Juego juego) {
        this.juego = juego;
        generadorBonus = new GeneradorBonus(juego.getPendientesGraficos(), juego.getSec());
        nivelactual = new Nivel0(juego.getKeyboard(), juego.getPendientesGraficos());
        nivelactual.start();
        generadorBonus.start();
    }

    public Nivel getNivel() {
        return nivelactual;
    }

    public void update() {
        if (juego.getKeyboard().isKeyPressed(KeyEvent.VK_ESCAPE))
            juego.stop();
        int estado = nivelactual.getestado();
        switch (estado) {
            case 1:
                break;// caso normal, en ejecución
            case -1:// cerrar el juego
                juego.stop();
                break;
            case 0:// Game over
                juego.clear();
                nivelactual = new GameOver(juego.getKeyboard(), juego.getPendientesGraficos());
                nivelactual.start();
                break;
            case 2:// Avanzó de nivel
                juego.clear();
                indexNivel++;
                nivelactual = inicializarNivel(indexNivel);
                nivelactual.start();
                break;
            case 3://  Retomó el nivel
                juego.clear();
                nivelactual = inicializarNivel(indexNivel);
                nivelactual.start();
                break;

        }
    }

    private Nivel inicializarNivel(int indexNivel) {
        Nivel nibel = new Nivel1(juego.getPendientesGraficos(), juego.getSec());
        switch (indexNivel) {
            case 1:
            break;
        }
        return nibel;
    }

}
