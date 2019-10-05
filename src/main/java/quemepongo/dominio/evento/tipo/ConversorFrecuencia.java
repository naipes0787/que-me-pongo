package quemepongo.dominio.evento.tipo;

import quemepongo.dominio.frecuencia.Frecuencia;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ConversorFrecuencia implements AttributeConverter<Frecuencia, String> {

    @Override
    public String convertToDatabaseColumn(Frecuencia frecuencia) {
        return frecuencia.expresion();
    }

    @Override
    public Frecuencia convertToEntityAttribute(String expresion) {
        return new Frecuencia(expresion);
    }
}
