package es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.boostrap;


import es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.models.*;
import es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.repositoires.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Seeder implements CommandLineRunner {
    @Autowired
    private JugadorRepository jugadorRepository;

    @Autowired
    private LocalidadRepository localidadRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private PaisRespository paisRespository;
    @Autowired
    private EquiposRepository equiposRepository;
    @Autowired
    private JERepository JERepository;

    @Override
    public void run(String[] args){


        Pais p1=paisRespository.save(new Pais("espanna"));
        Pais p2=paisRespository.save(new Pais("francia"));
        Region r1=regionRepository.save((new Region("andalucia",p1)));
        Region r2=regionRepository.save((new Region("cantabria",p1)));
        Region r3=regionRepository.save((new Region("paris",p2)));


        Localidad l1=localidadRepository.save(new Localidad("san fernando",r1));

        Localidad l2=localidadRepository.save(new Localidad("santander",r2));
        Localidad l3=localidadRepository.save(new Localidad("paris",r3));

        Jugador pl1= jugadorRepository.save(new Jugador("Eduardo",0,l1));

        Jugador pl2= jugadorRepository.save(new Jugador("laura",12,l1));

        Jugador pl3= jugadorRepository.save(new Jugador("sofia",12,l2));

        Jugador pl4= jugadorRepository.save(new Jugador("fransua",3,l3));

        Equipo eq1=equiposRepository.save(new Equipo("los relajaos"));
        Equipo eq2=equiposRepository.save(new Equipo("vagete asesine"));

        Jugadorequipo jq1= JERepository.save(new Jugadorequipo(pl1,eq1));

        Jugadorequipo jq2=JERepository.save(new Jugadorequipo(pl4,eq2));


    }
}

