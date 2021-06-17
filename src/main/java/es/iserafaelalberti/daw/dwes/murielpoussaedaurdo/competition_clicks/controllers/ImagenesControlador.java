package es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.controllers;

import es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.service.ImagenServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class ImagenesControlador {

    @Autowired
    private ImagenServicio imagenServicio;

    @PostMapping(value = "/subirimagen")
    public ResponseEntity<Object>subirImagenI(@RequestParam("id") String id,
                                              @RequestParam("entidad")String entidad,
                                              @RequestParam("archivo")MultipartFile archivo){
        try{
            String nombrePaquete=this.getClass().getPackageName();
            Class<?>claseEntidad=Class.forName(nombrePaquete.substring(0,nombrePaquete.lastIndexOf("."))+".model."+entidad);

            imagenServicio.imagenStore(archivo,claseEntidad,Long.parseLong(id));
        }catch (IOException e){
            return new ResponseEntity<>("Error en el archivo", HttpStatus.NO_CONTENT);
        }catch (Exception e){
            throw new EntityNotFoundException(entidad);
        }
        return  new ResponseEntity<>(id,HttpStatus.OK);
    }

    @GetMapping(value = "/verimagen/{name}")
    public ResponseEntity<Resource>mostarImagen(@PathVariable("name") String name) {

        Path ruta = Paths.get("./imagenes/" + name).normalize();
        try {
            Resource resource = new UrlResource(ruta.toUri());
            if (resource.exists()) {
                String Contenido = Files.probeContentType(ruta);
                return ResponseEntity.ok().contentType(MediaType.parseMediaType(Contenido)).body(resource);
            }
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }



}
