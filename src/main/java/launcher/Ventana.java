package launcher;

import java.awt.*;
import javax.swing.*;

public class Ventana extends JFrame {
    static Ventana ventana;

    

    static void iniciar() {
        Ventana ventana = new Ventana();
        ventana.setTitle("Selección de juego");
        ImageIcon im = new ImageIcon("app/src/main/resources/config.json");
        ventana.setIconImage(im.getImage());
        ventana.setLayout(new BorderLayout());
        ventana.add(BorderLayout.CENTER, new Panelcenter());
        ventana.add(BorderLayout.WEST, new Panelwest());
        ventana.setDefaultCloseOperation(EXIT_ON_CLOSE);
        ventana.setSize(800, 600);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
    }

    public static void main(String args[]) {
        Ventana.iniciar();

    }

}
