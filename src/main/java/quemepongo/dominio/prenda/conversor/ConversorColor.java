package quemepongo.dominio.prenda.conversor;

import com.google.common.base.Strings;
import javafx.scene.paint.Color;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ConversorColor implements AttributeConverter<Color, String> {

    private static final String SEPARATOR = "|";

    /**
     * Convertir un objeto Color a un String con el formato red|green|blue|alpha
     */
    @Override
    public String convertToDatabaseColumn(Color color) {
        return color.getRed() + SEPARATOR
             + color.getGreen() + SEPARATOR
             + color.getBlue() + SEPARATOR
             + color.getOpacity();
    }

    /**
     * Convertir un String con formato red|green|blue|alpha a un objeto Color
     */
    @Override
    public Color convertToEntityAttribute(String colorString) {
        if (Strings.isNullOrEmpty(colorString)) {
            return null;
        }
        String[] rgb = colorString.split("\\|");
        return new Color(Float.parseFloat(rgb[0]),
                Float.parseFloat(rgb[1]),
                Float.parseFloat(rgb[2]),
                Float.parseFloat(rgb[3]));
    }

}