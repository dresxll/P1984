package juego0;

public class Fondo extends ObjetoGrafico {
	public Fondo(String filename) {
		super(filename);
		setPosition(0, -3400); // El fondo es una imagen estatica, pero muy grande
	}

}