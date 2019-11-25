package quemepongo.util;

import org.apache.commons.fileupload.FileItem;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class RepositorioImagenes {

    private File directorio = new File("imagenes_subidas");

    private static RepositorioImagenes instancia = new RepositorioImagenes();

    private RepositorioImagenes() {
        directorio.mkdir();
    }

    public static RepositorioImagenes instancia() {
        return instancia;
    }

    public String dir() {
        return directorio.getName();
    }

    public String subir(FileItem fileItem) {
        try {
            Path file = Files.createTempFile(directorio.toPath(), "", "");
            Files.copy(fileItem.getInputStream(), file, StandardCopyOption.REPLACE_EXISTING);
            return file.toFile().getPath();
        } catch (Exception e) {
            throw new RuntimeException("Error al intentar subir imagen");
        }
    }

}
