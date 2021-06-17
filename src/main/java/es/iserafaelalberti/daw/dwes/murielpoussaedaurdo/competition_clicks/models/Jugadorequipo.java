package es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Optional;


@Entity
@Getter
@Setter
public class Jugadorequipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;


    @ManyToOne
    @JoinColumn()
    Jugador jugador;

    @ManyToOne
    @JoinColumn()
    Equipo equipo;



    public Jugadorequipo() {

    }

    public Jugadorequipo(Jugador jugador, Equipo equipo) {
        this.jugador = jugador;
        this.equipo = equipo;
    }
}
