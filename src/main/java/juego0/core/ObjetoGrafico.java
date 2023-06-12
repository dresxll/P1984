package juego0.core;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class ObjetoGrafico {
    protected BufferedImage imagen = null;
	protected double positionX = 0;
	protected double positionY = 0;
    protected Boolean borrar = false;
    public ObjetoGrafico(String filename, double positionX, double positionY) {
		try {
			imagen = ImageIO.read(getClass().getClassLoader().getResourceAsStream(filename));
            this.positionX=positionX;
            this.positionY=positionY;

		} catch (IOException e) {
			System.out.println(e);
		}
	}
    public void display(Graphics2D g2) {
		g2.drawImage(imagen, (int) this.positionX, (int) this.positionY, null);
	}
	public void setImagen(String filename) {
		try {
			imagen = ImageIO.read(getClass().getClassLoader().getResourceAsStream(filename));

		} catch (IOException e) {
			System.out.println(e);
		}

	}
    public int getWidth() {
		return imagen.getWidth();
	}
	public int getHeight() {

		return imagen.getHeight();
	}
    public void setPosition(double x, double y) {
		this.positionX = x;
		this.positionY = y;
	}
	public double getX() {
		return positionX;
	}
	public double getY() {
		return positionY;
	}
    public void setX(double x) {
		this.positionX = x;
	}
    public void setY(double y) {
		this.positionY = y;
	}
    public void moverX(double x) {
		positionX += x;
	}
    public void moverY(double y) {
		positionY += y;
	}
    public boolean getBorrar(){
        return borrar;
    }
	public void setBorrar(boolean borrar){
        this.borrar=borrar;
    }
    public abstract void update();    
}
