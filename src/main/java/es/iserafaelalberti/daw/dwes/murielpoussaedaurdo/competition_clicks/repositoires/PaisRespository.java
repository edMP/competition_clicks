package es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.repositoires;

import es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.models.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PaisRespository extends JpaRepository<Pais,Long> {
    @Query("SELECT r FROM Pais r WHERE r.nombre=:nombre")
    List<Pais>buscarPais(@Param("nombre")String nombre);
    @Query("SELECT new es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.repositoires.Resultados(p.nombre, SUM(j.clicks))" +
            " FROM Jugador j  inner JOIN   Localidad l on l.idlocalidad = j.censo.idlocalidad inner JOIN Region r on l.ciudad.idRegion =r.idRegion " +
            "inner JOIN Pais p on r.mapa.idPais= p.idPais group by  p order by SUM (j.clicks) desc ")
    List<Resultados> totalpuntos();
}

