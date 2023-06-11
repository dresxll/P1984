package juego0.core;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.Vector;

import javax.imageio.ImageIO;

import com.entropyinteractive.Keyboard;

import juego0.armas.*;
import juego0.bonus.Refuerzo;

public class P38 extends ObjetoGrafico {
    private int energia = 100;
    private Keyboard keyboard;
    private int shell = 0;
    private boolean interrumpirdisparo = false, interrumpirdisparo2 = false;
    private Arma arma = new ArmaBase();
    private Date dAhora = new Date(), dDanio, dUltimoBonus, dUltimoDisparo = new Date();
    private BufferedImage p38Invulnerable = null;
    private Vector<ObjetoGrafico> pendientesGraficos;
    private Vector<Refuerzo> refuerzos = new Vector<>();

    public P38(Keyboard keyboard, Vector<ObjetoGrafico> pendientesGraficos) {
        super("images/1984/p38.png", 275, 700);
        this.keyboard = keyboard;
        this.pendientesGraficos = pendientesGraficos;
        arma.setPendienteGraficos(pendientesGraficos);
        try {
            p38Invulnerable = ImageIO
                    .read(getClass().getClassLoader().getResourceAsStream("images/1984/Invulnerable.png"));

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void update() {
        if (keyboard.isKeyPressed(KeyEvent.VK_W) && positionY > 30)
            positionY -= 3;
        if (keyboard.isKeyPressed(KeyEvent.VK_S) && positionY < 756)
            positionY += 3;
        if (keyboard.isKeyPressed(KeyEvent.VK_A) && (positionX > 6))
            positionX -= 3;
        if (keyboard.isKeyPressed(KeyEvent.VK_D) && (positionX < 536))
            positionX += 3;
        if (keyboard.isKeyPressed(KeyEvent.VK_X)) {
            switch (shell) {
                case 0:
                    if (!interrumpirdisparo) {
                        arma.disparar(positionX + 23, positionY - (this.getHeight()));
                        interrumpirdisparo = true;
                    }
                    break;
                case 1:
                    if (dAhora.getTime() - dUltimoDisparo.getTime() > 200) {
                        arma.disparar(positionX + 23, positionY - (this.getHeight()));
                        dUltimoDisparo = new Date();
                    }
                    break;
                case 2:
                    if (dAhora.getTime() - dUltimoDisparo.getTime() > 100) {
                        arma.disparar(positionX + 23, positionY - (this.getHeight()));
                        dUltimoDisparo = new Date();
                    }
                    break;
            }
            if (!interrumpirdisparo2) {
                for (Refuerzo refuerzo : refuerzos) {
                    pendientesGraficos.add(refuerzo.disparar());
                }
                interrumpirdisparo2 = true;
            }
        } else {
            interrumpirdisparo = false;
            interrumpirdisparo2 = false;
        }

        if (dUltimoBonus != null) {
            if ((dAhora.getTime() - dUltimoBonus.getTime()) / 1000 % 60 > 3) {
                eliminarBonus();
            }
        }
        for (Refuerzo refuerzo : refuerzos) {
            if (refuerzo.getIz())
                refuerzo.setPosition(positionX - 50, positionY + 10);
            else
                refuerzo.setPosition(positionX + 82, positionY + 10);
        }
    }

    public int getEnergia() {
        return energia;
    }

    public void recibirDanio(int danio) {
        dDanio = new Date();
        energia -= danio;
    }

    public void recargarEnergia() {
        energia = 100;
    }

    public void recargarEnergia(int a) {
        energia += a;
    }

    public boolean invulnerable() {
        if (dDanio != null) {
            return (dAhora.getTime() - dDanio.getTime()) / 1000 % 60 < 1;
        } else
            return false;
    }

    public void setDate() {
        dAhora = new Date();
    }

    public void setdUltimoBonus(Date dUltimoBonus) {
        this.dUltimoBonus = dUltimoBonus;
    }

    public void eliminarBonus() {
        arma = new ArmaBase();
        arma.setPendienteGraficos(pendientesGraficos);
        dUltimoBonus = null;
        shell = 0;
    }

    public Arma getArma() {
        return arma;
    }

    public void setArma(Arma arma) {
        this.arma = arma;
    }

    public void setShell(int shell) {
        this.shell = shell;
    }

    public int getShell() {
        return shell;
    }

    public Vector<ObjetoGrafico> getPendientesGraficos() {
        return pendientesGraficos;
    }

    @Override
    public void display(Graphics2D g2) {
        if (invulnerable())
            g2.drawImage(p38Invulnerable, (int) this.positionX, (int) this.positionY, null);
        else
            g2.drawImage(imagen, (int) this.positionX, (int) this.positionY, null);
    }

    public void setRefuerzos(int danio) {
        refuerzos.clear();
        refuerzos.add(new Refuerzo(true, 1, this.getX() - 50, this.getY() + 10));
        refuerzos.add(new Refuerzo(false, 1, this.getX() + 82, this.getY() + 10));
    }

    public Vector<Refuerzo> getRefuerzos() {
        return refuerzos;

    }

}
