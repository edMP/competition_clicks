package es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Pais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPais;
    private String nombre;

    @JsonBackReference
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "mapa", cascade = CascadeType.ALL)
    Set<Region> mapa=new HashSet<>();


    public Pais(){

    }

    public Pais(String nombre){
        this.nombre=nombre;
    }




}
