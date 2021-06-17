package es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.controllers;

import es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.models.Pais;
import es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.repositoires.PaisRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
public class PaisControlador {
    @Autowired
    private PaisRespository paisRespository;

    @GetMapping(value="/paises")
    public ResponseEntity<Object> paisesList(){
        return new ResponseEntity<>(paisRespository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/toppais")
    public ResponseEntity<Object>top(){
        return new ResponseEntity<>(paisRespository.totalpuntos(),HttpStatus.OK);
    }
    @GetMapping(value="/pais/{id}")
    public ResponseEntity<Object> paisInfo(@PathVariable("id") Long id){
        return new ResponseEntity<>(paisRespository.findById(id).
                orElseThrow(EntityNotFoundException::new),
                HttpStatus.OK);
    }
    @PostMapping(value="/pais/add")
    public ResponseEntity<Object>altPpais(@RequestParam("name")String name){
        Pais newp=new Pais(name);
        paisRespository.save(newp);
        return new ResponseEntity<>(newp.getNombre()+" a sido añadido",
                HttpStatus.OK);
    }

    @PutMapping(value  = "/uppais/{id}")
    public ResponseEntity<Object> paisUpdate(@PathVariable("id") Long id, @RequestBody Pais pais) throws EntityNotFoundException {
        paisRespository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id.toString()));
        paisRespository.save(pais);
        return new ResponseEntity<>(pais, HttpStatus.OK);
    }
    @DeleteMapping(value = "/pais/{id}")
    public ResponseEntity<Object>deletepaisS(@PathVariable("id") Long id){
        paisRespository.deleteById(id);
        return new ResponseEntity<>("pais eliminado"+id,HttpStatus.OK);
    }

}
