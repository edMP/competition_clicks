package es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity

public class Equipo implements Serializable {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;


//    @ManyToMany(fetch = FetchType.EAGER)
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    @JsonBackReference
//    @JoinTable(
//            name="jugadores_equipo",
//            joinColumns = @JoinColumn(name="jugador_id",referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name="equpo_id",referencedColumnName = "id")
//    )
//    Set<Jugador> componentes =new HashSet<>();
    @JsonBackReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "equipo")
    Set<Jugadorequipo> participa_jugadorequipo = new HashSet<>();

    public Equipo(){

    }

    public Equipo(String nombre){

        this.nombre=nombre;
    }

//    public Equipo(String nombre, Jugador jugador) {
//        this.nombre = nombre;
//        componentes.add(jugador);
//        jugador.getPertenecen().add(this);
//    }
//    public void añadirJugador(Jugador jugador){
//        componentes.add(jugador);
//        jugador.getPertenecen().add(this);
//    }
}
