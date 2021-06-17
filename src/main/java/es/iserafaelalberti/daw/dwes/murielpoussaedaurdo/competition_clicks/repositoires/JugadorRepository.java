package es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.repositoires;

import es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.models.Jugador;

import es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.models.Jugadorequipo;
import es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.models.Localidad;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JugadorRepository extends  CrudRepository<Jugador,Long> {

        List<Jugador>findByOrderByClicksDesc();

        @Query("SELECT j FROM Jugador j WHERE j.id=:id")
        List<Jugador> buscarJugador(@Param("id")Long id);






}
