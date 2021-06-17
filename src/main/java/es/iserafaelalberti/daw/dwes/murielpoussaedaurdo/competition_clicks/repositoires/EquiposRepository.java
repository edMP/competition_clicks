package es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.repositoires;

import es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.models.Equipo;
import es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.models.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EquiposRepository extends JpaRepository<Equipo,Long> {
    @Query("SELECT e FROM Equipo e WHERE e.id=:id")
    List<Equipo> buscarEquipo(@Param("id")Long id);

}
