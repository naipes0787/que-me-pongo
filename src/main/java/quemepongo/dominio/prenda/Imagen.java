package quemepongo.dominio.prenda;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.codec.binary.Base64;
import quemepongo.excepcion.PathInvalidoException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class Imagen {
    private static final Integer ANCHO_FOTO = 200;
    private static final Integer ALTO_FOTO = 200;
    public static final Imagen PRENDA_DESCONOCIDA = new Imagen("./src/main/resources/public/prenda_desconocida.png");

    private BufferedImage normalizada;

    public Imagen(String path) {
        normalizada = normalizar(archivo(path));
    }

    private static File archivo(String path) {
        File archivo = new File(path);
        if (!archivo.exists()) {
            throw new PathInvalidoException(path);
        }
        return archivo;
    }

    private BufferedImage normalizar(File archivo) {
        try {
            return Thumbnails.of(archivo).forceSize(ANCHO_FOTO, ALTO_FOTO).asBufferedImage();
        } catch (IOException e) {
            throw new RuntimeException("Error al normalizar la foto", e);
        }
    }

    @Override
    public String toString() {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(normalizada, "png", os);
            return new String(Base64.encodeBase64(os.toByteArray()));
        } catch (IOException e) {
            throw new RuntimeException("Error al codificar la imagen", e);
        }
    }

    public BufferedImage getNormalizada() {
        return normalizada;
    }

}