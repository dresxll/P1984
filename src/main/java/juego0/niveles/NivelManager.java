package juego0.niveles;


import juego0.core.Juego;

public class NivelManager {
    private Nivel nivel;
    //private Juego juego;
    private GeneradorBonus generadorBonus;
    public NivelManager(Juego juego) {
        //this.juego = juego;
        generadorBonus = new GeneradorBonus(juego.getPendientesGraficos(),juego.getSec());
        nivel = new Nivel1(juego.getPendientesGraficos(),juego.getSec());
        nivel.start();
        generadorBonus.start();    }

    public Nivel getNivel() {
        return nivel;
    }

}
