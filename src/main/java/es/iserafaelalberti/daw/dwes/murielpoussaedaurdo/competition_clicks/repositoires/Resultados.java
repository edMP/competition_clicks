package es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.repositoires;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Resultados {
    String tipo;
    Long sumaclicks;

    public Resultados() {
    }

    public Resultados(String tipo, Long sumaclicks) {
        this.tipo = tipo;
        this.sumaclicks = sumaclicks;
    }
}
