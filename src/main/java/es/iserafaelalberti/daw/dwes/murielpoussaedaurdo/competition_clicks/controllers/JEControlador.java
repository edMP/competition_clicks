package es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.controllers;

import es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.models.Equipo;
import es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.models.Jugador;
import es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.models.Jugadorequipo;
import es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.models.Region;
import es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.repositoires.EquiposRepository;
import es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.repositoires.JERepository;
import es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.repositoires.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
public class JEControlador {
    @Autowired
    private JERepository JERepository;
    @Autowired
    JugadorRepository jugadorRepository;
    @Autowired
    private EquiposRepository equiposRepository;

    @GetMapping(value="/jueq")
    public ResponseEntity<Object> jugadorequipoList(){
        return new ResponseEntity<>(JERepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value="/jueq/{id}")
    public ResponseEntity<Object> jugadorequipoInfo(@PathVariable("id") Long id){
        return new ResponseEntity<>(JERepository.findById(id).
                orElseThrow(EntityNotFoundException::new),
                HttpStatus.OK);
    }
    @DeleteMapping(value = "/jueq/{id}")
    public ResponseEntity<Object>deletejugadorequipo(@PathVariable("id") Long id){
        JERepository.deleteById(id);
        return new ResponseEntity<>("equipo eliminado "+id,HttpStatus.OK);
    }
    @PostMapping(value="/jueq/add")
    public ResponseEntity<Object>altEquipo(@RequestParam("jugador_id")Long jugador_id,
                                           @RequestParam("equipo_id")Long equipo_id){
        Jugador j1 =checkJugador(jugador_id);
        Equipo e1=checkEquipo(equipo_id);
        //devulve null si el jugador no eixte
        if(j1==null){
            return new ResponseEntity<>("este jugador no existe ",
                    HttpStatus.NOT_FOUND);
        }
        //devuelve null si el equipo no existe
        if (e1==null){
            return new ResponseEntity<>("este equipo no existe ",
                    HttpStatus.NOT_FOUND);
        }

        JERepository.save(new Jugadorequipo(j1,e1));
        return new ResponseEntity<>(j1.getName()+ " juega en el " +e1.getNombre(),
                HttpStatus.OK);
    }
    @GetMapping(value = "/topjueq")
    public ResponseEntity<Object>top(){
        return new ResponseEntity<>(JERepository.totalpuntos(),HttpStatus.OK);
    }
//metodo para comprobar si existe el jugador
    private Jugador checkJugador(Long id){
        Jugador newju=null;
        List<Jugador> verJugador=jugadorRepository.buscarJugador(id);
        if(verJugador != null && !verJugador.isEmpty() ){
            for(Jugador jugador: verJugador){
                if(id==jugador.getId()){
                    newju=jugador;
                }
            }

        }
        return newju;
    }
//metodo para comprobar si existe el equipo
    private Equipo checkEquipo(Long id){
        Equipo neweq=null;
        List<Equipo> verequipo=equiposRepository.buscarEquipo(id);
        if(verequipo != null && !verequipo.isEmpty() ){
            for(Equipo equipo: verequipo){
                if(id==equipo.getId()){
                    neweq=equipo;
                }
            }

        }
        return neweq;
    }
}
