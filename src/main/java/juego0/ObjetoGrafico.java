package juego0;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public abstract class ObjetoGrafico {
	private BufferedImage imagen = null;
	private double positionX = 0;
	private double positionY = 0;

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

	public void setImagen(BufferedImage img) {
		this.imagen = img;

	}

	protected Point2D.Double GetOrigen() {
		return null;
	}

	protected double getAlcance() {
		return 0;
	}

	protected double getDeltaX() {
		return 0;
	}

	protected double getDeltaY() {
		return 0;
	}
}