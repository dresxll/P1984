package juego0.Jefes;

import java.awt.Graphics2D;
import java.util.Vector;

import juego0.enemigos.Blindaje;
import juego0.enemigos.Torreta;

public class Yamato extends Jefe {
    private final int TOTALTORRETAS = 20;
    private double danioAcumulado;
    private int estadoMovimiento = 0, deltax, deltay;
    private long count = 0;
    private Vector<Torreta> torretas = new Vector<>();
    private Vector<Blindaje> blindajes = new Vector<>();

    public Yamato() {
        super("images/1984/Yamato.png", 300, -1500);
        torretas.add(new Torreta("images/1984/T2.png", this.positionX + 98, this.positionY + 523));
        torretas.add(new Torreta("images/1984/T2.png", this.positionX + 98, this.positionY + 385));
        torretas.add(new Torreta("images/1984/T2.png", this.positionX + 98, this.positionY + 523));
        torretas.add(new Torreta("images/1984/T2.png", this.positionX + 98, this.positionY + 1130));

    }

    @Override
    public void update() {
        Limpieza();
        switch (estadoMovimiento) {
            case 0:
                deltay = 3;
                deltax = 0;
                count++;
                if (count >= 500) {
                    estadoMovimiento = 1;
                    count = 0;
                }
                break;
            case 1:
                deltax = -3;
                deltay = 0;
                count++;
                if (count >= 100) {
                    estadoMovimiento = 2;
                    count = 0;
                }
                break;
            case 2:
                deltay = -3;
                deltax = 0;
                count++;
                if (count >= 500) {
                    estadoMovimiento = 3;
                    count = 0;
                }
                break;
            case 3:
                deltax = 3;
                deltay = 0;
                count++;
                if (count >= 100) {
                    estadoMovimiento = 0;
                    count = 0;
                }
                break;
        }
        moverX(deltax);
        moverY(deltay);
        for (Torreta torreta : torretas) {
            torreta.moverX(deltax);
            torreta.moverY(deltay);
        }
    }

    @Override
    public void display(Graphics2D g2) {
        g2.drawImage(imagen, (int) this.positionX, (int) this.positionY, null);
        for (Torreta torreta : torretas) {
            torreta.display(g2);
        }

    }

    public Vector<Torreta> getTorretas() {
        return torretas;
    }
    
    public double getDanioAcumulado(){
        for (Torreta torreta : torretas) {
           danioAcumulado+=torreta.getEnergiaInicial()-torreta.getEnergia();
        }
        return danioAcumulado/TOTALTORRETAS;
    }

    private void Limpieza(){
        Vector<Torreta> vector = new Vector<>();
        for (Torreta torreta : torretas) {
            if (torreta.getBorrar()){
                vector.add(torreta);
            }
        }
        for (Torreta torreta : vector) {
            danioAcumulado+=torreta.getEnergiaInicial();
            torretas.remove(torreta);
        }
    }
}
