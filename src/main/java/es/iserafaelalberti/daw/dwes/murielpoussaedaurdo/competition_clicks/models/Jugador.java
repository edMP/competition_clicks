package es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Getter
@Setter
public class Jugador implements Serializable {

    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int clicks;
    private String imageURL;

    //censo por que las personas estan censadas en una ciudad
    @ManyToOne
    @JoinColumn()
    private Localidad censo;


    @JsonBackReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "jugador")
    Set<Jugadorequipo> participa_jugadorequipo= new HashSet<>();



    public void addCliciks(int clicks){
        this.clicks+=clicks;
    }

    public Jugador() {
    }

    public Jugador(String name, int clicks) {
        this.name = name;
        this.clicks = clicks;
    }

    public Jugador(String name, int clicks, Localidad censo) {
        this.name = name;
        this.clicks = clicks;
        this.censo = censo;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", clicks=" + clicks +
                '}';
    }
}
