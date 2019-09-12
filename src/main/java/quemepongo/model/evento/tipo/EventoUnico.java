package quemepongo.model.evento.tipo;

import quemepongo.exceptions.FechaEventoNoValidaException;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class EventoUnico extends TipoEvento {

    private LocalDateTime fecha;

    public EventoUnico(LocalDateTime fecha) {
        if (fecha.toLocalDate().isBefore(LocalDateTime.now().toLocalDate())) {
            throw new FechaEventoNoValidaException();
        }
        this.fecha = fecha;
    }

    @Override
    public LocalDateTime getFecha() {
        return fecha;
    }
}
