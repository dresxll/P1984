package juego0.bonus;

import juego0.core.P38;

public class ObtenerRefuerzos extends Bonus {

    public ObtenerRefuerzos() {
        super("images/1984/Refuerzos.png");
    }

    @Override
    public void aplicar(P38 p38) {
        if (p38.getRefuerzos().isEmpty()) {
            this.setBorrar(true);
            p38.setRefuerzos(1);
        } else {
            this.setBorrar(true);
            p38.setRefuerzos(2);
        }

    }

}
