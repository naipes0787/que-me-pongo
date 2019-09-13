package quemepongo.model.evento.tipo;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.temporal.ChronoUnit;

@Converter
public class ConversorAnticipacion implements AttributeConverter<Anticipacion, String> {

    @Override
    public String convertToDatabaseColumn(Anticipacion anticipacion) {
        return String.format("%s,%s", anticipacion.getUnidadTiempo().name(), anticipacion.getTiempo());
    }

    @Override
    public Anticipacion convertToEntityAttribute(String s) {
        String[] anticipacion = s.split(",");
        return new Anticipacion(ChronoUnit.valueOf(anticipacion[0]), Long.valueOf(anticipacion[1]));
    }
}
