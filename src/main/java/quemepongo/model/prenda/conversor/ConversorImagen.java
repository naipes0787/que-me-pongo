package quemepongo.model.prenda.conversor;

import quemepongo.exceptions.ConversorImagenException;

import javax.imageio.ImageIO;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

@Converter
public class ConversorImagen implements AttributeConverter<BufferedImage, Blob> {

    /**
     * Convertir un objeto BufferedImage a un Blob de sql
     */
    @Override
    public Blob convertToDatabaseColumn(BufferedImage bufferedImage) {
        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", baos);
            Blob blobImage = new javax.sql.rowset.serial.SerialBlob(baos.toByteArray());
            return blobImage;
        } catch( SQLException | IOException e) {
            throw new ConversorImagenException("Error al convertir la imagen a formato Blob");
        }
    }

    /**
     * Convertir un objeto Blob de sql a un BufferedImage
     */
    @Override
    public BufferedImage convertToEntityAttribute(Blob blobImage) {
        try{
            InputStream in = blobImage.getBinaryStream();
            BufferedImage bufferedImage = ImageIO.read(in);
            return bufferedImage;
        } catch( SQLException | IOException e) {
            throw new ConversorImagenException("Error al convertir la imagen a formato BufferedImage");
        }
    }

}