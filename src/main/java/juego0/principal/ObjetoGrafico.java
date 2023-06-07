package juego0.principal;

import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public abstract class ObjetoGrafico {
	private BufferedImage imagen = null;
	protected double positionX = 0;
	protected double positionY = 0;

	public ObjetoGrafico(String filename) {
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

	public void display(Graphics2D g2) {
		g2.drawImage(imagen, (int) this.positionX, (int) this.positionY, null);
	}

	public double getX() {
		return positionX;
	}

	public double getY() {
		return positionY;
	}

	public void setImagen(String filename) {
		try {
			imagen = ImageIO.read(getClass().getClassLoader().getResourceAsStream(filename));

		} catch (IOException e) {
			System.out.println(e);
		}

	}

	public void moverX(double x) {
		positionX += x;
	}

	public void moverY(double y) {
		positionY += y;
	}

	public abstract void update();

}