package es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.controllers;

import es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.models.Pais;
import es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.models.Region;
import es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.repositoires.PaisRespository;
import es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.repositoires.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
public class RegionControlador {
    @Autowired
    private RegionRepository regionRepository;
    @GetMapping(value="/regiones")
    public ResponseEntity<Object> paisesList(){
        return new ResponseEntity<>(regionRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping(value = "/topregion")
    public ResponseEntity<Object>top(){
        return new ResponseEntity<>(regionRepository.totalpuntos(),HttpStatus.OK);
    }

    @GetMapping(value="/region/{id}")
    public ResponseEntity<Object> paisInfo(@PathVariable("id") Long id){
        return new ResponseEntity<>(regionRepository.findById(id).
                orElseThrow(EntityNotFoundException::new),
                HttpStatus.OK);
    }
    @PutMapping(value  = "/upregion/{id}")
    public ResponseEntity<Object> paisUpdate(@PathVariable("id") Long id, @RequestBody Region region) throws EntityNotFoundException {
        regionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id.toString()));
        regionRepository.save(region);
        return new ResponseEntity<>(region, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delregion/{id}")
    public ResponseEntity<Object>deletePlayer(@PathVariable("id") Long id){
        regionRepository.deleteById(id);
        return new ResponseEntity<>("region eliminada"+id,HttpStatus.OK);
    }
}
