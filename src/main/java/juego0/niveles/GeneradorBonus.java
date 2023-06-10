package juego0.niveles;

import java.util.Random;
import java.util.Vector;

import juego0.bonus.*;
import juego0.bonus.powerUps.*;
import juego0.core.ObjetoGrafico;

public class GeneradorBonus extends Thread {
    private Vector<ObjetoGrafico> pendientesGraficos;

    public GeneradorBonus(Vector<ObjetoGrafico> pendientesGraficos) {
        this.pendientesGraficos = pendientesGraficos;
    }

    public void run() {
        while (true) {
            try {
                sleep(50);
                pendientesGraficos.add(bonusRandom());
                sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Bonus bonusRandom() {
        Random random = new Random();
        int numeroRandom1;
        numeroRandom1 = random.nextInt(1) + 0;
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
            bonus = new CambioArma();
                break;
            case 7:
            bonus = new CambioArma();
                break;
            case 8:
            bonus = new ObtenerRefuerzos();
                break;
        }
        return bonus;
    }

}
