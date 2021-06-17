package es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.controllers;


import es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.models.Localidad;
import es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.models.Pais;
import es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.models.Region;
import es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.repositoires.LocalidadRepository;
import es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.repositoires.PaisRespository;
import es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.repositoires.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Localidadcontrolador {
    @Autowired
    private LocalidadRepository localidadRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private PaisRespository paisRespository;

    @GetMapping(value = "/toplocalidad")
    public ResponseEntity<Object>top(){
        return new ResponseEntity<>(localidadRepository.totalpuntos(),HttpStatus.OK);
    }


    @GetMapping(value="/localidades")
    public ResponseEntity<Object>localidadesList(){
            return new ResponseEntity<>(localidadRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping(value="/localidades/detail")
    public ResponseEntity<Object>localidadesPlayesr(@RequestParam("id")Long id){
        Localidad lc= localidadRepository.findById(id).orElseThrow(()->new EntityExistsException());
        List<Object>result=new ArrayList<>();
        result.add(lc);
        result.add(lc.getRegistered());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value="/localidad/add")
    public ResponseEntity<Object>altlocalidad(@RequestParam("localidad")String  censo,
                                           @RequestParam("region") String ciudad,
                                           @RequestParam("pais")String mapa){
    //para mantener la coerencia de los id comprueba s ya exiten las localidades y las regiones pos si es asi tome los valores
        //de los que ya existen
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

        localidadRepository.save(nl);
        return new ResponseEntity<>("nueva localidad "+ nl.getNombre(),
                HttpStatus.OK);
    }

    @PutMapping(value  = "/uplocalidad/{id}")
    public ResponseEntity<Object> paisUpdate(@PathVariable("id") Long id, @RequestBody Localidad localidad) throws EntityNotFoundException {
        localidadRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id.toString()));
        localidadRepository.save(localidad);
        return new ResponseEntity<>(localidad, HttpStatus.OK);
    }

    @DeleteMapping(value = "/localidad/{id}")
    public ResponseEntity<Object>deleteLocalidad(@PathVariable("id") Long id){
        localidadRepository.deleteById(id);
        return new ResponseEntity<>("localidad eliminada "+id,HttpStatus.OK);
    }
    //metood para comporbar si exite ya la localidad la region y el pais
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
// metodo para comprobar si existe la region con el pais
    private Region checkRegion(String ciudad, String mapa){
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


}
