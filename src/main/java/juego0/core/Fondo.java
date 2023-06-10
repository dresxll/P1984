package juego0.core;

public class Fondo extends ObjetoGrafico {
	public Fondo(String filename) {
		super(filename, 0, 0);
	}

	@Override
	public void update() {
		positionY+=1;
	}

}