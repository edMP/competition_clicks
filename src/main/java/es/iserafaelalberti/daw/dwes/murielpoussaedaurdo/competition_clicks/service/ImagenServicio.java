package es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ImagenServicio {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public boolean imagenStore(MultipartFile archivo, Class entidad, Long id) throws IOException {
        try {
            Object objeto = entityManager.find(entidad, id);

            String nombreArchivo = entidad.getSimpleName() + "_" + id.toString() + "_" + archivo.getOriginalFilename();

            Path targetPath = Paths.get("./imagenes/" +
                    nombreArchivo)
                    .normalize();
            Files.copy(archivo.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            Method method = entidad.getMethod("setImageUrl", String.class);
            method.invoke(objeto,"./imagenes/" + nombreArchivo);
            entityManager.persist(entidad.cast(objeto));
        } catch (Exception e) {
            throw new EntityNotFoundException(entidad.getSimpleName() + ": " + id.toString());
        }

        return true;
    }

}
