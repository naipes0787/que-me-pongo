package quemepongo.dominio.prenda;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import quemepongo.excepcion.PathInvalidoException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class Imagen {
    private static final Integer ANCHO_FOTO = 200;
    private static final Integer ALTO_FOTO = 200;
    public static final Imagen PRENDA_DESCONOCIDA = new Imagen("./src/main/resources/public/prenda_desconocida.png");

    private File archivo;
    private BufferedImage imagen;

    public Imagen(String pathOriginal) {
        archivo = validar(pathOriginal);
        imagen = normalizar(archivo);
    }

    public static File validar(String path) {
        File archivoOriginal = new File(path);
        if (!archivoOriginal.exists()) {
            throw new PathInvalidoException(path);
        }
        return archivoOriginal;
    }

    private BufferedImage normalizar(File archivo) {
        try {
            return Thumbnails.of(ImageIO.read(archivo)).forceSize(ANCHO_FOTO, ALTO_FOTO).asBufferedImage();
        } catch (IOException e) {
            throw new RuntimeException("Error al normalizar la foto", e);
        }
    }

    @Override
    public String toString() {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            String extension = FilenameUtils.getExtension(archivo.getName());
            ImageIO.write(imagen, extension, os);
            return new String(Base64.encodeBase64(os.toByteArray()));
        } catch (IOException e) {
            throw new RuntimeException("Error al codificar la imagen", e);
        }
    }

    public BufferedImage getImagen() {
        return imagen;
    }

}