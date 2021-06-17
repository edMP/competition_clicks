package es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.controllers;

import es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.models.Localidad;
import es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.models.Pais;
import es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.models.Jugador;
import es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.models.Region;
import es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.repositoires.LocalidadRepository;
import es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.repositoires.PaisRespository;
import es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.repositoires.JugadorRepository;
import es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.repositoires.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
public class JugadorControlador {
    @Autowired
    JugadorRepository jugadorRepository;
    @Autowired
    LocalidadRepository localidadRepository;
    @Autowired
    RegionRepository regionRepository;
    @Autowired
    PaisRespository paisRespository;

    @GetMapping(value="/players")
        public ResponseEntity<Object> playersList(){
            return new ResponseEntity<>(jugadorRepository.findAll(),HttpStatus.OK);
    }
    @GetMapping(value="/players/top")
    public ResponseEntity<Object> topList(){
        return new ResponseEntity<>(jugadorRepository.findByOrderByClicksDesc(),HttpStatus.OK);
    }







    @GetMapping(value="/players/{id}")
    public ResponseEntity<Object> playersInfo(@PathVariable("id") Long id){
        return new ResponseEntity<>(jugadorRepository.findById(id).
                orElseThrow(EntityNotFoundException::new),
                HttpStatus.OK);
    }
    @PostMapping(value="/players/add")
    public ResponseEntity<Object>altPlayer(@RequestParam("name")String name,
                                           @RequestParam("clicks")int clicks,
                                           @RequestParam("localidad")String  censo,
                                           @RequestParam("region") String ciudad,
                                           @RequestParam("pais")String mapa){

        Pais ps=null;
        Region rg=checkRegion(ciudad,mapa);
        Localidad nl=crear(censo,ciudad,mapa);
        if(nl==null){
            if(rg==null){
                ps=new Pais(mapa);
                rg=new Region(ciudad,paisRespository.save(ps));
            }
            nl=new Localidad(censo,regionRepository.save(rg));
        }

        Jugador nuevoplayer=new Jugador(name,clicks,localidadRepository.save(nl)  );
        jugadorRepository.save(nuevoplayer);
            return new ResponseEntity<>("bienvenido nuevo jugador "+nuevoplayer.getName(),
                    HttpStatus.OK);
    }
    @PutMapping(value  = "/upjugador/{id}")
    public ResponseEntity<Object> paisUpdate(@PathVariable("id") Long id, @RequestBody Jugador jugador) throws EntityNotFoundException {
        jugadorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id.toString()));
        jugadorRepository.save(jugador);
        return new ResponseEntity<>(jugador, HttpStatus.OK);
    }

    @DeleteMapping(value = "/players/{id}")
    public ResponseEntity<Object>deletePlayer(@PathVariable("id") Long id){
        jugadorRepository.deleteById(id);
        return new ResponseEntity<>("jugador eliminado"+id,HttpStatus.OK);
    }


    private Localidad crear (String censo, String ciudad, String mapa){
        Localidad newlc = null;
        List<Localidad>verlocalidad=localidadRepository.buscarLocalidad(censo);
        if(verlocalidad!= null && !verlocalidad.isEmpty()){
            for (Localidad localidad: verlocalidad) {
                if(ciudad.equals(localidad.getCiudad().getNombre())){
                    if(mapa.equals(localidad.getCiudad().getMapa().getNombre())){
                        newlc=localidad;
                    }
                }

            }
        }
        return newlc;
    }
    //private check region
    private Region checkRegion(String ciudad,String mapa){
        Region newRg=null;
        List<Region>verRegion=regionRepository.buscarRegiones(ciudad);
        if(verRegion != null && !verRegion.isEmpty() ){
            for(Region region: verRegion){
                if(mapa.equals(region.getMapa().getNombre())){
                    newRg=region;
                }
            }

        }
        return newRg;
    }
    //private check pais

}
