package juego0.ataquesEspeciales;

import java.util.Vector;

import juego0.enemigos.Enemigo;

public class Tsunami extends AtaqueEspecial {
    private Vector<Enemigo> afectados = new Vector<>();

    public Tsunami() {
        super("images/1984/Tsunami.png", 400, 0);
    }

    @Override
    public void update() {
        this.moverX(-10);
        paso++;
        if (paso>180) this.borrar=true;
    }

    public Vector<Enemigo> getAfectados() {
        return afectados;
    }
    public int desplazamiento() {
        return paso*(-10);
    }

}
