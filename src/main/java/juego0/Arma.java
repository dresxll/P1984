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
            DisparoBasico disparo1 = new DisparoBasico(x+10,y-10,0,-15);
            disparos_p38.add(disparo1);
            DisparoBasico disparo2 = new DisparoBasico(x+40,y-10,0,-15);
            disparos_p38.add(disparo2);
        } 

    }

    public Tipoarma getTipoarma() {
        return tipoarma;
    }

    public void setTipoarma(Tipoarma tipoarma) {
        this.tipoarma = tipoarma;
    }
}
