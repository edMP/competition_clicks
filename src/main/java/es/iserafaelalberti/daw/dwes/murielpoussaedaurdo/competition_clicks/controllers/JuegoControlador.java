package es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.controllers;


import es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.models.Jugador;
import es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.repositoires.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityExistsException;

@RestController
public class JuegoControlador {
    @Autowired
    private JugadorRepository jugadorRepository;

    @PostMapping(value="/addclisks/{playerid}")
    public ResponseEntity<Object>addclicks(@RequestParam("clicks")Integer clicks,@PathVariable("playerid") Long id){
        Jugador jugador = jugadorRepository.findById(id).orElseThrow(EntityExistsException::new);
        jugador.addCliciks(clicks);
        jugadorRepository.save(jugador);

        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
}
