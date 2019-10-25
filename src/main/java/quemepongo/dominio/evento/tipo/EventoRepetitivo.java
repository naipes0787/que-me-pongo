package quemepongo.dominio.evento.tipo;

import quemepongo.dominio.frecuencia.Frecuencia;

import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue(TipoEventoFactory.REPETITIVO)
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
