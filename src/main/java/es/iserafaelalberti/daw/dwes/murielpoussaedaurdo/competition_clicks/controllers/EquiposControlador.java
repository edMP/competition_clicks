package es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.controllers;

import es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.models.Equipo;
import es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.repositoires.EquiposRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class EquiposControlador {
    @Autowired
    private EquiposRepository equiposRepository;

    @GetMapping(value="/equipos")
    public ResponseEntity<Object> equipoList(){
        return new ResponseEntity<>(equiposRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping(value="/equipo/add")
    public ResponseEntity<Object>altEquipo(@RequestParam("name")String name){
        Equipo newe=checkEquipo(name);
        //si devulve distinto de null es que el nombre ya existe por lo que no lo inserta
        if(newe!=null){
            return new ResponseEntity<>("este equipo ya existe ",
                    HttpStatus.CONFLICT);
        }
        newe=new Equipo(name);
        equiposRepository.save(newe);
        return new ResponseEntity<>(newe.getNombre()+" a sido añadido",
                HttpStatus.OK);
    }
    @GetMapping(value="/equipo/detail")
    public ResponseEntity<Object>equipoPlayesr(@RequestParam("id")Long id){
        Equipo equipo= equiposRepository.findById(id).orElseThrow(()->new EntityExistsException());
        List<Object> result=new ArrayList<>();
        result.add(equipo);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }



    @PutMapping(value  = "/upequipo/{id}")
    public ResponseEntity<Object> equipoUpdate(@PathVariable("id") Long id, @RequestBody Equipo equipo) throws EntityNotFoundException {
        equiposRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id.toString()));
        equiposRepository.save(equipo);
        return new ResponseEntity<>(equipo, HttpStatus.OK);
    }

    @DeleteMapping(value = "/equipo/{id}")
    public ResponseEntity<Object>deleteequipo(@PathVariable("id") Long id){
        equiposRepository.deleteById(id);
        return new ResponseEntity<>("equipo  eliminado "+id,HttpStatus.OK);
    }

    //metodo pra comporbar si existe ya un equipo con ese nombre
    private Equipo checkEquipo(String nombre){
        Equipo neweq=null;
        List<Equipo> verequipo=equiposRepository.findAll();
        if(verequipo != null && !verequipo.isEmpty() ){
            for(Equipo equipo: verequipo){
                if(nombre.equals(equipo.getNombre())){
                    neweq=equipo;
                }
            }

        }
        return neweq;
    }
}
