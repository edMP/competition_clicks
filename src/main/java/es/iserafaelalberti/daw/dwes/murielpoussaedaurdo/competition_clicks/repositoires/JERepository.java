package es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.repositoires;

import es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.models.Jugadorequipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JERepository extends JpaRepository<Jugadorequipo,Long> {




    @Query("SELECT new es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.repositoires.Resultados" +
            "(e.nombre, SUM(j.clicks))" +
            " FROM  Jugador j  " +
            "INNER JOIN  Jugadorequipo q  on q.jugador.id = j.id  " +
            "INNER JOIN Equipo e on q.equipo.id = e.id" +
            " group by  e order by SUM (j.clicks) desc ")

    List<Resultados> totalpuntos();
}
