package es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.repositoires;


import es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.models.Localidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocalidadRepository extends JpaRepository<Localidad,Long> {
    @Query("SELECT r FROM Localidad r WHERE r.nombre=:nombre")
    List<Localidad> buscarLocalidad(@Param("nombre")String nombre);

    @Query("SELECT r FROM Localidad r WHERE r.nombre=:nombre")
    Localidad  buscarLaLocalidad(@Param("nombre")String nombre);



    @Query("SELECT new es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.repositoires.Resultados(l.nombre, SUM(j.clicks))" +
            " FROM Jugador j  inner JOIN   Localidad l on l.idlocalidad = j.censo.idlocalidad group by  l order by SUM (j.clicks) desc ")
    List<Resultados> totalpuntos();
}
