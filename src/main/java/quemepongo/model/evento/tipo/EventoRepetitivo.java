package quemepongo.model.evento.tipo;

import quemepongo.frecuencia.Frecuencia;

import javax.persistence.Convert;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("REPETITIVO")
public class EventoRepetitivo extends TipoEvento {

    @Convert(converter = ConversorFrecuencia.class)
    private Frecuencia frecuencia;

    public EventoRepetitivo(Frecuencia frecuencia) {
        this.frecuencia = frecuencia;
    }

    public EventoRepetitivo() {}

    @Override
    public LocalDateTime getFecha() {
        return frecuencia.calcularProximaOcurrencia();
    }
}
