package juego0.bonus;

import java.util.Date;
import java.util.Random;

import juego0.armas.disparos.Disparo;
import juego0.core.Hiteable;
import juego0.core.ObjetoGrafico;
import juego0.core.P38;

public abstract class Bonus extends ObjetoGrafico implements Hiteable {
    private int count;
    public boolean cambiado = false;
    public Bonus(String filename) {
        super(filename, 0, 0);
        Random random = new Random();
        this.moverX(random.nextInt(400) + 100);

    }

    public void update() {
        this.setPosition(positionX, positionY + 2);
        if (count>=3) this.borrar = true;
    }

    public void aplicar(P38 p38) {
        p38.eliminarBonus();
        this.borrar = true;
        p38.setdUltimoBonus(new Date());
        
    }
    public void recibirDisparo(Disparo disparo){
        count++;
        this.moverY(-20);
        if (count>=3){
            disparo.setBorrar(true);
        }
        
    }
    public int getCount(){
        return count;
    }

}

