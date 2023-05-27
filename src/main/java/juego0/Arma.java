package juego0;

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
    public void disparar(double x, double y, Tipoarma t) {
        if (t == Tipoarma.BASE) {
            Disparo disparo = new Disparo("images/1984/bala_simple.png",x,y,0,-25,550);
            Bucle.ObjetoGraficos.add(disparo);
        } 

    }

    public Tipoarma getTipoarma() {
        return tipoarma;
    }

    public void setTipoarma(Tipoarma tipoarma) {
        this.tipoarma = tipoarma;
    }
}
