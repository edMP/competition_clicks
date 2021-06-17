package es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.repositoires;

import es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.models.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region,Long> {

    @Query("SELECT r FROM Region  r WHERE r.nombre=:nombre")
    List<Region> buscarRegiones(@Param("nombre")String nombre);
    @Query("SELECT new es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.repositoires.Resultados(r.nombre, SUM(j.clicks))" +
            " FROM Jugador j " +
            " inner JOIN   Localidad l on l.idlocalidad = j.censo.idlocalidad " +
            "inner JOIN Region r on l.ciudad.idRegion =r.idRegion" +
            " group by  r order by SUM (j.clicks) desc ")
    List<Resultados> totalpuntos();
}
