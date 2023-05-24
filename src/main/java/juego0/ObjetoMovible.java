package juego0;

import com.entropyinteractive.Keyboard;

// import processing.core.*;

public interface ObjetoMovible {

	public void update(double delta, Keyboard keyboard);

	public double getX();

	public double getY();
}