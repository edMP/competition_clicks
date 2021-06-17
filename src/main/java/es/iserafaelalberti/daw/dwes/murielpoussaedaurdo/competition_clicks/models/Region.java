package es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRegion;
    private String nombre;

    @JsonBackReference
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "ciudad",cascade = CascadeType.ALL)
    Set<Localidad> ciudades=new HashSet<>();

    @ManyToOne
    @JoinColumn
    private Pais mapa;

    public Region(){

    }
    public Region(String nombre){

        this.nombre=nombre;
    }

    public Region( String nombre, Pais mapa) {

        this.nombre = nombre;
        this.mapa = mapa;
    }
}
