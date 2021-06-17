package es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Getter
@Setter
@Entity
public class Localidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idlocalidad;
    private String nombre;

    @JsonBackReference
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "censo",cascade = CascadeType.ALL)
    Set<Jugador> registered=new HashSet<>();
    //ciudad por que esta pertenence a una region
    @ManyToOne
    @JoinColumn
    private Region ciudad;

    public Localidad() {
    }

    public Localidad(String nombre) {

        this.nombre = nombre;
    }

    public Localidad( String nombre, Region ciudad) {
        this.nombre = nombre;
        this.ciudad = ciudad;
    }
}
