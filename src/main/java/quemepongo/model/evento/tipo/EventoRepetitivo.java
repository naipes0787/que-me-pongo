package quemepongo.model.evento.tipo;

import quemepongo.frecuencia.Frecuencia;

import javax.persistence.Convert;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class EventoRepetitivo extends TipoEvento {

    @Convert(converter = ConversorFrecuencia.class)
    private Frecuencia frecuencia;

    public EventoRepetitivo(Frecuencia frecuencia) {
        this.frecuencia = frecuencia;
    }

    @Override
    public LocalDateTime getFecha() {
        return frecuencia.calcularProximaOcurrencia();
    }
}
