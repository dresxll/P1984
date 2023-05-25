package juego0;

import java.util.Vector;

enum Tipoarma {
    BASE,
    ESCOPETA,
    AMETRALLADORA,
    LASER
}

public class Arma {
    private Tipoarma tipoarma = Tipoarma.BASE;

    public Arma() {
    }
    public void disparar(double x, double y, Tipoarma t, Vector<DisparoBasico> disparos_p38) {
        if (t == Tipoarma.BASE) {
            DisparoBasico disparo = new DisparoBasico(x,y,0,-25);
            disparos_p38.add(disparo);
        } 

    }

    public Tipoarma getTipoarma() {
        return tipoarma;
    }

    public void setTipoarma(Tipoarma tipoarma) {
        this.tipoarma = tipoarma;
    }
}
