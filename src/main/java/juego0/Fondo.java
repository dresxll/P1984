package juego0;

public class Fondo extends ObjetoGrafico {
	public Fondo(String filename) {
		super(filename);
	}

	@Override
	public void update() {
		positionY+=1;
	}

}